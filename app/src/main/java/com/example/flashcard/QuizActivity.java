package com.example.flashcard;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;


public class QuizActivity extends AppCompatActivity {


    TextView resultView;
    RadioGroup inputRadioGroup;
    Button confirmButton;
    private int idquestion;
    private Quiz quest;
    private ArrayList<Quiz> questions;
    private TextView correctAwserTextView;
    private TextView questionNumberTextView;
    private boolean questvalide;
    private int nbrquestvalid;
    private int nbrquestions;

    private ArrayList<Quiz> nbrquesterror;

    private static final String TAG = "QuizActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!questvalide)
                {
                    questvalide = true;
                    resultView.setText("Tu n'as pas répondu assez rapidement");
                    correctAwserTextView.setText("La bonne réponse était " + quest.choice.get(quest.response));
                    for (int i = 0; i < inputRadioGroup.getChildCount(); i++) {
                        inputRadioGroup.getChildAt(i).setEnabled(false);
                    }
                    nbrquesterror.add(quest);
                    OnConfirmButtonClick(0);
                }
            }
        }, 10_000);

        Chronometer simpleChronometer = findViewById(R.id.simpleChronometer);
        simpleChronometer.setFormat("Delais: %s");
        simpleChronometer.setBase(SystemClock.elapsedRealtime());
        Log.d(TAG, "onCreate: " + SystemClock.elapsedRealtime());
        simpleChronometer.start();

        simpleChronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                long elapsedMillis = SystemClock.elapsedRealtime() - simpleChronometer.getBase();
                if (elapsedMillis >= 10_000 || questvalide) {
                    simpleChronometer.stop();
                }
            }
        });


        resultView = findViewById(R.id.awserTextView);
        correctAwserTextView = findViewById(R.id.correctAwserTextView);
        confirmButton = findViewById(R.id.confirmButton);
        inputRadioGroup = findViewById(R.id.inputRadioGroup);
        ImageView imageView = findViewById(R.id.flagImageView);
        questionNumberTextView = findViewById(R.id.questionNumberTextView);
        questvalide = false;



        Intent intent = getIntent();
        questions = intent.getParcelableArrayListExtra("quiz");
        Log.d("QuizActivity", "question = " + questions);

        nbrquestvalid = intent.getIntExtra("nbrquestvalid", 0);
        nbrquesterror = intent.getParcelableArrayListExtra("nbrquesterror");
        if (nbrquesterror == null)
        {
            nbrquesterror = new ArrayList<>();
        }


        idquestion = intent.getIntExtra("idquestion", 0);
        Log.d("QuizActivity", "idquestion = " + idquestion);

        nbrquestions = questions.size();
        int idquest = idquestion + 1;
        questionNumberTextView.setText(idquest + "/" + nbrquestions);

        quest = questions.get(idquestion);
        ArrayList<String> options = quest.choice;

        for (int i = 0; i < inputRadioGroup.getChildCount(); i++) {
            View child = inputRadioGroup.getChildAt(i);
            if (child instanceof RadioButton) {
                RadioButton radioButton = (RadioButton) child;
                radioButton.setText(options.get(i));
                if (quest.difficulty == 3){
                    radioButton.setRotationY(180);
                    imageView.setRotation(180);
                }
            }
        }

        imageView.setImageResource(quest.imageid);


        imageView.setOnClickListener(view -> {
            Intent intent_media = new Intent(this, MediaInteractActivity.class);
            intent_media.putExtra("image", quest.imageid);
            startActivity(intent_media);
        });


        inputRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull RadioGroup group, int checkedId) {
                confirmButton.setOnClickListener(view -> {
                    OnConfirmButtonClick(checkedId);
                });
            }
        });
        Button returnButton = findViewById(R.id.returnButton);
        returnButton.setOnClickListener(view ->{
            Intent intentReturn = new Intent(this, MainActivity.class);
            startActivity(intentReturn);
            finish();
        });
    }

    private void OnConfirmButtonClick(int checkedId) {
        RadioButton radioButton = findViewById(checkedId);


        if (!questvalide) {
            if (radioButton.getText() == quest.choice.get(quest.response)) {
                resultView.setText("Bonne réponse");
                for (int i = 0; i < inputRadioGroup.getChildCount(); i++) {
                    inputRadioGroup.getChildAt(i).setEnabled(false);
                }
                questvalide = true;
                nbrquestvalid += 1;
            } else {
                questvalide = true;
                resultView.setText("Mauvaise réponse");
                for (int i = 0; i < inputRadioGroup.getChildCount(); i++) {
                    inputRadioGroup.getChildAt(i).setEnabled(false);
                }
                correctAwserTextView.setText("La bonne réponse était " + quest.choice.get(quest.response));
                nbrquesterror.add(quest);
            }
        }



        if (idquestion + 1 >= questions.size()) {
            confirmButton.setText("Voir les statistiques");
        }
        else{
            confirmButton.setText("Question suivante");
        }
        confirmButton.setOnClickListener(view -> {

            if (idquestion + 1 >= questions.size()) {
                Intent intent_result = new Intent(this, ResultActivity.class);
                intent_result.putExtra("nbrquestions", questions.size());
                intent_result.putExtra("nbrquestvalid", nbrquestvalid);
                intent_result.putExtra("nbrquesterror", nbrquesterror);
                intent_result.putExtra("difficult", quest.difficulty);
                recordendtime();
                statsglobal(nbrquestvalid, nbrquesterror.size());
                startActivity(intent_result);
                finish();
            } else {
                Intent intent = new Intent(this, QuizActivity.class);
                intent.putExtra("idquestion", idquestion + 1);
                intent.putExtra("quiz", questions);
                intent.putExtra("nbrquestvalid", nbrquestvalid);
                intent.putExtra("nbrquesterror", nbrquesterror);
                startActivity(intent);
                finish();
            }
        });
    }

    private void recordendtime() {
        long endtime = System.nanoTime();
        SharedPreferences prefs = getSharedPreferences("stats", MODE_PRIVATE);
        long starttime = prefs.getLong("starttime", 0);
        Log.d(TAG, "recordendtime: " + starttime);

        long time = endtime - starttime;

        int timesec = (int) (time / 1_000_000_000);

        prefs.edit().remove("timemoyen").apply();

        int timemoyen = (prefs.getInt("timemoyen", 0) + timesec) / 2;
        int alltime = prefs.getInt("alltime", 0) + timesec;

        Log.d(TAG, "timemoyen :" +timemoyen);

        prefs.edit().putInt ("timemoyen", timemoyen).apply();
        prefs.edit().putInt("alltime", alltime).apply();


    }

    private void statsglobal(int nbrtrueresponse , int nbrfalseresponse) {
        SharedPreferences prefs = getSharedPreferences("stats", MODE_PRIVATE);

        int nbrstartquiz = prefs.getInt("nbrstartquiz", 0) + 1;
        int trueresponse = prefs.getInt("trueresponse", 0) + nbrtrueresponse;
        int falseresponse = prefs.getInt("falseresponse", 0) + nbrfalseresponse;

        prefs.edit().putInt("nbrstartquiz", nbrstartquiz).apply();
        prefs.edit().putInt("trueresponse", trueresponse).apply();
        prefs.edit().putInt("falseresponse", falseresponse).apply();
    }
}
package com.example.flashcard;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

        int srcImageId = R.drawable.point_interogation;

        ImageView imageView = (ImageView) findViewById(R.id.flagImageView);
        imageView.setImageResource(srcImageId);

        imageView.setOnClickListener( view -> {
            Intent intent = new Intent(this, MediaInteractActivity.class);
            intent.putExtra("image", srcImageId);
            startActivity(intent);
        });



        resultView = findViewById(R.id.resultView);
        confirmButton = findViewById(R.id.confirmButton);
        inputRadioGroup  = findViewById(R.id.inputRadioGroup);
        ImageView flagImageView = findViewById(R.id.flagImageView);


        Intent intent = getIntent();
        questions = intent.getParcelableArrayListExtra("quiz");
        Log.d("QuizActivity", "question = " + questions);

        idquestion = intent.getIntExtra("idquestion", 0);
        Log.d("QuizActivity", "idquestion = " + idquestion);

        quest = questions.get(idquestion);
        ArrayList<String> options = quest.questions;

        for (int i = 0; i < inputRadioGroup.getChildCount(); i++) {
            View child = inputRadioGroup.getChildAt(i);
            if (child instanceof RadioButton ) {
                RadioButton radioButton = (RadioButton) child;
                radioButton.setText(options.get(i));
            }
        }

        flagImageView.setImageResource(quest.imageid);




        inputRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull RadioGroup group, int checkedId) {
                confirmButton.setOnClickListener( view -> {
                    OnConfirmButtonClick(checkedId);
                });
            }
        });
    }

    private void OnConfirmButtonClick(int checkedId) {
        RadioButton radioButton = findViewById(checkedId);

        if (radioButton.getText() == quest.questions.get(quest.response) ){
            resultView.setText("Bonne réponse");
            Intent intent = new Intent(this, QuizActivity.class);
            intent.putExtra("idquestion", idquestion +1);
            intent.putExtra("quiz",questions);
            startActivity(intent);
            finish();
        }
        else {
            TextView correctAwserTextView = findViewById(R.id.correctAwserTextView);
            resultView.setText("Mauvaise réponse");
            correctAwserTextView.setText("La Bonne réponse était : " + quest.questions.get(quest.response));
        }
    }
}
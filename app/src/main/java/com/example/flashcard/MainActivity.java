package com.example.flashcard;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Reboot prefs
//        SharedPreferences prefs = getSharedPreferences("stats", MODE_PRIVATE);
//        prefs.edit().clear().apply();

        //When the Quizz button is pressed, check the choosen difficulty setting and send it
        Button quizzButton = findViewById(R.id.quizzButton);
        RadioButton easyRadioButton = findViewById(R.id.easyDifficultyRadioButton);
        RadioButton mediumRadioButton = findViewById(R.id.mediumDifficultyRadioButton);
        RadioButton hardRadioButton = findViewById(R.id.hardDifficultyRadioButton);
        quizzButton.setOnClickListener(view ->{
            if (easyRadioButton.isChecked()){
                Intent intent = new Intent(this, QuizActivity.class);
                ArrayList<Quiz> quizList = QuizLoader.loadQuiz(0);
                intent.putExtra("quiz", quizList);
                recordstarttime();
                startActivity(intent);
            } else if (mediumRadioButton.isChecked()){
                Intent intent = new Intent(this, QuizActivity.class);
                ArrayList<Quiz> quizList = QuizLoader.loadQuiz(1);
                intent.putExtra("quiz", quizList);
                recordstarttime();
                startActivity(intent);
            } else if (hardRadioButton.isChecked()) {
                Intent intent = new Intent(this, QuizActivity.class);
                ArrayList<Quiz> quizList = QuizLoader.loadQuiz(2);
                intent.putExtra("quiz", quizList);
                recordstarttime();
                startActivity(intent);
            }
            else {
                TextView noDifficultyTextView = findViewById(R.id.noDifficultyTextView);
                noDifficultyTextView.setVisibility(TextView.VISIBLE);
            }
        });
        //Takes the user to the about activity
        Button aboutButton = findViewById(R.id.aboutButton);
        aboutButton.setOnClickListener(view ->{
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
        });

        Button statButton = findViewById(R.id.statButton);
        statButton.setOnClickListener(view ->{
            Intent intent = new Intent(this, StatistiqueActivity.class);
            startActivity(intent);
        });

        Button listQuestionButton = findViewById(R.id.listQuestionButton);
        listQuestionButton.setOnClickListener(view ->{
            Intent intent = new Intent(this, ListQuestionActivity.class);
            startActivity(intent);
        });


    }

    private void recordstarttime() {
        long time = System.nanoTime();
        SharedPreferences prefs = getSharedPreferences("stats", MODE_PRIVATE);
        prefs.edit().putLong("starttime", time).apply();
    }
}





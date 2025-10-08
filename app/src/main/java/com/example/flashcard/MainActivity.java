package com.example.flashcard;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

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

        Log.d(TAG, "Hello Flashcard");

        ArrayList<Quiz> quizList = new ArrayList<>();

        ArrayList<String> questions = new ArrayList<>();
        questions.add("Commencer un massage cardiaque");
        questions.add("Lui rouler une pelle");
        questions.add("Tu est dans une tempête  et c'est ok");

        quizList.add(new Quiz(R.drawable.arret, questions,1,0 ));

        ArrayList<String> questions2 = new ArrayList<>();
        questions.add("Japon");
        questions.add("Royaume-Uni");
        questions.add("Espagne");
        quizList.add(new Quiz(R.drawable.flag_japon, questions2,0,0 ));

        ArrayList<String> questions3 = new ArrayList<>();
        questions.add("Japon");
        questions.add("Royaume-Uni");
        questions.add("Espagne");
        quizList.add(new Quiz(R.drawable.flag_england, questions3, 1,0 ));

        Intent intent = new Intent(this, QuizActivity.class);
        intent.putExtra("quiz", quizList);
        startActivity(intent);
    }
}
package com.example.flashcard;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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

        //When the Quizz button is pressed, check the choosen difficulty setting and send it
        Button quizzButton = findViewById(R.id.quizzButton);
        quizzButton.setOnClickListener(view ->{
                Intent intent = new Intent(this, QuizActivity.class);
                RadioGroup difficultyRadioGroup = findViewById(R.id.difficultyRadioGroup);
                intent.putExtra("difficulty",difficultyRadioGroup.getCheckedRadioButtonId());
                startActivity(intent);
        });
        //Takes the user to the about activity
        Button aboutButton = findViewById(R.id.aboutButton);
        aboutButton.setOnClickListener(view ->{
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
        });
}}
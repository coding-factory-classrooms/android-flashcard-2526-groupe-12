package com.example.flashcard;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
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
    Parcelable[] questions;

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


        resultView = findViewById(R.id.resultView);
        confirmButton = findViewById(R.id.confirmButton);
        inputRadioGroup  = findViewById(R.id.inputRadioGroup);
        ImageView flagImageView = findViewById(R.id.flagImageView);

        Intent intent = getIntent();
        ArrayList<Quiz> questions = intent.getParcelableArrayListExtra("quiz");
        Quiz question = questions.get(0);
        ArrayList<String> options = question.questions;

        for (int i = 0; i < inputRadioGroup.getChildCount(); i++) {
            View child = inputRadioGroup.getChildAt(i);
            if (child instanceof RadioButton ) {
                RadioButton radioButton = (RadioButton) child;
                radioButton.setText(options.get(i));
            }
        }

        flagImageView.setImageResource(question.flagid);






        inputRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull RadioGroup group, int checkedId) {
                RadioButton radioButton = findViewById(checkedId);
                confirmButton.setOnClickListener( view -> {
                    if (radioButton.getText() == options.get(question.response) ){
                        resultView.setText("Bonne réponse");
                    }
                    else {
                        resultView.setText("Mauvaise réponse");
                    }
                });

            }

        });


    }
}
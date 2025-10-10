package com.example.flashcard;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Currency;

public class ListQuestionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_question);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ArrayList<Quiz> quizArrayList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            ArrayList<Quiz> quizList = QuizLoader.loadQuiz(5);
            quizArrayList.addAll(quizList);
        }

        QuizAdapter adapter = new QuizAdapter(quizArrayList);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter.setOnItemClickListener((quiz, position) -> {

            Intent intent = new Intent(this, QuizActivity.class);
            intent.putParcelableArrayListExtra("quiz", quizArrayList);
            intent.putExtra("idquestion", position);
            startActivity(intent);
        });
    }

}
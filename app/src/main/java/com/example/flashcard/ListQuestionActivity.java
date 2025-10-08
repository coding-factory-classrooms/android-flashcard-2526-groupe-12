package com.example.flashcard;

import android.os.Bundle;

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

        // Je crée en dur 15000 currencies pour avoir des trucs a afficher
        // Ca peut venir d'une API, un DB etc
        ArrayList<Quiz> quizArrayList = new ArrayList<>();
        for (int i = 0; i < 5000; i++) {
            /*quizArrayList.add(new Quiz(R.drawable.flag_usa, "$", 1.17f));
            quizArrayList.add(new Quiz(R.drawable.flag_japon, "¥", 176f));
            quizArrayList.add(new Quiz(R.drawable.flag_uk, "£", 6.20f));*/
        }

        // On branche tout le monde
        // les données a l'adapter
        // l'adapter au recyclerview
        QuizAdapter adapter = new QuizAdapter(quizArrayList);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
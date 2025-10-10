package com.example.flashcard;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class StatistiqueActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_statistique);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView totalTextView = findViewById(R.id.totalTextView);
        TextView moyenneTextView = findViewById(R.id.moyenneTextView);
        TextView nbrstartquizTextView = findViewById(R.id.nbrstartquizTextView);
        TextView trueresponseTextView = findViewById(R.id.trueresponseTextView);
        TextView falseresponseTextView = findViewById(R.id.falseresponseTextView);

        SharedPreferences prefs = getSharedPreferences("stats", MODE_PRIVATE);
        int timemoyen = prefs.getInt("timemoyen", 0);
        int alltime = prefs.getInt("alltime", 0);
        int nbrstartquiz = prefs.getInt("nbrstartquiz", 0);
        int trueresponse = prefs.getInt("trueresponse", 0);
        int falseresponse = prefs.getInt("falseresponse", 0);

        totalTextView.setText("Tu as passé en tout " +alltime+ " sec sur notre jeux");
        moyenneTextView.setText("En moyenne une partie dure " + timemoyen +" sec");
        nbrstartquizTextView.setText("Tu as réaliser " + nbrstartquiz + " quiz");
        trueresponseTextView.setText("Tu as eu " + trueresponse +" bonne réponses");
        falseresponseTextView.setText("Tu as eu "+ falseresponse +" mauvaises réponses");

    }
}
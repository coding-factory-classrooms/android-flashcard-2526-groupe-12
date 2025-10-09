package com.example.flashcard;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        Intent intent = getIntent();

        int nbrquestvalid = intent.getIntExtra("nbrquestvalid", 0);
        int nbrquestions = intent.getIntExtra("nbrquestions", 0);
        int diffuclt = intent.getIntExtra("difficult", 0);
        TextView difficultTextView = findViewById(R.id.difficultTextView);
        TextView resultTextView = findViewById(R.id.resultTextView);
        TextView pourcentageTextView = findViewById(R.id.pourcentageTextView);

        if (diffuclt==0){
            difficultTextView.setText("Mode facile");
        } else if (diffuclt==1) {
            difficultTextView.setText("Mode moyen");
        } else if (diffuclt==2) {
            difficultTextView.setText("Mode difficile");
        }

        resultTextView.setText(nbrquestvalid + "/" + nbrquestions);

        int pourcentagecalcul = (nbrquestvalid * 100) / nbrquestions;
        pourcentageTextView.setText("Tu a eu "+String.valueOf(pourcentagecalcul) + "% pourcent de réussite");

    }
}
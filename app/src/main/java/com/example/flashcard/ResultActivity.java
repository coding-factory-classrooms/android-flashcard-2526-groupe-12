package com.example.flashcard;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

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
        int difficult = intent.getIntExtra("difficult", 0);
        ArrayList<Quiz> nbrquesterror = intent.getParcelableArrayListExtra("nbrquesterror");
        TextView difficultTextView = findViewById(R.id.difficultTextView);
        TextView resultTextView = findViewById(R.id.resultTextView);
        TextView pourcentageTextView = findViewById(R.id.pourcentageTextView);
        Button errorButton = findViewById(R.id.errorButton);
        Button shareButton = findViewById(R.id.shareButton);
        String dif;


        if (difficult==0){
            difficultTextView.setText("Mode facile");
            dif = "facile";
        } else if (difficult==1) {
            difficultTextView.setText("Mode moyen");
            dif = "moyen";
        } else if (difficult==2) {
            difficultTextView.setText("Mode difficile");
            dif = "difficile";
        } else if (difficult==3) {
            difficultTextView.setText("Mode hardcore");
            dif = "Hardcore";
        } else {
            dif = "";
        }

        resultTextView.setText(nbrquestvalid + "/" + nbrquestions);

        int pourcentagecalcul = (nbrquestvalid * 100) / nbrquestions;
        pourcentageTextView.setText("Tu a eu "+ pourcentagecalcul + "% pourcent de réussite");

        if (nbrquesterror!=null && !nbrquesterror.isEmpty()){
            errorButton.setOnClickListener(view -> {
                Intent intent_quiz = new Intent(this, QuizActivity.class);
                intent_quiz.putExtra("quiz",nbrquesterror);
                startActivity(intent_quiz);
                finish();
            });
        }else {
            errorButton.setVisibility(View.GONE);
        }

        shareButton.setOnClickListener(view -> {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "J'ai eu " + nbrquestvalid+"/"+nbrquestions + " au quiz " + dif + " sur l'app Le pire sauveteur !");
            sendIntent.setType("text/plain");
            Intent shareIntent = Intent.createChooser(sendIntent, null);
            startActivity(shareIntent);
        });

        Button menuButton = findViewById(R.id.menuButton);
        menuButton.setOnClickListener(view -> {
            Intent intentMenu = new Intent(this, MainActivity.class);
            startActivity(intentMenu);
        });
    }
}
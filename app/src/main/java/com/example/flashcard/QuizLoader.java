package com.example.flashcard;

import java.util.ArrayList;
import java.util.Collections;

public class QuizLoader {

    static ArrayList<Quiz> loadQuiz() {
        ArrayList<Quiz> quizList = new ArrayList<>();

        ArrayList<String> questions = null;

        questions = new ArrayList<>();
        questions.add("Commencer un massage cardiaque");
        questions.add("Lui rouler une pelle");
        questions.add("Tu est dans une tempête d'émotion et c'est ok");
        Collections.shuffle(questions);
        quizList.add(new Quiz(R.drawable.arret, questions, questions.indexOf("Lui rouler une pelle"), 0));

        questions = new ArrayList<>();
        questions.add("Japon");
        questions.add("Royaume-Uni");
        questions.add("Espagne");
        Collections.shuffle(questions);
        quizList.add(new Quiz(R.drawable.flag_uk, questions, questions.indexOf("Japon"), 0));

        questions = new ArrayList<>();
        questions.add("Japon");
        questions.add("Royaume-Uni");
        questions.add("Espagne");
        Collections.shuffle(questions);
        quizList.add(new Quiz(R.drawable.flag_england, questions, questions.indexOf("Royaume-Uni"), 0));

        Collections.shuffle(quizList);

        return quizList;
    }
}

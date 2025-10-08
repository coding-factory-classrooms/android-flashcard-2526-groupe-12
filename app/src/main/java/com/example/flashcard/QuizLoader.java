package com.example.flashcard;

import java.util.ArrayList;

public class QuizLoader {

    static ArrayList<Quiz> loadQuiz() {
        ArrayList<Quiz> quizList = new ArrayList<>();

        ArrayList<String> questions = null;

        questions = new ArrayList<>();
        questions.add("Commencer un massage cardiaque");
        questions.add("Lui rouler une pelle");
        questions.add("Tu est dans une tempête d'émotion et c'est ok");

        quizList.add(new Quiz(R.drawable.arret, questions, 1, 0));

        questions = new ArrayList<>();
        questions.add("Japon");
        questions.add("Royaume-Uni");
        questions.add("Espagne");
        quizList.add(new Quiz(R.drawable.flag_england, questions, 0, 0));

        questions = new ArrayList<>();
        questions.add("Japon");
        questions.add("Royaume-Uni");
        questions.add("Espagne");
        quizList.add(new Quiz(R.drawable.flag_england, questions, 1, 0));

        return quizList;
    }
}

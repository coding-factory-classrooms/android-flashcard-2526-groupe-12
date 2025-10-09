package com.example.flashcard;

import java.util.ArrayList;
import java.util.Collections;

public class QuizLoader {

     static ArrayList<Quiz> loadQuiz(int difficulty) {
        ArrayList<Quiz> quizList = new ArrayList<>();

        ArrayList<String> questions = null;

        questions = new ArrayList<>();
        questions.add("Commencer un massage cardiaque");
        questions.add("Lui rouler une pelle");
        questions.add("Tu est dans une tempête d'émotion et c'est ok");
        Collections.shuffle(questions);
        quizList.add(new Quiz(R.drawable.arret, questions, questions.indexOf("Lui rouler une pelle"), 1));

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

        questions = new ArrayList<>();
        questions.add("Déinfecter la blessure");
        questions.add("C'est rien, ca va guérir tout seul !");
        questions.add("S'emputer");
        Collections.shuffle(questions);
        quizList.add(new Quiz(R.drawable.blessures, questions, questions.indexOf("S'emputer"), 1));

        questions = new ArrayList<>();
        questions.add("Faire la manoeuvre de Heimlich");
        questions.add("Frapper dans le ventre pendant que l'aversaire est affaibli");
        questions.add("Mettre ca main dans ca bouche pour retirer ce qui bloque");
        Collections.shuffle(questions);
        quizList.add(new Quiz(R.drawable.etouffement, questions, questions.indexOf("Frapper dans le ventre pendant que l'aversaire est affaibli"), 2));

        questions = new ArrayList<>();
        questions.add("S'arreter et appeller les secours");
        questions.add("Continuer, je suis en retard ai travail");
        questions.add("S'arreter et faire un selfie");
        Collections.shuffle(questions);
        quizList.add(new Quiz(R.drawable.accident, questions, questions.indexOf("Continuer, je suis en retard ai travail"), 2));

        quizList.removeIf(n -> (n.difficulty != difficulty && difficulty != 5));
        Collections.shuffle(quizList);

        return quizList;
    }
}

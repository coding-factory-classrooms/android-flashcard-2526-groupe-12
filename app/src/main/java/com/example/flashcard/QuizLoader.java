package com.example.flashcard;

import com.google.gson.reflect.TypeToken;

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
        quizList.add(new Quiz(R.drawable.arret, questions, questions.indexOf("Lui rouler une pelle"), 0));

        questions = new ArrayList<>();
        questions.add("Déinfecter la blessure");
        questions.add("C'est rien, ca va guérir tout seul !");
        questions.add("S'emputer");
        Collections.shuffle(questions);
        quizList.add(new Quiz(R.drawable.blessures, questions, questions.indexOf("S'emputer"), 0));

        questions = new ArrayList<>();
        questions.add("Faire la manoeuvre de Heimlich");
        questions.add("Frapper dans le ventre pendant que l'aversaire est affaibli");
        questions.add("Mettre ca main dans ca bouche pour retirer ce qui bloque");
        Collections.shuffle(questions);
        quizList.add(new Quiz(R.drawable.etouffement, questions, questions.indexOf("Frapper dans le ventre pendant que l'aversaire est affaibli"), 1));

        questions = new ArrayList<>();
        questions.add("S'arreter et appeller les secours");
        questions.add("Continuer, je suis en retard ai travail");
        questions.add("S'arreter et faire un selfie");
        Collections.shuffle(questions);
        quizList.add(new Quiz(R.drawable.accident, questions, questions.indexOf("Continuer, je suis en retard ai travail"), 1));

        questions = new ArrayList<>();
        questions.add("Cela sert a apporter des premier soins");
        questions.add("C'est un super sac à main Dior");
        questions.add("Cela sert a transporter la coke discretement");
        Collections.shuffle(questions);
        quizList.add(new Quiz(R.drawable.ifak, questions, questions.indexOf("Cela sert a transporter la coke discretement"), 3));

        questions = new ArrayList<>();
        questions.add("C'est un masque chirurgicale");
        questions.add("C'est pour cacher les moche");
        questions.add("C'est un sous-vetement");
        Collections.shuffle(questions);
        quizList.add(new Quiz(R.drawable.mask, questions, questions.indexOf("C'est un sous-vetement"), 3));


        questions = new ArrayList<>();
        questions.add("Faire couler abondament de l'eau sur la brulure");
        questions.add("Appliquer un film plastique");
        questions.add("Désinfecter");
        Collections.shuffle(questions);
        quizList.add(new Quiz(R.drawable.brule, questions, questions.indexOf("Faire couler abondament de l'eau sur la brulure"), 2));

        questions = new ArrayList<>();
        questions.add("Faire 5 insuflations");
        questions.add("Faire un message cardiaque");
        questions.add("Poser un défibralateur directement après avoir sortie la victime de l'eau");
        Collections.shuffle(questions);
        quizList.add(new Quiz(R.drawable.noyade, questions, questions.indexOf("Poser un défibralateur directement après avoir sortie la victime de l'eau"), 2));

        quizList.removeIf(n -> (n.difficulty != difficulty && difficulty != 5));
        Collections.shuffle(quizList);

        return quizList;
    }
}

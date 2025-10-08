package com.example.flashcard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

// Cette classe fait le lien entre l'UI (RecylcerView)
// et les données (une liste de Currency)
public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.ViewHolder> {
    ArrayList<Quiz> quizArrayList;

    public QuizAdapter(ArrayList<Quiz> quizArrayList) {
        this.quizArrayList = quizArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Code relou pour récupérer notre item_currency.xml
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_currency,
                parent,
                false);

        // On crée notre instance de ViewHolder qui serarecylcée a chaque scroll de l'utilisateur
        ViewHolder viewHolder= new ViewHolder(view);
        return  viewHolder;
    }

    // A chaque fois qu'on veut afficher une ligne, cette fonction est appelée
    // On va modifier les données pour une row de notre RecyclerView
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Quiz quiz = quizArrayList.get(position);
        holder.imageView.setImageResource(quiz.imageid);
        String text = "";
        // Parcourir quiz.questions
        // pour chaque string dans questions + (concatener ) avec un espace et la variable text
        for (String question : quiz.questions) {
            text += question + " ";
        }
        //ArrayList<String> a = {"a", "b", "c"};
        //print(text) // "a b c"

        holder.textView.setText(text);
        //holder.rateTextView.setText(quiz.rate + ""); // feinte du chacal pour convertir en String
    }

    @Override
    public int getItemCount() {
        return quizArrayList.size();
    }

    // Ce sont des instance de ViewHolder qui vont être recylcés lorsqu'on scroll
    // Un ViewHolder fait le lien avec item_currency.xml
    // Si j'ai 10 items vivibles a l'écran, j'aurais 10 instance de ViewHolder
    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}




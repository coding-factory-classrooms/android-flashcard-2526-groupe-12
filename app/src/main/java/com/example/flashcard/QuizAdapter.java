package com.example.flashcard;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.ViewHolder> {
    ArrayList<Quiz> quizArrayList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Quiz quiz, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public QuizAdapter(ArrayList<Quiz> quizArrayList) {
        this.quizArrayList = quizArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_currency,
                parent,
                false);

        ViewHolder viewHolder= new ViewHolder(view);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("QuizAdapter", "onBindViewHolder position = " + position);

        Quiz quiz = quizArrayList.get(position);
        holder.imageView.setImageResource(quiz.imageid);
        String text = "";
        for (String question : quiz.choice) {
            text += question + " ";
        }
        holder.textView.setText(text);
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(quiz, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return quizArrayList.size();
    }

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




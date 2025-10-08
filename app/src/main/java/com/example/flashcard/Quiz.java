package com.example.flashcard;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Quiz implements Parcelable {
    public final int imageid;

    public final ArrayList<String> questions;

    public final int response;

    public final int difficulty;

    public Quiz(int imageid, ArrayList<String> questions, int response, int difficulty) {
        this.imageid = imageid;
        this.questions = questions;
        this.response = response;
        this.difficulty = difficulty;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "imageid=" + imageid +
                ", questions=" + questions +
                ", response=" + response +
                ", difficulty=" + difficulty +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {

    }
}





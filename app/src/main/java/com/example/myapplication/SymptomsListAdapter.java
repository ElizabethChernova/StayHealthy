package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.entities.Note;
import com.example.entities.Pill;

import java.util.List;

public class SymptomsListAdapter  extends RecyclerView.Adapter<SymptomsListAdapter.SymptomsViewHolder> {

    private List<Note> symptoms; // Cached copy of words

    SymptomsListAdapter(List<Note> symptoms) {
        this.symptoms =symptoms;
    }

    @Override
    public SymptomsListAdapter.SymptomsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_symptoms, parent, false);
        return new SymptomsListAdapter.SymptomsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SymptomsListAdapter.SymptomsViewHolder holder, int position) {
        if (symptoms != null) {
            Note current = symptoms.get(position);
            holder.ratingBar.setRating(current.getRate());
            holder.data.setText(current.getData().toString() + "\n" + current.getTime());
            holder.comment.setText(current.getComment());
        } else {
            // Covers the case of data not being ready yet.
           // holder.name.setText("No name");
        }
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (symptoms != null)
            return symptoms.size();
        else return 0;
    }

    class SymptomsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final RatingBar ratingBar;
        private final TextView data;
        private final LinearLayout linearComment;
        private final TextView comment;

        private SymptomsViewHolder(View itemView) {
            super(itemView);
            data = itemView.findViewById(R.id.dayAndTime);
            ratingBar = itemView.findViewById(R.id.lastRatingBar);
            linearComment= itemView.findViewById(R.id.additionalPanel);
            comment = itemView.findViewById(R.id.comment);
        }

        @Override
        public void onClick(View view) {
            linearComment.setVisibility(View.VISIBLE);
        }
    }

}



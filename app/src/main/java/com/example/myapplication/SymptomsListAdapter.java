package com.example.myapplication;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.entities.Note;
import com.example.entities.Person;
import com.example.entities.Pill;
import com.example.entities.Storage;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SymptomsListAdapter  extends RecyclerView.Adapter<SymptomsListAdapter.SymptomsViewHolder> {

    private List<Note> symptoms; // Cached copy of words

    SymptomsListAdapter(List<Note> symptoms) {
        Collections.reverse(symptoms);
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
            if(current.isTemperature())
                holder.l1.setVisibility(View.VISIBLE);
            if(current.isCaught())
                holder.l2.setVisibility(View.VISIBLE);
            if(current.isBadAppetite())
                holder.l3.setVisibility(View.VISIBLE);
            if(current.isBadEating())
                holder.l4.setVisibility(View.VISIBLE);
            if(current.isBadMood())
                holder.l5.setVisibility(View.VISIBLE);

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

    class SymptomsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,  View.OnCreateContextMenuListener {
        private final RatingBar ratingBar;
        private final TextView data;
        private final LinearLayout linearComment;
        private final EditText comment;
        private final LinearLayout item;

        //symptoms
        private final LinearLayout l1;
        private final LinearLayout l2;
        private final LinearLayout l3;
        private final LinearLayout l4;
        private final LinearLayout l5;

        private boolean alreadyOpen=false;

        private SymptomsViewHolder(View itemView) {
            super(itemView);
            data = itemView.findViewById(R.id.dayAndTime);
            ratingBar = itemView.findViewById(R.id.lastRatingBar);
            linearComment= itemView.findViewById(R.id.additionalPanel);
            comment = itemView.findViewById(R.id.comment);

            l1 = itemView.findViewById(R.id.linearLayout2);
            l2 = itemView.findViewById(R.id.linearLayout3);
            l3 = itemView.findViewById(R.id.linearLayout4);
            l4 = itemView.findViewById(R.id.linearLayout5);
            l5 = itemView.findViewById(R.id.linearLayout6);

            item =  itemView.findViewById(R.id.item);
            item.setOnClickListener(this);
            item.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onClick(View view) {
            if(!alreadyOpen){
                alreadyOpen=true;
                linearComment.setVisibility(View.VISIBLE);
                ratingBar.setIsIndicator(false);


            }
            else{
                alreadyOpen=false;
                linearComment.setVisibility(View.GONE);
                ratingBar.setIsIndicator(true);
                Person p=Storage.importFromJSON(view.getContext());
                p.getNotes().get(p.getNotes().size()-1-getAdapterPosition()).setComment(comment.getText().toString());
                p.getNotes().get(p.getNotes().size()-1-getAdapterPosition()).setRate(ratingBar.getRating());
                Storage.exportToJSON(view.getContext(), p);

            }

        }
        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.add(this.getAdapterPosition(), 122,1,"Видалити");
        }

    }
    public void removeItem(int position)
    {
        symptoms.remove(position);
        notifyDataSetChanged();
    }

}



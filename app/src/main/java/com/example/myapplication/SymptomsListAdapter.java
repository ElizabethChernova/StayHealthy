package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.entities.Pill;

import java.util.List;

public class SymptomsListAdapter  extends RecyclerView.Adapter<SymptomsListAdapter.SymptomsViewHolder>{

    private final LayoutInflater mInflater;
    private List<Pill> symptoms; // Cached copy of words

    SymptomsListAdapter(Context context, List<Pill> pills) {
        mInflater = LayoutInflater.from(context);
        this.symptoms =pills;
    }

    @Override
    public SymptomsListAdapter.SymptomsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new SymptomsListAdapter.SymptomsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SymptomsListAdapter.SymptomsViewHolder holder, int position) {
        if (symptoms != null) {
            Pill current = symptoms.get(position);
         //   holder.name.setText(current.getName() + ", доза: "+current.getDose()+"мл");
            //holder.time.setText(current.getTimes().get(0).toString());
            //holder.dependency.setText(current.getDependency());

            // holder.priority.setBackgroundColor();
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

    class SymptomsViewHolder extends RecyclerView.ViewHolder {
        private final RatingBar ratingBar;
        private final TextView data;

        private SymptomsViewHolder(View itemView) {
            super(itemView);
            data = itemView.findViewById(R.id.dayAndTime);
            ratingBar = itemView.findViewById(R.id.lastRatingBar);
        }
    }
}



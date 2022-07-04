package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.entities.Pill;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

public class MedicalListAdapter extends RecyclerView.Adapter<MedicalListAdapter.MedicalViewHolder> {

    private final LayoutInflater mInflater;
    private List<Pill> pills; // Cached copy of words

    MedicalListAdapter(Context context, List<Pill> pills) {
        mInflater = LayoutInflater.from(context);
        this.pills=pills;
    }

    @Override
    public MedicalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new MedicalViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MedicalViewHolder holder, int position) {
        if (pills != null) {
            Pill current = pills.get(position);
            holder.name.setText(current.getName() + ", доза: "+current.getDose()+"мл");
           // holder.time.setText(current.getTimes().get(0).toString());
            holder.dependency.setText(current.getDependency());
           // holder.priority.setBackgroundColor();
        } else {
            // Covers the case of data not being ready yet.
            holder.name.setText("No name");
        }
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (pills != null)
            return pills.size();
        else return 0;
    }

    class MedicalViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView time;
        private final TextView dependency;
        private final ShapeableImageView priority;

        private MedicalViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_of_the_medical);
            time = itemView.findViewById(R.id.time);
            dependency = itemView.findViewById(R.id.condition);
            priority = itemView.findViewById(R.id.priority);
        }
    }
}


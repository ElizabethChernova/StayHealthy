package com.example.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
            holder.name.setText(current.getName() + ", "+current.getDose()+"мг");
            holder.time.setText(current.getTimes().get(0).toString());
            holder.dependency.setText(current.getDependency());
            holder.days.setText("Ще лишилось днів: " + current.getNumberOfDays());
            if (!current.getComment().equals("")) {
                holder.comment.setVisibility(View.VISIBLE);
                holder.comment.setText("Коментар: " + current.getComment());
            }
            else {
                holder.comment.setVisibility(View.GONE);
                holder.comment.setText("");
            }
            if(current.getAlarmType()=='A')
            {
                holder.priority.setBackgroundColor(Color.parseColor("#ED786F"));
            }else{
                holder.priority.setBackgroundColor(Color.parseColor("#BCAFAE"));
            }
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

    class MedicalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener {
        private final TextView name;
        private final TextView time;
        private final TextView dependency;
        private final ShapeableImageView priority;
        private final LinearLayout item;
        private final LinearLayout additionField;
        private final TextView days;
        private final TextView comment;

        private boolean alreadyOpen=false;

        private MedicalViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_of_the_medical);
            time = itemView.findViewById(R.id.time);
            dependency = itemView.findViewById(R.id.condition);
            priority = itemView.findViewById(R.id.priority);
            item =  itemView.findViewById(R.id.item);
            additionField = itemView.findViewById(R.id.additionScreen);
            days = itemView.findViewById(R.id.daysNeedToEat);
            comment = itemView.findViewById(R.id.comment);

            item.setOnCreateContextMenuListener(this);
            item.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(!alreadyOpen){
                alreadyOpen=true;
                additionField.setVisibility(View.VISIBLE);


            }
            else{
                alreadyOpen=false;
                additionField.setVisibility(View.GONE);
            }
        }
        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.add(this.getAdapterPosition(), 121,0,"Редагувати");
            contextMenu.add(this.getAdapterPosition(), 122,1,"Видалити");
        }

    }
    public void removeItem(int position)
    {
        //TODO remove from json
        pills.remove(position);
        notifyDataSetChanged();
    }
}


package com.example.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.entities.Person;
import com.example.entities.Pill;
import com.example.entities.Storage;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MedicalListAdapter extends RecyclerView.Adapter<MedicalListAdapter.MedicalViewHolder> {

    private final LayoutInflater mInflater;
    private List<Pill> pills; // Cached copy of words
    private static Person person;
    private static Context myContext;

    MedicalListAdapter(Context context, List<Pill> pills) {
        mInflater = LayoutInflater.from(context);
        this.pills=pills;
        person= Storage.importFromJSON(context);
        myContext=context;
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
            if(current.getStatus()==1) {
                holder.item.setBackgroundColor(Color.parseColor("#B9BCBC"));
                holder.switchDone.setVisibility(View.GONE);
            }
            else{
                holder.item.setBackgroundColor(Color.parseColor("#C4EDEB"));
                holder.switchDone.setVisibility(View.VISIBLE);
            }
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

    class MedicalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener, CompoundButton.OnCheckedChangeListener {
        private final TextView name;
        private final TextView time;
        private final TextView dependency;
        private final ShapeableImageView priority;
        private final LinearLayout item;
        private final LinearLayout additionField;
        private final TextView days;
        private final TextView comment;
        private final Switch switchDone;

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
            switchDone = itemView.findViewById(R.id.switchDone);

            switchDone.setOnCheckedChangeListener(this);
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


        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
            if(isChecked){
                //todo skip item to end
                person.getPills().get(getAdapterPosition()).setCurrentTimesPerDay(person.getPills().get(getAdapterPosition()).getCurrentTimesPerDay()+1);
                if(person.getPills().get(getAdapterPosition()).getCurrentTimesPerDay()<person.getPills().get(getAdapterPosition()).getTimesPerDay()){
                    //swipe used time to the end of array list? make next time on the top of the list
                    person.getPills().get(getAdapterPosition()).getTimes().add(person.getPills().get(getAdapterPosition()).getTimes().remove(0));
                    item.setBackgroundColor(Color.parseColor("#C4EDEB"));
                }
                else{
                    person.getPills().get(getAdapterPosition()).getTimes().add(person.getPills().get(getAdapterPosition()).getTimes().remove(0));
                    item.setBackgroundColor(Color.parseColor("#B9BCBC"));
                    person.getPills().get(getAdapterPosition()).setStatus(1);
                    compoundButton.setVisibility(View.GONE);
                }


                Collections.sort(person.getPills(), new Comparator<Pill>() {
                    @Override
                    public int compare(Pill pill1, Pill pill2) {
                        return Integer.compare(pill1.getStatus(),pill2.getStatus());
                    }
                });

                Collections.sort(person.getPills(), new Comparator<Pill>() {
                    @Override
                    public int compare(Pill pill1, Pill pill2) {
                        if (pill1.getStatus() < pill2.getStatus())
                            return -1;
                        if (pill1.getStatus() > pill2.getStatus())
                            return 1;
                        else{
                            if (pill1.getTimes().get(0).getHours()<pill2.getTimes().get(0).getHours()) return -1;
                            if (pill1.getTimes().get(0).getHours()>pill2.getTimes().get(0).getHours()) return 1;
                            else{
                                return Integer.compare(pill1.getTimes().get(0).getMinutes(),pill2.getTimes().get(0).getMinutes());
                            }

                        }
                    }
                });
//                Collections.sort(person.getPills(), new Comparator<Pill>() {
//                    @Override
//                    public int compare(Pill pill1, Pill pill2) {
//                        return pill1.getUserTimes().get(0).compareTo(pill2.getUserTimes().get(0));
//                    }
//                });
                Storage.exportToJSON(myContext,person);
                compoundButton.setChecked(false);
                time.setText(person.getPills().get(0).getTimes().get(0).toString());

            }else{
                //todo skip item to start
                item.setBackgroundColor(Color.parseColor("#C4EDEB"));
            }
        }
    }
    public void removeItem(int position)
    {
        pills.remove(position);
        notifyDataSetChanged();
    }

    public static void changeToNextDay(){
        person=Storage.importFromJSON(myContext);

        if(person!=null){
            for(int i=0;i<person.getPills().size();i++) {
                person.getPills().get(i).setNumberOfDays(person.getPills().get(i).getNumberOfDays()-1);
                if(person.getPills().get(i).getNumberOfDays()<=0){
                    person.getPills().remove(person.getPills().get(i));
                }
                else{
                    person.getPills().get(i).setStatus(0);
                }
            }
            Storage.exportToJSON(myContext,person);



        }
    }
}


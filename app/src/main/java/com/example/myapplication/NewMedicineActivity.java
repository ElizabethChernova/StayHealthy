package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class NewMedicineActivity extends AppCompatActivity {

    private Button add;
    private Spinner spinner;
    private String[] dependency = {"До іжі", "Під час іжі", "Після іжі", "До сну", "Після сну", "Немає залежності"};

    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_medicine);

        TimePicker timePicker = this.findViewById(R.id.timePickerEatPills);
        timePicker.setIs24HourView(true);

        add = findViewById(R.id.addButton);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // додаємо ліки у списор
            }
        });

        // адаптер
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dependency);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        layout = findViewById(R.id.newDependency);
        spinner = findViewById(R.id.spinnerDependency);
        spinner.setAdapter(adapter);
        spinner.setSelection(5);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                if(position==0 || position==2 || position==3|| position==4)
                {
                    layout.setVisibility(View.VISIBLE);
                }
                if(position==1 || position==5)
                {
                    layout.setVisibility(View.GONE);
                }
                TextView timeTExt= findViewById(R.id.eating_time);
                if(position==0 || position==1  || position==2 )
                {
                    timeTExt.setText("Час прийому іжі");
                }
                if(position==3)
                {
                    timeTExt.setText("Час засинання");
                }
                if(position==4)
                {
                    timeTExt.setText("Час прокидання");
                }
                if(position==5)
                {
                    timeTExt.setText("Час прийому");
                }
           }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }
//save new medical
    public void saveChanges(View view) {
        if(findViewById(R.id.edit_med_name)!=null && !findViewById(R.id.edit_med_name).equals("")
                && findViewById(R.id.edit_weigh)!=null && !findViewById(R.id.edit_weigh).equals("")
                && findViewById(R.id.radio_group_sex)!=null){

        }
    else{
        Toast.makeText(this, "Заповніть, будь ласка, всі поля", Toast.LENGTH_LONG).show();
    }
    }
}
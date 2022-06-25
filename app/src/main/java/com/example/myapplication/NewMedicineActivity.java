package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.google.android.material.navigation.NavigationView;

public class NewMedicineActivity extends AppCompatActivity {

    private Button add;
    private Spinner spinner;
    private String[] dependency = {"До іжі", "Під час іжі", "Після іжі", "До сну", "Після сну", "Немає залежності"};
private int dependencyOnFood;
    private LinearLayout layout;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_medicine);

        TimePicker timePicker = this.findViewById(R.id.timePickerEatPills);
        timePicker.setIs24HourView(true);
        TimePicker timePicker2=this.findViewById(R.id.timePickereating);
        dependencyOnFood=(timePicker2.getHour())*60+timePicker2.getMinute();
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
           }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }
}
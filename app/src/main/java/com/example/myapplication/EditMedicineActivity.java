package com.example.myapplication;

import static com.example.myapplication.MainActivity.info;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.entities.Pill;

import java.util.ArrayList;

public class EditMedicineActivity extends AppCompatActivity {

    private EditText name, dose, comment, dependencyTime;
    private NumberPicker times, numberOfDays;
    private RadioGroup alarmType;
    private RadioButton notification, alarm;
    private TimePicker timeToPills;
    private Button add;
    private Spinner spinner;
    private String[] dependency = {"До їжі", "Під час їжі", "Після іжі", "До сну", "Після сну", "Немає залежності"};
    Pill newPill;
    private LinearLayout layout;
    private LinearLayout timeLayout;

    private LinearLayout layoutWithTimePickers;
    private ArrayList<View> arrayListOfTimePicker=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_medicine);

        Bundle extras = this.getIntent().getExtras();


        name = (EditText) findViewById(R.id.edit_med_name);
        name.setText(extras.getString("name"));

        dose = (EditText) findViewById(R.id.edit_dose);
        dose.setText(extras.getString("dose"));

        times = (NumberPicker) findViewById(R.id.edit_times);
        times.setMaxValue(20);
        times.setMinValue(1);
        times.setValue(Integer.parseInt(extras.getString("times")));

        numberOfDays= (NumberPicker) findViewById(R.id.edit_number_of_days);
        numberOfDays.setMaxValue(365);
        numberOfDays.setMinValue(1);
        numberOfDays.setValue(Integer.parseInt(extras.getString("numberOfDays")));

        dependencyTime=(EditText) findViewById(R.id.edit_minute);
        dependencyTime.setText(extras.getString("dependencyTime"));

        alarmType = (RadioGroup) findViewById(R.id.radio_group_alarmType);
        alarm = (RadioButton) findViewById(R.id.radio_button_alarm);
        notification = (RadioButton) findViewById(R.id.radio_button_notification);
        if(extras.getString("alarmNot").equals("A"))
        {
            alarm.setChecked(true);
        }else{
            notification.setChecked(true);
        }

        comment=(EditText) findViewById(R.id.editTextСomment);
        comment.setText(extras.getString("comment"));

        add = findViewById(R.id.addButton);
        add.setText("Редагувати");
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // додаємо ліки у список

            }
        });

        // адаптер для залежності
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dependency);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        layout = findViewById(R.id.newDependency);
        timeLayout = findViewById(R.id.field_time);
        spinner = findViewById(R.id.spinnerDependency);
        spinner.setAdapter(adapter);
        for(int i=0; i<dependency.length; i++){
            if(dependency[i].equals(extras.getString("depend"))){
                spinner.setSelection(i);
                break;
            }
        }

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                if (position == 0 || position == 2 || position == 3 || position == 4) {
                    layout.setVisibility(View.VISIBLE);
                }
                if (position == 1 || position == 5) {
                    layout.setVisibility(View.GONE);
                }
                TextView timeTExt = findViewById(R.id.eating_time);
                if (position == 0 || position == 1 || position == 2) {
                    timeLayout.setVisibility(View.VISIBLE);
                    timeTExt.setText("Час прийому їжі");
                }
                if (position == 3) {
                    timeLayout.setVisibility(View.GONE);
                    // timeTExt.setText("Час засинання");
                }
                if (position == 4) {
                    timeLayout.setVisibility(View.GONE);
                    // timeTExt.setText("Час прокидання");
                }
                if (position == 5) {
                    timeLayout.setVisibility(View.VISIBLE);
                    timeTExt.setText("Час прийому");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }
}
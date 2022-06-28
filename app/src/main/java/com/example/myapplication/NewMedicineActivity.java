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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.entities.DaySchedule;
import com.example.entities.Person;
import com.example.entities.Pill;
import com.example.entities.Storage;
import com.google.android.material.navigation.NavigationView;

public class NewMedicineActivity extends AppCompatActivity {
Person person;
    private EditText name,dose,comment,minutesEating,times;
    private RadioGroup alarmType;
    private RadioButton notification,alarm;
    private TimePicker eatingPills;
    private Button add;
    private Spinner spinner;
    private String[] dependency = {"До іжі", "Під час іжі", "Після іжі", "До сну", "Після сну", "Немає залежності"};
Pill newPill;
    private LinearLayout layout;

    public static final String EXTRA_REPLY = "com.example.android.StayHealthy.REPLY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_medicine);

        eatingPills = this.findViewById(R.id.timePickerEatPills);
        eatingPills.setIs24HourView(true);
        name=(EditText) findViewById(R.id.edit_med_name);
        dose=(EditText) findViewById(R.id.edit_dose);
        comment=(EditText) findViewById(R.id.editTextСomment);
        times=(EditText) findViewById(R.id.edit_times);
        alarmType=(RadioGroup) findViewById(R.id.radio_group_alarmType);
        alarm=(RadioButton) findViewById(R.id.radio_button_alarm);
        notification=(RadioButton) findViewById(R.id.radio_button_notification);
        eatingPills=(TimePicker) findViewById(R.id.timePickerEatPills);

        // адаптер
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dependency);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        layout = findViewById(R.id.newDependency);
        spinner = findViewById(R.id.spinnerDependency);
        spinner.setAdapter(adapter);
        spinner.setSelection(5);

        add = findViewById(R.id.addButton);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // додаємо ліки у список
                saveNewMedicine(view);

                Intent replyIntent = new Intent();
                String info[] = new String[4];

                info[0]= String.valueOf(name.getText());
                info[1]= eatingPills.toString();
                info[2]= String.valueOf(spinner.getSelectedItem());
                info[3]= String.valueOf(alarmType.getCheckedRadioButtonId());

                replyIntent.putExtra(EXTRA_REPLY, info);
                setResult(RESULT_OK, replyIntent);
                finish();
            }
        });
        open();

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

    private void open() {
        person = Storage.importFromJSON(this);
    }

    private void saveNewMedicine(View view) {
        String nameGetString=name.getText().toString();
        String doseString=dose.getText().toString();
String timesString=times.getText().toString();
        if (timesString.matches("")||((alarmType.getCheckedRadioButtonId() != R.id.radio_button_alarm) &&alarmType.getCheckedRadioButtonId() != R.id.radio_button_notification)||doseString.matches("")||nameGetString.matches("")){
            Toast.makeText(this, "You did not enter everything", Toast.LENGTH_SHORT).show();

        }
        //todo check if dependence is on eating, there should be minetus(you should always enter minutes, if dependency on eating)
        else{
newPill= new Pill();
            newPill.setName(name.getText().toString());

            newPill.setDose(Double.parseDouble(dose.getText().toString()));
            newPill.setTimesPerDay(Integer.parseInt(times.getText().toString()));


            if (alarmType.getCheckedRadioButtonId() == R.id.radio_button_notification) newPill.setAlarmType('A');
            else if (alarmType.getCheckedRadioButtonId() == R.id.radio_button_alarm) newPill.setAlarmType('N');
            person = Storage.importFromJSON(this);
            if(person!=null){

    person.addPill(newPill);
                Toast.makeText(this, "Додали пігулку", Toast.LENGTH_SHORT).show();

        }else{
                Toast.makeText(this, "PLease make your personal profile at first", Toast.LENGTH_SHORT).show();

            }
        }
    }

}
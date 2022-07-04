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
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.entities.DaySchedule;
import com.example.entities.Person;
import com.example.entities.Storage;
import com.google.android.material.navigation.NavigationView;

import java.time.LocalDateTime;
import java.time.OffsetTime;

public class ProfileActivity extends AppCompatActivity {

    private EditText name, weight, height, age;
    private RadioGroup sex;
    private RadioButton male, female;
    private TimePicker awake, goToSleep;
    private Button save;
    private boolean firstSetUp;
    Person person;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        TimePicker timePicker = this.findViewById(R.id.timePickerAwakeTime);
        timePicker.setIs24HourView(true);
        timePicker = this.findViewById(R.id.timePickerSleepingTime);
        timePicker.setIs24HourView(true);

        drawerLayout = (DrawerLayout) findViewById(R.id.dl);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.Open, R.string.Close);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        //set connection with fields
        name = (EditText) findViewById(R.id.personName);
        weight = (EditText) findViewById(R.id.editWeigh);
        height = (EditText) findViewById(R.id.editHeight);
        age = (EditText) findViewById(R.id.editAge);
        sex = (RadioGroup) findViewById(R.id.radio_group_sex);
        male = (RadioButton) findViewById(R.id.radio_button_male);
        female = (RadioButton) findViewById(R.id.radio_button_female);
        awake = (TimePicker) findViewById(R.id.timePickerAwakeTime);
        goToSleep = (TimePicker) findViewById(R.id.timePickerSleepingTime);
        save = (Button) findViewById(R.id.save_changes);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveChanges(view);
            }
        });
        open();


        NavigationView nav_view = (NavigationView) findViewById(R.id.nav_view);
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.nav_main) {
                    //Toast.makeText(MainActivity.this,"Main",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ProfileActivity.this, MainActivity.class));
                } else if (id == R.id.nav_profile) {
                    //Toast.makeText(MainActivity.this,"Help",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ProfileActivity.this, ProfileActivity.class));
                } else if (id == R.id.nav_water) {
                    //Toast.makeText(MainActivity.this,"Broadcast",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ProfileActivity.this, WaterBalanceActivity.class));
                } else if (id == R.id.nav_feeling) {
                    //Toast.makeText(MainActivity.this,"Background",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ProfileActivity.this, FeelingActivity.class));
                } else if (id == R.id.nav_help) {
                    //Toast.makeText(MainActivity.this,"Background",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ProfileActivity.this, InstructionActivity.class));
                }
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return actionBarDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }


    public void saveChanges(View view) {
        String nameGetString = name.getText().toString();
        String weightString = weight.getText().toString();
        String ageString = age.getText().toString();
        if (((sex.getCheckedRadioButtonId() != R.id.radio_button_male) && sex.getCheckedRadioButtonId() != R.id.radio_button_female) || ageString.matches("") || nameGetString.matches("") || weightString.matches("")) {
            Toast.makeText(this, "You did not enter everything", Toast.LENGTH_SHORT).show();

        } else if (Double.parseDouble(weightString) > 400 || Double.parseDouble(weightString) < 1) {
            Toast.makeText(this, "Please enter correct weight", Toast.LENGTH_SHORT).show();
        } else if (Double.parseDouble(ageString) > 150 || Double.parseDouble(weightString) < 0) {
            Toast.makeText(this, "Please enter correct age", Toast.LENGTH_SHORT).show();
        } else {

            person.setName(name.getText().toString());

            person.setWeight(Double.parseDouble(weight.getText().toString()));
            person.setHeight(Double.parseDouble(height.getText().toString()));
            person.setAge(Integer.parseInt(age.getText().toString()));

            if (sex.getCheckedRadioButtonId() == R.id.radio_button_male) person.setSex('Ч');
            else if (sex.getCheckedRadioButtonId() == R.id.radio_button_female) person.setSex('Ж');


            DaySchedule ds=new DaySchedule(awake.getHour(),awake.getMinute(),goToSleep.getHour(),goToSleep.getMinute());
            person.setDaySchedule(ds);

            boolean result = Storage.exportToJSON(this, person);
            if (result) {
                Toast.makeText(this, "Зміни збережено", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Помилка збереження змін", Toast.LENGTH_LONG).show();
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void open() {
        person = Storage.importFromJSON(this);
        if (person != null) {
            name.setText(person.getName());
            weight.setText(person.getWeight() + "");
            height.setText(person.getHeight() + "");
            age.setText(person.getAge() + "");
            if (person.getSex() == 'Ч') male.setChecked(true);
            else if (person.getSex() == 'Ж') female.setChecked(true);
            awake.setHour(person.getDaySchedule().wakingUpH);
            awake.setMinute(person.getDaySchedule().wakingUpM);
            goToSleep.setHour(person.getDaySchedule().goingToSleepH);
            goToSleep.setMinute(person.getDaySchedule().goingToSleepM);
        } else {
            person = new Person();
            firstSetUp = true;
        }
    }
}


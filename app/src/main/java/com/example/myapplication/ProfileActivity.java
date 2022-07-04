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
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.entities.Person;
import com.example.entities.Storage;
import com.example.entities.Time;
import com.google.android.material.navigation.NavigationView;

public class ProfileActivity extends AppCompatActivity {

    private EditText name, weight;
    private NumberPicker height, age;
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
        height =  findViewById(R.id.editHeight);
        height.setMaxValue(230);
        height.setMinValue(120);
        age =  findViewById(R.id.editAge);
        age.setMaxValue(150);
        age.setMinValue(1);
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
        if (((sex.getCheckedRadioButtonId() != R.id.radio_button_male) && sex.getCheckedRadioButtonId() != R.id.radio_button_female) || nameGetString.matches("") || weightString.matches("")) {
            Toast.makeText(this, "You did not enter everything", Toast.LENGTH_SHORT).show();

        } else if (Double.parseDouble(weightString) > 400 || Double.parseDouble(weightString) < 1) {
            Toast.makeText(this, "Please enter correct weight", Toast.LENGTH_SHORT).show();
        } else {

            person.setName(name.getText().toString());

            person.setWeight(Double.parseDouble(weight.getText().toString()));
            person.setHeight(height.getValue());
            person.setAge(age.getValue());

            if (sex.getCheckedRadioButtonId() == R.id.radio_button_male) person.setSex('Ч');
            else if (sex.getCheckedRadioButtonId() == R.id.radio_button_female) person.setSex('Ж');


            person.setStart(new Time(awake.getHour(),awake.getMinute()));
            person.setEnd(new Time(goToSleep.getHour(),goToSleep.getMinute()));

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
            height.setValue((int) person.getHeight());
            age.setValue((int) person.getAge());

            if (person.getSex() == 'Ч') male.setChecked(true);
            else if (person.getSex() == 'Ж') female.setChecked(true);
            awake.setHour(person.getStart().getHours());
            awake.setMinute(person.getStart().getMinutes());
            goToSleep.setHour(person.getEnd().getHours());
            goToSleep.setMinute(person.getEnd().getMinutes());
        } else {
            person = new Person();
            firstSetUp = true;
        }
    }
}


package com.example.myapplication;

import static com.example.myapplication.MainActivity.info;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
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
import android.widget.ToggleButton;

import com.example.entities.Person;
import com.example.entities.Pill;
import com.example.entities.Storage;
import com.example.entities.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class EditMedicineActivity extends AppCompatActivity {

    private EditText name, dose, comment, dependencyTime;
    private NumberPicker times, numberOfDays;
    private ToggleButton toggleButton;
    private TimePicker timeToPills;
    private Button add;
    private Spinner spinner;
    private String[] dependency = {"До їжі", "Під час їжі", "Після іжі", "До сну", "Після сну", "Немає залежності"};
    Pill newPill;
    private LinearLayout layout;
    private LinearLayout timeLayout;

    private LinearLayout layoutWithTimePickers;
    private ArrayList<View> arrayListOfTimePicker=new ArrayList<>();
    private ArrayList<Time> arrayListOfTimes=new ArrayList<>();
    public static final String EXTRA_REPLY = "com.example.android.StayHealthy.REPLY";
    int number = 1;
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

        arrayListOfTimes = (ArrayList<Time>) getIntent().getSerializableExtra("arrayListTime");
        layoutWithTimePickers = findViewById(R.id.timePickers);
        layoutWithTimePickers.removeAllViewsInLayout();
        for(int i=0; i<arrayListOfTimes.size(); i++)
        {
            TimePicker timePicker= new TimePicker(this);
            timePicker.setIs24HourView(true);
            timePicker.setHour(arrayListOfTimes.get(i).getHours());
            timePicker.setMinute(arrayListOfTimes.get(i).getMinutes());
            arrayListOfTimePicker.add(timePicker);
        }
        for(int i=0; i<arrayListOfTimePicker.size(); i++) {
            layoutWithTimePickers.addView(arrayListOfTimePicker.get(i));
        }
        LinearLayout layoutWithTimePickersAndText = findViewById(R.id.field_time);
        layoutWithTimePickersAndText.setLayoutParams( new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));


        toggleButton =  findViewById(R.id.alarmToggle);
        if(extras.getString("alarmNot").equals("A"))
        {
            toggleButton.setChecked(true);
        }else{
            toggleButton.setChecked(false);
        }

        comment=(EditText) findViewById(R.id.editTextСomment);
        comment.setText(extras.getString("comment"));

        add = findViewById(R.id.addButton);
        add.setText("Редагувати");
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // редагуємо ліки з списку
                //saveEditMedicine(view);

                newPill = new Pill();
                newPill.setName(name.getText().toString());

                newPill.setDose(Double.parseDouble(dose.getText().toString()));
                newPill.setTimesPerDay(times.getValue());
                newPill.setNumberOfDays(numberOfDays.getValue());
                newPill.setDependency(spinner.getSelectedItem().toString());
                newPill.setComment(comment.getText().toString());
                if (!dependencyTime.getText().toString().equals(""))
                    newPill.setDependencyTime(Integer.parseInt(dependencyTime.getText().toString()));
                else
                    newPill.setDependencyTime(0);

                for (View timePicker : arrayListOfTimePicker) {
                    newPill.addUserTime(new Time(((TimePicker) timePicker).getHour(), ((TimePicker) timePicker).getMinute()));
                }
                if(toggleButton.isChecked())
                    newPill.setAlarmType('A');
                else
                    newPill.setAlarmType('N');
//                person.getPills().get(positionInList).setTimesPerDay(times.getValue());
//                person.getPills().get(positionInList).setComment(comment.getText().toString());
//                if (!dependencyTime.getText().toString().equals(""))
//                    person.getPills().get(positionInList).setDependencyTime(Integer.parseInt(dependencyTime.getText().toString()));
//                else
//                    person.getPills().get(positionInList).setDependencyTime(0);
//
//                person.getPills().get(positionInList).setUserTimes(new ArrayList<Time>());
//                for (View timePicker : arrayListOfTimePicker) {
//                    person.getPills().get(positionInList).addUserTime(new Time(((TimePicker) timePicker).getHour(), ((TimePicker) timePicker).getMinute()));
//                }
//
//                if (alarmType.getCheckedRadioButtonId() == R.id.radio_button_notification)
//                    person.getPills().get(positionInList).setAlarmType('A');
//                else if (alarmType.getCheckedRadioButtonId() == R.id.radio_button_alarm)
//                    person.getPills().get(positionInList).setAlarmType('N');

                Person person= Storage.importFromJSON(view.getContext());
                if(person!=null) {
                    int positionInList = extras.getInt("position");
                    newPill.countTimeSlots();
                    person.getPills().set(positionInList, newPill);
                    Collections.sort(person.getPills(), new Comparator<Pill>() {
                        @Override
                        public int compare(Pill pill1, Pill pill2) {
                            return pill1.getUserTimes().get(0).compareTo(pill2.getUserTimes().get(0));
                        }
                    });
                    Storage.exportToJSON(view.getContext(), person);
                }


                Intent replyIntent = new Intent();
                replyIntent.putExtra(EXTRA_REPLY, info);
                setResult(RESULT_OK, replyIntent);
                finish();
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

        times.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int oldVal, int newVal) {
                number = newVal;

            }
        });

        times.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //Log.e("TAG", event.getX()+" "+event.getY());

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN://0
                        Log.e("TAG", "LinearLayout onTouch and hold");
                        break;
                    case MotionEvent.ACTION_UP://1
                        Log.e("ТЕГ", "LinearLayout onTouch поднять вверх");
                        layoutWithTimePickers.removeAllViewsInLayout();
                        if (arrayListOfTimePicker.size() <= number) {
                            for (int i = arrayListOfTimePicker.size(); i < number; i++) {
                                TimePicker timePicker = new TimePicker(EditMedicineActivity.this);
                                timePicker.setIs24HourView(true);
//                    timePicker= findViewById(R.id.timePickerEatPills);
                                arrayListOfTimePicker.add(timePicker);
                            }
                        }else{
                            while(arrayListOfTimePicker.size()>number) {
                                arrayListOfTimePicker.remove(arrayListOfTimePicker.size()-1);
                            }
                        }
                        for (int i = 0; i < arrayListOfTimePicker.size(); i++) {
                            layoutWithTimePickers.addView(arrayListOfTimePicker.get(i));
                        }
                        layoutWithTimePickersAndText.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

                        break;
                    case MotionEvent.ACTION_MOVE://2
                        Log.e("TAG", "LinearLayout onTouch Mobile");
                        break;
                }
                return false;
            }
        });

    }
}
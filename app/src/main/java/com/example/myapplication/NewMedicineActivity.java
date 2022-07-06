package com.example.myapplication;

import static com.example.myapplication.MainActivity.info;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Build;
import android.util.Log;
import android.view.MotionEvent;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.entities.Person;
import com.example.entities.Pill;
import com.example.entities.Storage;
import com.example.entities.Time;

import java.time.OffsetTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;
import java.util.stream.Stream;

public class NewMedicineActivity extends AppCompatActivity {


    // Notification ID.
    private static final int NOTIFICATION_ID = 0;
    // Notification channel ID.
    private static final String PRIMARY_CHANNEL_ID =
            "primary_notification_channel";
    private NotificationManager mNotificationManager;


    Person person;
    private EditText name, dose, comment, dependencyTime;
    private NumberPicker times, numberOfDays;
    private ToggleButton toggleButton;
    //private TimePicker timeToPills;
    private Button add;
    private Spinner spinner;
    private String[] dependency = {"До їжі", "Під час їжі", "Після їжі", "До сну", "Після сну", "Немає залежності"};
    Pill newPill;
    private LinearLayout layout;
    private LinearLayout timeLayout;
    ToggleButton alarmToggle;
    private LinearLayout layoutWithTimePickers;
    private ArrayList<Button> arrayListOfTimePicker = new ArrayList<>();

    public static final String EXTRA_REPLY = "com.example.android.StayHealthy.REPLY";

    int number = 1;
    int hour,minute;

    private Button timeButton;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTheme(R.style.TimePicker);
        setContentView(R.layout.activity_new_medicine);

//        timeToPills = this.findViewById(R.id.timePickerEatPills);
//        timeToPills.setIs24HourView(true);
        LinearLayout layoutWithTimePickersAndText = findViewById(R.id.field_time);
        layoutWithTimePickers = findViewById(R.id.timePickers);
        layoutWithTimePickers.removeAllViewsInLayout();
        timeButton  = new Button(NewMedicineActivity.this);
        if(OffsetTime.now().getMinute()<10)
            timeButton.setText(OffsetTime.now().getHour()+":0"+OffsetTime.now().getMinute());
        else
            timeButton.setText(OffsetTime.now().getHour()+":"+OffsetTime.now().getMinute());

        timeButton.setBackgroundColor(Color.parseColor("#C4EDEB"));
        timeButton.setTextColor(Color.parseColor("#00857D"));
        timeButton.setHeight(35);
        arrayListOfTimePicker.add(timeButton);
        layoutWithTimePickers.addView(arrayListOfTimePicker.get(0));
        layoutWithTimePickersAndText.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        name = (EditText) findViewById(R.id.edit_med_name);
        dose = (EditText) findViewById(R.id.edit_dose);
        comment = (EditText) findViewById(R.id.editTextСomment);
        times = findViewById(R.id.edit_times);
        times.setMaxValue(20);
        times.setMinValue(1);

        toggleButton =  findViewById(R.id.alarmToggle);

        toggleButton =  findViewById(R.id.alarmToggle);
        timeLayout = findViewById(R.id.field_time);
        numberOfDays=  findViewById(R.id.edit_number_of_days);
        numberOfDays.setMaxValue(365);
        numberOfDays.setMinValue(1);
        comment = (EditText) findViewById(R.id.editTextСomment);
        dependencyTime = (EditText) findViewById(R.id.edit_minute);

        //     layoutWithTimePickers.addChildrenForAccessibility(arrayListOfTimePicker);


        // адаптер
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dependency);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        layout = findViewById(R.id.newDependency);
        spinner = findViewById(R.id.spinnerDependency);
        spinner.setAdapter(adapter);
        spinner.setSelection(5);




        mNotificationManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);

        alarmToggle = findViewById(R.id.alarmToggle);

        // Set up the Notification Broadcast Intent.
        Intent notifyIntent = new Intent(this, AlarmReceiver.class);

        boolean alarmUp = (PendingIntent.getBroadcast(this, NOTIFICATION_ID,
                notifyIntent, PendingIntent.FLAG_NO_CREATE) != null);
       // alarm.setChecked(alarmUp);

        final PendingIntent notifyPendingIntent = PendingIntent.getBroadcast
                (this, NOTIFICATION_ID, notifyIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT);

        final AlarmManager alarmManager = (AlarmManager) getSystemService
                (ALARM_SERVICE);

        // Set the click listener for the toggle button.
        alarmToggle.setOnCheckedChangeListener
                (new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged
                            (CompoundButton buttonView, boolean isChecked) {


                        if (checkIfSomethingIsMissing()) {
                            Toast.makeText(NewMedicineActivity.this, "You did not enter everything", Toast.LENGTH_SHORT).show();

                        }else{
                            saveNewMedicine();
                            String toastMessage;
                            if (isChecked) {
                                long repeatInterval = AlarmManager.INTERVAL_FIFTEEN_MINUTES;
                                Calendar alarmStartTime = Calendar.getInstance();
                                alarmStartTime.setTimeInMillis(System.currentTimeMillis());
                                Calendar now = Calendar.getInstance();
                                ArrayList<Pill> pills=Storage.importFromJSON(NewMedicineActivity.this).getPills();
                                for(int i=0; i<pills.size();i++){
                                    int hours= pills.get(pills.size()-1).getTimes().get(i).getHours();
                                    int minutes= pills.get(pills.size()-1).getTimes().get(i).getMinutes();
                                    alarmStartTime.set(Calendar.HOUR_OF_DAY, hours);
                                    alarmStartTime.set(Calendar.MINUTE, minutes );
                                    alarmStartTime.set(Calendar.SECOND, 0);

                                    long triggerTime = alarmStartTime.getTimeInMillis();
                                    //SystemClock.elapsedRealtime() + repeatInterval;

                                    // If the Toggle is turned on, set the repeating alarm with
                                    // a 15 minute interval.
                                    if (alarmManager != null) {
                                    //    long thirtySecondsFromNow = System.currentTimeMillis() + 50 * 1000;
                                        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, triggerTime, AlarmManager.INTERVAL_DAY, notifyPendingIntent);
                                }


                                }
                                // Set the toast message for the "on" case.
                                toastMessage = getString(R.string.alarm_on_toast);

                            } else {
                                // Cancel notification if the alarm is turned off.
                                mNotificationManager.cancelAll();

                                if (alarmManager != null) {
                                    alarmManager.cancel(notifyPendingIntent);
                                }
                                // Set the toast message for the "off" case.
                                toastMessage = getString(R.string.alarm_off_toast);

                            }

                            // Show a toast to say the alarm is turned on or off.
                            Toast.makeText(NewMedicineActivity.this, toastMessage,
                                    Toast.LENGTH_SHORT).show();
                        }}
                });

        // Create the notification channel.
        createNotificationChannel();




        add = findViewById(R.id.addButton);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // додаємо ліки у список
                if(!toggleButton.isChecked()){
                saveNewMedicine();}

                Intent replyIntent = new Intent();
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
            @RequiresApi(api = Build.VERSION_CODES.O)
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
                                Button timePickerButton = new Button(NewMedicineActivity.this);
                                if(OffsetTime.now().getMinute()<10)
                                    timePickerButton.setText(OffsetTime.now().getHour()+":0"+OffsetTime.now().getMinute());
                                else
                                    timePickerButton.setText(OffsetTime.now().getHour()+":"+OffsetTime.now().getMinute());
                                timePickerButton.setBackgroundColor(Color.parseColor("#C4EDEB"));
                                timePickerButton.setTextColor(Color.parseColor("#00857D"));
                                timePickerButton.setHeight(35);
                              //  timePickerButton.setPadding(0,8,0,0);
                                timePickerButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        // додаємо ліки у список

                                        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
                                            @Override
                                            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                                                hour = i;
                                                minute=i1;
                                                timePickerButton.setText(String.format(Locale.getDefault(), "%02d:%02d", i, i1));
                                            }
                                        };
                                        TimePickerDialog timePickerDialog = new TimePickerDialog(NewMedicineActivity.this, onTimeSetListener, hour, minute, true);
                                        timePickerDialog.show();
                                    }
                                });
                                arrayListOfTimePicker.add(timePickerButton);
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

        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // додаємо ліки у список

                TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        hour = i;
                        minute=i1;
                        timeButton.setText(String.format(Locale.getDefault(), "%02d:%02d", i, i1));
                    }
                };
                TimePickerDialog timePickerDialog = new TimePickerDialog(NewMedicineActivity.this, onTimeSetListener, hour, minute, true);
                timePickerDialog.show();
            }
        });
    }

    private void open() {
        person = Storage.importFromJSON(this);
    }
    private boolean checkIfSomethingIsMissing(){
        String nameGetString = name.getText().toString();
        String doseString = dose.getText().toString();

        if (doseString.matches("") || nameGetString.matches("")) {
            return true;
        }
        return false;
    }
    private void saveNewMedicine( ) {

        if (checkIfSomethingIsMissing()) {
        
            Toast.makeText(this, "You did not enter everything", Toast.LENGTH_SHORT).show();

        }
        //todo check if dependence is on eating, there should be minetus(you should always enter minutes, if dependency on eating)
        else {
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

            for (Button timePickerButton : arrayListOfTimePicker) {
                String str= (String) timePickerButton.getText();
                String[] arr = str.split(":");
                newPill.addUserTime(new Time(Integer.parseInt( arr[0]), Integer.parseInt( arr[1])));
            }

            if(toggleButton.isChecked())
                newPill.setAlarmType('A');
            else
                newPill.setAlarmType('N');
            person = Storage.importFromJSON(this);
            if (person != null) {
                newPill.countTimeSlots(person.getEnd());
                person.addPill(newPill);
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

                Storage.exportToJSON(this, person);
                Toast.makeText(this, "Збережено налаштування", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(this, "PLease make your personal profile at first", Toast.LENGTH_SHORT).show();

            }
        }
    }


    /**
     * Creates a Notification channel, for OREO and higher.
     */
    public void createNotificationChannel() {

        // Create a notification manager object.
        mNotificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // Notification channels are only available in OREO and higher.
        // So, add a check on SDK version.
        if (android.os.Build.VERSION.SDK_INT >=
                android.os.Build.VERSION_CODES.O) {

            // Create the NotificationChannel with all the parameters.
            NotificationChannel notificationChannel = new NotificationChannel
                    (PRIMARY_CHANNEL_ID,
                            "Stand up notification",
                            NotificationManager.IMPORTANCE_HIGH);

            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("Notifies every 15 minutes to " +
                    "stand up and walk");
            mNotificationManager.createNotificationChannel(notificationChannel);
        }
    }


}
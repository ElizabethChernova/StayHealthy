package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.entities.Data;
import com.example.entities.Note;
import com.example.entities.Person;
import com.example.entities.Pill;
import com.example.entities.Storage;
import com.example.entities.Time;
import com.google.android.material.navigation.NavigationView;

import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.util.ArrayList;
import java.util.List;

public class FeelingActivity extends AppCompatActivity {

    private ImageButton temperature;
    private ImageButton cough;
    private ImageButton appetite;
    private ImageButton stomach;
    private ImageButton mood;
    private RatingBar ratingBar;
    private EditText comment;

    private Button saveButton;

    private ImageView tick;

    private boolean temperatureClicked=false;
    private boolean coughClicked=false;
    private boolean appetiteClicked=false;
    private boolean stomachClicked=false;
    private boolean moodClicked=false;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    private RecyclerView recyclerView;
    private  SymptomsListAdapter adapter;
    private List<Note> notesFromJson = new ArrayList<>();
    private Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feeling);
        drawerLayout=(DrawerLayout) findViewById(R.id.dl);
        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.Open,R.string.Close);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        temperature = findViewById(R.id.imageButtonTemper);
        cough = findViewById(R.id.imageButtonCough);
        appetite = findViewById(R.id.imageButtonEating);
        stomach = findViewById(R.id.imageButtonStomach);
        mood = findViewById(R.id.imageButtonMood);

        ratingBar=(RatingBar) findViewById(R.id.ratingBar);
        comment=(EditText) findViewById(R.id.editTextFeelingsComment);

        person= Storage.importFromJSON(FeelingActivity.this);
        if(person!=null){
            notesFromJson=person.getNotes();
        }

        recyclerView = findViewById(R.id.recyclerview);
        adapter = new SymptomsListAdapter(notesFromJson);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(FeelingActivity.this));

        saveButton = findViewById(R.id.saveSymptomData);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveNewNote(view);
                person= Storage.importFromJSON(FeelingActivity.this);

                if(person!=null){
                    notesFromJson=person.getNotes();
                }

                recyclerView = findViewById(R.id.recyclerview);
                adapter = new SymptomsListAdapter(notesFromJson);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(FeelingActivity.this));
            }
        });
        temperature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tick=findViewById(R.id.tick1);
                if(!temperatureClicked) {
                    tick.setVisibility(View.VISIBLE);
                    temperatureClicked=true;
                }else{
                    tick.setVisibility(View.INVISIBLE);
                    temperatureClicked=false;
                }
            }
        });

        cough.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tick=findViewById(R.id.tick2);
                if(!coughClicked) {
                    tick.setVisibility(View.VISIBLE);
                    coughClicked=true;
                }else{
                    tick.setVisibility(View.INVISIBLE);
                    coughClicked=false;
                }
            }
        });

        appetite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tick=findViewById(R.id.tick3);
                if(!appetiteClicked) {
                    tick.setVisibility(View.VISIBLE);
                    appetiteClicked=true;
                }else{
                    tick.setVisibility(View.INVISIBLE);
                    appetiteClicked=false;
                }
            }
        });

        stomach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tick=findViewById(R.id.tick4);
                if(!stomachClicked) {
                    tick.setVisibility(View.VISIBLE);
                    stomachClicked=true;
                }else{
                    tick.setVisibility(View.INVISIBLE);
                    stomachClicked=false;
                }
            }
        });

        mood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tick=findViewById(R.id.tick5);
                if(!moodClicked) {
                    tick.setVisibility(View.VISIBLE);
                    moodClicked=true;
                }else{
                    tick.setVisibility(View.INVISIBLE);
                    moodClicked=false;
                }
            }
        });


        NavigationView nav_view=(NavigationView) findViewById(R.id.nav_view);
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                if(id==R.id.nav_main){
                    //Toast.makeText(MainActivity.this,"Main",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(FeelingActivity.this, MainActivity.class));
                }else if(id==R.id.nav_profile){
                    //Toast.makeText(MainActivity.this,"Help",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(FeelingActivity.this, ProfileActivity.class));
                }else if(id==R.id.nav_water){
                    //Toast.makeText(MainActivity.this,"Broadcast",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(FeelingActivity.this, WaterBalanceActivity.class));
                }else if(id==R.id.nav_feeling){
                    //Toast.makeText(MainActivity.this,"Background",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(FeelingActivity.this, FeelingActivity.class));
                }else if(id==R.id.nav_help){
                    //Toast.makeText(MainActivity.this,"Background",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(FeelingActivity.this, InstructionActivity.class));
                }
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return actionBarDrawerToggle.onOptionsItemSelected(item)||super.onOptionsItemSelected(item);
    }

    private void saveNewNote(View view) {
        person=Storage.importFromJSON(this);
        Note myNote=new Note();
        myNote.setData(new Data(OffsetDateTime.now().getDayOfMonth(),OffsetDateTime.now().getMonthValue(),OffsetDateTime.now().getYear()));
        myNote.setTime(new Time(OffsetTime.now().getHour(), OffsetTime.now().getMinute()));
        myNote.setTemperature(temperatureClicked);
        myNote.setCaught(coughClicked);
        myNote.setBadAppetite(appetiteClicked);
        myNote.setBadEating(stomachClicked);
        myNote.setBadMood(moodClicked);
        myNote.setRate(ratingBar.getRating());
        myNote.setComment(comment.getText().toString());

        if(person!=null){
            person.addNote(myNote);
            Storage.exportToJSON(this, person);
        }
        else showDialog(view);
    }
    public void showDialog(View view) {
        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setTitle("Некоректні дії");
        ad.setMessage("Перед додаванням нотаток заповніть профіль користувача");
        ad.setIcon(R.drawable.ic_warning);
        ad.setPositiveButton("Зрозуміло", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        ad.create().show();
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case 122:

                adapter.removeItem(item.getGroupId());
                person=Storage.importFromJSON(this);
                if(person!=null) {
                    person.getNotes().remove(item.getGroupId());
                    Storage.exportToJSON(this, person);
                }
                return false;
        }
        return super.onContextItemSelected(item);
    }


}
package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.entities.Note;
import com.example.entities.Person;
import com.example.entities.Pill;
import com.example.entities.Storage;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class FeelingActivity extends AppCompatActivity {

    private ImageButton temperature;
    private ImageButton cough;
    private ImageButton appetite;
    private ImageButton stomach;
    private ImageButton mood;

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

        saveButton = findViewById(R.id.saveSymptomData);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO зберегти динні в json!

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
       //TODO add notes
    }
}
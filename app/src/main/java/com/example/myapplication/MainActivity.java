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
import android.widget.Toast;

import com.example.entities.Person;
import com.example.entities.Pill;
import com.example.entities.Storage;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    private Person person;
    private FloatingActionButton buttonPlus;
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    public static String info[]= new String[4];

    private RecyclerView recyclerView;
    private  MedicalListAdapter adapter;
    private List<Pill> pillsFromJson = new ArrayList<Pill>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout=(DrawerLayout) findViewById(R.id.dl);
        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.Open,R.string.Close);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        buttonPlus = findViewById(R.id.fab);

        person= Storage.importFromJSON(this);

        if(person!=null){
            pillsFromJson=person.getPills();
        }
//        pillsFromJson.add(new Pill("pill1", 2, 3, "d"));
//        pillsFromJson.add(new Pill("pill2", 2, 3, "d"));
        recyclerView = findViewById(R.id.recyclerview);
        adapter = new MedicalListAdapter(this, pillsFromJson);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        NavigationView nav_view=(NavigationView) findViewById(R.id.nav_view);
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                if(id==R.id.nav_main){
                    //Toast.makeText(MainActivity.this,"Main",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, MainActivity.class));
                }else if(id==R.id.nav_profile){
                    //Toast.makeText(MainActivity.this,"Help",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                }else if(id==R.id.nav_water){
                    //Toast.makeText(MainActivity.this,"Broadcast",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, WaterBalanceActivity.class));
                }else if(id==R.id.nav_feeling){
                    //Toast.makeText(MainActivity.this,"Background",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, FeelingActivity.class));
                }else if(id==R.id.nav_help){
                //Toast.makeText(MainActivity.this,"Background",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, InstructionActivity.class));
                }
                return true;
            }
        });

        buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewMedicineActivity.class);
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return actionBarDrawerToggle.onOptionsItemSelected(item)||super.onOptionsItemSelected(item);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {

            person= Storage.importFromJSON(this);

            if(person!=null){
                pillsFromJson=person.getPills();
            }
            adapter = new MedicalListAdapter(this, pillsFromJson);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

//          Pill pill =new Pill(info[0], Double.parseDouble(info[1]), Integer.parseInt(info[2]),
//            info[3], info[4], Integer.parseInt(info[5]));
//            Word word = new Word(data.getStringExtra(NewWordActivity.EXTRA_REPLY));
//            mWordViewModel.insert(word);
        } else {

        }
    }
}
package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class WaterBalanceActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private TextView mTextView;
    private ImageButton plus;
    private ImageButton minus;
    private TextView numberOfGlass;
    private TextView mlInDay;

    private int currentNumberOfGlass=0;
    //це число має розраховуватись!
    private int neededMLInDay=2000;
    private int remainingML=neededMLInDay;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_balance);
        drawerLayout=(DrawerLayout) findViewById(R.id.dl);
        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.Open,R.string.Close);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        final SeekBar seekBar = findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(this);

        mTextView = findViewById(R.id.textML);
        numberOfGlass = findViewById(R.id.numberOfGlass);
        plus = findViewById(R.id.plusButton);
        minus = findViewById(R.id.minusButton);
        mlInDay = findViewById(R.id.mlInDay);
        mlInDay.setText("На сьогодні ще потрібно випити: " + neededMLInDay+ " мл");

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentNumberOfGlass = Integer.parseInt((String) numberOfGlass.getText());
                currentNumberOfGlass++;
                numberOfGlass.setText(String.valueOf(currentNumberOfGlass));
                remainingML-= getNumber((String) mTextView.getText());
                if(remainingML>=0) {
                    mlInDay.setText("На сьогодні ще потрібно випити: " + remainingML + " мл");
                }else{
                    mlInDay.setText("Сьогодні ви випили на: " + remainingML*(-1) + " мл вище норми");
                }
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentNumberOfGlass = Integer.parseInt((String) numberOfGlass.getText());
                if(currentNumberOfGlass!=0)
                {
                    currentNumberOfGlass--;
                    numberOfGlass.setText(String.valueOf(currentNumberOfGlass));
                    remainingML+= getNumber((String) mTextView.getText());
                    mlInDay.setText("На сьогодні ще потрібно випити: " + remainingML+ " мл");
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
                    startActivity(new Intent(WaterBalanceActivity.this, MainActivity.class));
                }else if(id==R.id.nav_profile){
                    //Toast.makeText(MainActivity.this,"Help",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(WaterBalanceActivity.this, ProfileActivity.class));
                }else if(id==R.id.nav_water){
                    //Toast.makeText(MainActivity.this,"Broadcast",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(WaterBalanceActivity.this, WaterBalanceActivity.class));
                }else if(id==R.id.nav_feeling){
                    //Toast.makeText(MainActivity.this,"Background",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(WaterBalanceActivity.this, FeelingActivity.class));
                }else if(id==R.id.nav_help){
                    //Toast.makeText(MainActivity.this,"Background",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(WaterBalanceActivity.this, InstructionActivity.class));
                }
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return actionBarDrawerToggle.onOptionsItemSelected(item)||super.onOptionsItemSelected(item);
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        mTextView.setText(String.valueOf(seekBar.getProgress()) + " мл");
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    private int getNumber(String str){
        int result=0;
        for(int i=0; i<str.length(); i++)
        {
            if(str.charAt(i)>='0' && str.charAt(i)<='9')
            {
                result = result*10 + Character.getNumericValue(str.charAt(i));
            }
        }

        return result;
    }
}
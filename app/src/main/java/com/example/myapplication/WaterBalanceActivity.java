package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class WaterBalanceActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private TextView mTextView;

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

        final SeekBar seekBar = (SeekBar)findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(this);

        mTextView = (TextView)findViewById(R.id.textML);
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
}
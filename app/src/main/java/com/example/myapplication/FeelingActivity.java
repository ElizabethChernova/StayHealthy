package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class FeelingActivity extends AppCompatActivity {

    private ImageButton temperature;
    private ImageButton cough;
    private ImageButton appetite;
    private ImageButton stomach;
    private ImageButton mood;

    private ImageView tick;

    private boolean temperatureClicked=false;
    private boolean coughClicked=false;
    private boolean appetiteClicked=false;
    private boolean stomachClicked=false;
    private boolean moodClicked=false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feeling);

        temperature = findViewById(R.id.imageButtonTemper);
        cough = findViewById(R.id.imageButtonCough);
        appetite = findViewById(R.id.imageButtonEating);
        stomach = findViewById(R.id.imageButtonStomach);
        mood = findViewById(R.id.imageButtonMood);

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
    }
}
package com.example.entities;

import java.time.OffsetTime;
import java.util.ArrayList;

/**
 * This class contains basic information about each pill that user has
 */
public class Pill {
    /**
     * Name of pill
     */
    private String name;
    /**
     * Dose of pill
     */
    private double dose;
    /**
     * User can set how many times some pills should be taken
     */
    private int timesPerDay;
    /**
     * User can set dependency between pills and sleep (e.g. 2 hours before sleep)
     */
    private int dependencyOnSleep;
    /**
     * User can add dependency between pills and meal (user can add comments such as "After breakfast"
     */
    private String dependencyOnFood;
    /**
     * Set of times, when user should take pills
     */
    private ArrayList<OffsetTime> times;

    public Pill(String name, double dose, int timesPerDay, int dependencyOnSleep, String dependencyOnFood){
        this.name=name;
        this.dose=dose;
        this.timesPerDay=timesPerDay;
        this.dependencyOnSleep=dependencyOnSleep;
        this.dependencyOnFood=dependencyOnFood;
        countTimeSlots();
    }

    private void countTimeSlots(){
        times=new ArrayList<>(timesPerDay);
        //TODO коли буде готовий клас користувача додати обрахунок часових слотів для прийому ліків залежно від сну і режиму дня
    }
}

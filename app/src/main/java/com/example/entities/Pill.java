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
     * User can set how many das he needs to eat pills
     */
    private int numberOfDays;
    /**
     * User can add dependency between pills and meal (user can add comments such as "After breakfast"
     */
    private String dependency;

    /**
     * type of Alarm: A-alarm, N-noification
     */
    private char alarmType;
    /**
     * Set of times, when user should take pills
     */
    private ArrayList<OffsetTime> times;

    public Pill() {
    }

    //todo add dependancy on food nd sleep in xml(connect with this class)
    public Pill(String name, double dose, int timesPerDay, String dependency){
        this.name=name;
        this.dose=dose;
        this.timesPerDay=timesPerDay;
        this.dependency =dependency;
        countTimeSlots();
    }

    public Pill(String name, double dose, int timesPerDay, int numberOfDays, String dependency, int typeOfAlarm) {
        this.name=name;
        this.dose=dose;
        this.timesPerDay=timesPerDay;
        this.numberOfDays=numberOfDays;
        //timeForEatingPill
        this.dependency =dependency;
        if(typeOfAlarm==0)
            alarmType='N';
        else
            alarmType='A';

        countTimeSlots();
    }

    private void countTimeSlots(){
        times=new ArrayList<>(timesPerDay);
        //TODO коли буде готовий клас користувача додати обрахунок часових слотів для прийому ліків залежно від сну і режиму дня
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(char alarmType) {
        this.alarmType = alarmType;
    }
    public double getDose() {
        return dose;
    }

    public void setDose(double dose) {
        this.dose = dose;
    }

    public int getTimesPerDay() {
        return timesPerDay;
    }

    public void setTimesPerDay(int timesPerDay) {
        this.timesPerDay = timesPerDay;
    }

    public String getDependency() {
        return dependency;
    }

    public void setDependency(String dependency) {
        this.dependency = dependency;
    }

    public ArrayList<OffsetTime> getTimes() {
        return times;
    }

    public void setTimes(ArrayList<OffsetTime> times) {
        this.times = times;
    }
}

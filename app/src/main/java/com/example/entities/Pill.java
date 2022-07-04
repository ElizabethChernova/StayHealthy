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

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    /**
     * User can set how many das he needs to eat pills
     */
    private int numberOfDays;
    /**
     * User can add dependency between pills and meal (user can add comments such as "After breakfast"
     */
    private String dependency;

    public int getUserTimeH() {
        return userTimeH;
    }

    public void setUserTimeH(int userTimeH) {
        this.userTimeH = userTimeH;
    }

    public int getUserTimeM() {
        return userTimeM;
    }

    public void setUserTimeM(int userTimeM) {
        this.userTimeM = userTimeM;
    }

    private int userTimeH,userTimeM;
    private OffsetTime userTime;

    public int getDependencyTime() {
        return dependencyTime;
    }

    public void setDependencyTime(int dependencyTime) {
        this.dependencyTime = dependencyTime;
    }

    private int dependencyTime;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    private String comment;

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
//"До іжі", "Під час іжі", "Після іжі", "До сну", "Після сну", "Немає залежності"
    public void countTimeSlots(){
        times=new ArrayList<>(timesPerDay);
        OffsetTime currentUserTime=OffsetTime.of(userTimeH,userTimeM,0,0,OffsetTime.now().getOffset());
        if(dependency.equals("До іжі")){
            currentUserTime.minusMinutes(dependencyTime);
        }
        if(dependency.equals("Після іжі")){
            currentUserTime.plusMinutes(dependencyTime);
        }
        if(dependency.equals("До сну")){
            currentUserTime=OffsetTime.of(Person.getDaySchedule().goingToSleepH, Person.getDaySchedule().goingToSleepM, 0,0,OffsetTime.now().getOffset());
            currentUserTime.minusMinutes(dependencyTime);
        }
        if(dependency.equals("Після сну")){
            currentUserTime=OffsetTime.of(Person.getDaySchedule().goingToSleepH, Person.getDaySchedule().goingToSleepM, 0,0,OffsetTime.now().getOffset());
            currentUserTime.plusMinutes(dependencyTime);
        }
        times.add(currentUserTime);
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

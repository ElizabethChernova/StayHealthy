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

    public ArrayList<Time> getUserTimes() {
        return userTimes;
    }

    public void setUserTimes(ArrayList<Time> userTimes) {
        this.userTimes = userTimes;
    }

    private ArrayList<Time> userTimes;
    public void addUserTime(Time time){userTimes.add(time);}

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
    private ArrayList<Time> times;

    public Pill() {
        userTimes=new ArrayList<Time>();
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

    public void countTimeSlots(){
        times=new ArrayList<>(timesPerDay);

        for (Time time:userTimes) {
            OffsetTime currentUserTime = OffsetTime.of(time.getHours(), time.getMinutes(), 0, 0, OffsetTime.now().getOffset());
            if (dependency.equals("До їжі")) {
                currentUserTime = currentUserTime.minusMinutes(dependencyTime);
            }
            if (dependency.equals("Після їжі")) {
                currentUserTime = currentUserTime.plusMinutes(dependencyTime);
            }
            if (dependency.equals("До сну")) {
                //currentUserTime = OffsetTime.of(Person.getDaySchedule().goingToSleep.getHour(), Person.getDaySchedule().goingToSleep.getMinute(), 0, 0, OffsetTime.now().getOffset());
                currentUserTime = currentUserTime.minusMinutes(dependencyTime);
            }
            if (dependency.equals("Після сну")) {
                //currentUserTime = OffsetTime.of(Person.getDaySchedule().goingToSleep.getHour(), Person.getDaySchedule().goingToSleep.getMinute(), 0, 0, OffsetTime.now().getOffset());
                currentUserTime = currentUserTime.plusMinutes(dependencyTime);
            }
            times.add(new Time(currentUserTime.getHour(), currentUserTime.getMinute()));
        }
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

    public ArrayList<Time> getTimes() {
        return times;
    }

    public void setTimes(ArrayList<Time> times) {
        this.times = times;
    }
}

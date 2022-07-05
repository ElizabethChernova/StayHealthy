package com.example.entities;

import java.io.Serializable;

public class Time implements Comparable<Time>, Serializable {
    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    private int hours, minutes;

    @Override
    public String toString() {
        String result="";
        if(hours<10) result+="0";
        result+=hours+" : ";
        if(minutes<10) result+="0";
        result+=minutes;
        return result;
    }

    public Time(int hours, int minutes) {
        this.hours = hours;
        this.minutes = minutes;
    }

    @Override
    public int compareTo(Time time) {
        if(hours> time.hours||hours< time.hours) return Integer.compare(hours,time.hours);
        return Integer.compare(minutes,time.minutes);

    }
}

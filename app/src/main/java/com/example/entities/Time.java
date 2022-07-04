package com.example.entities;

public class Time {
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
}

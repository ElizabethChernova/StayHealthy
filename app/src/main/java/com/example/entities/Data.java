package com.example.entities;

public class Data {
    private int day;

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public Data(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    private int month;

    @Override
    public String toString() {
        String result="";
        if(day<10) result+="0";
        result+=day+".";
        if(month<10) result+="0";
        result+=month+"."+year;
        return result;
    }

    private int year;
}

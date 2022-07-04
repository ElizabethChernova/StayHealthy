package com.example.entities;

public class Note {
    private String comment;
    private float rate;
    private boolean caught;
    private boolean temperature;
    private boolean badAppetite;
    private boolean badEating;
    private boolean badMood;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    private Data data;
    private Time time;

    public Note(){}
    public Note(String comment, float rate, boolean caught, boolean temperature, boolean badAppetite, boolean badEating, boolean badMood, Data data, Time time) {
        this.comment = comment;
        this.rate = rate;
        this.caught = caught;
        this.temperature = temperature;
        this.badAppetite = badAppetite;
        this.badEating = badEating;
        this.badMood = badMood;
        this.data = data;
        this.time = time;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isCaught() {
        return caught;
    }

    public void setCaught(boolean caught) {
        this.caught = caught;
    }

    public boolean isTemperature() {
        return temperature;
    }

    public void setTemperature(boolean temperature) {
        this.temperature = temperature;
    }

    public boolean isBadAppetite() {
        return badAppetite;
    }

    public void setBadAppetite(boolean badAppetite) {
        this.badAppetite = badAppetite;
    }

    public boolean isBadEating() {
        return badEating;
    }

    public void setBadEating(boolean badEating) {
        this.badEating = badEating;
    }

    public boolean isBadMood() {
        return badMood;
    }

    public void setBadMood(boolean badMood) {
        this.badMood = badMood;
    }

}

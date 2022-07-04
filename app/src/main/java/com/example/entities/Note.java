package com.example.entities;

import java.util.ArrayList;

public class Note {
String comment;

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    double rate;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


boolean cought;
boolean temperature;

    public Note(String comment, double rate, boolean cought, boolean temperature, boolean badAppetite, boolean badEating, boolean badMood) {
        this.comment = comment;
        this.rate = rate;
        this.cought = cought;
        this.temperature = temperature;
        this.badAppetite = badAppetite;
        this.badEating = badEating;
        this.badMood = badMood;
    }

    public boolean isCought() {
        return cought;
    }

    public void setCought(boolean cought) {
        this.cought = cought;
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

    boolean badAppetite;
boolean badEating;
boolean badMood;
}

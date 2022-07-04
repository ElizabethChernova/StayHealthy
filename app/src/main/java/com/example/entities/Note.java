package com.example.entities;

public class Note {
    private String comment;
    private double rate;
    private boolean caught;
    private boolean temperature;
    private boolean badAppetite;
    private boolean badEating;
    private boolean badMood;

    public Note(String comment, double rate, boolean caught, boolean temperature, boolean badAppetite, boolean badEating, boolean badMood) {
        this.comment = comment;
        this.rate = rate;
        this.caught = caught;
        this.temperature = temperature;
        this.badAppetite = badAppetite;
        this.badEating = badEating;
        this.badMood = badMood;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
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

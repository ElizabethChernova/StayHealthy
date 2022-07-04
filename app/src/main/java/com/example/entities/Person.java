package com.example.entities;

import java.util.ArrayList;

/**
 * This class contains basic information about user
 */
public class Person {


    /**
     * Name of person
     */
    private String name;
    /**
     * height of person(centimetres)
     */
    private double height;

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    private double weight;

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    private char sex;

    public double getCurrentAmountOfWater() {
        return currentAmountOfWater;
    }

    public void setCurrentAmountOfWater(double currentAmountOfWater) {
        currentAmountOfWater = currentAmountOfWater;
    }

    private double currentAmountOfWater = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ArrayList<Pill> getPills() {
        return pills;
    }

    public void setPills(ArrayList<Pill> pills) {
        this.pills = pills;
    }

    public DaySchedule getUsualDay() {
        return usualDay;
    }

    public void setUsualDay(DaySchedule usualDay) {
        this.usualDay = usualDay;
    }

    public static DaySchedule getDaySchedule() {
        return daySchedule;
    }

    public void addPill(Pill pill) {
        pills.add(pill);
    }

    public void addNote(Note note) {
        notes.add(note);
    }

    public void setDaySchedule(DaySchedule daySchedule) {
        this.daySchedule = daySchedule;
    }

    /**
     * age of person
     */
    private int age;
    /**
     * pills for person
     */
    private ArrayList<Pill> pills;

    private DaySchedule usualDay;
    private static DaySchedule daySchedule;

    public Person(String name, double weight, double height, int age, ArrayList<Pill> pills, DaySchedule usualDay) {
        this.name = name;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.pills = pills;
        this.usualDay = usualDay;
        daySchedule = makeNewSchedule();
    }

    public Person() {
        pills = new ArrayList<Pill>();
        notes = new ArrayList<Note>();
    }

    private DaySchedule makeNewSchedule() {
        return usualDay;
    }

    private ArrayList<Note> notes;

    public ArrayList<Note> getNotes() {
        return notes;
    }

    public void setNotes(ArrayList<Note> notes) {
        this.notes = notes;
    }
}
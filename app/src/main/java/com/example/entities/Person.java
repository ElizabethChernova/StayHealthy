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
        this.currentAmountOfWater = currentAmountOfWater;
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

    public Time getStart() {
        return start;
    }

    public void setStart(Time start) {
        this.start = start;
    }

    public Time getEnd() {
        return end;
    }

    public void setEnd(Time end) {
        this.end = end;
    }
    public void addPill(Pill pill) {
        pills.add(pill);
    }

    public void addNote(Note note) {
        notes.add(note);
    }

    private Time start=new Time(0,0);
    private Time end=new Time(0,0);

    /**
     * age of person
     */
    private int age;
    /**
     * pills for person
     */
    private ArrayList<Pill> pills;


    public Person(String name, double weight, double height, int age, ArrayList<Pill> pill) {
        this.name = name;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.pills = pills;
    }

    public Person() {
        pills = new ArrayList<Pill>();
        notes = new ArrayList<Note>();
    }


    private ArrayList<Note> notes;

    public ArrayList<Note> getNotes() {
        return notes;
    }

    public void setNotes(ArrayList<Note> notes) {
        this.notes = notes;
    }

    public Data getCurrentDataOfProgram() {
        return currentDataOfProgram;
    }

    public void setCurrentDataOfProgram(Data currentDataOfProgram) {
        this.currentDataOfProgram = currentDataOfProgram;
    }

    private Data currentDataOfProgram=new Data(0,0,0);
}
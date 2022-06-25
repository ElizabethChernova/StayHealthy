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

    public DaySchedule getDaySchedule() {
        return daySchedule;
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
    private DaySchedule daySchedule;
    public Person(String name, double weight, double height, int age, ArrayList<Pill> pills, DaySchedule usualDay) {
        this.name = name;
        this.weight=weight;
        this.height = height;
        this.age = age;
        this.pills = pills;
        this.usualDay=usualDay;
       daySchedule = makeNewSchedule();
    }

    public Person(){}
//TODO make new schedule
    private DaySchedule makeNewSchedule() {
        return usualDay;
    }
}
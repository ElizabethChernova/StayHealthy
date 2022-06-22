package com.example.entities;

import java.time.OffsetTime;
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

    public DayScedule getUsualDay() {
        return usualDay;
    }

    public void setUsualDay(DayScedule usualDay) {
        this.usualDay = usualDay;
    }

    public DayScedule getUsualDayWithPills() {
        return usualDayWithPills;
    }

    public void setUsualDayWithPills(DayScedule usualDayWithPills) {
        this.usualDayWithPills = usualDayWithPills;
    }

    /**
     * age of person
     */
    private int age;
    /**
     * pills for person
     */
    private ArrayList<Pill> pills;

private DayScedule usualDay;
    private DayScedule usualDayWithPills;
    public Person(String name, double weight, double height, int age, ArrayList<Pill> pills, DayScedule usualDay) {
        this.name = name;
        this.weight=weight;
        this.height = height;
        this.age = age;
        this.pills = pills;
        this.usualDay=usualDay;
       usualDayWithPills= makeNewScedual();
    }

    public Person(){};
//TODO make new scedual
    private DayScedule makeNewScedual() {
        return usualDay;
    }
}
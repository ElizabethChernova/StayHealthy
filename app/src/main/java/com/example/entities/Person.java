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
    public Person(String name, double height, int age, ArrayList<Pill> pills, DayScedule usualDay) {
        this.name = name;
        this.height = height;
        this.age = age;
        this.pills = pills;
        this.usualDay=usualDay;
       usualDayWithPills= makeNewScedual();
    }
//TODO make new scedual
    private DayScedule makeNewScedual() {
        return usualDay;
    }
}
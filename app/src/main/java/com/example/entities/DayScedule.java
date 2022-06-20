package com.example.entities;

import java.util.Date;
import java.util.HashMap;

public class DayScedule {
    /**
     *dependance on time to something
     */
    HashMap<Date,String> schedule =new HashMap();
    /**
     *dependance on time to Pills
     */
    HashMap<Date,Pill> pillSchedule =new HashMap();
    Date breakfast;
    Date dinner;
    Date lunch;
    Date wakingUp;
    Date Going_to_sleap;
}

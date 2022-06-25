package com.example.entities;

import java.time.OffsetTime;
import java.util.Date;
import java.util.HashMap;

public class DaySchedule {
    /**
     *dependance on time to something
     */
    HashMap<OffsetTime,String> schedule =new HashMap();
    /**
     *dependance on time to Pills
     */
    HashMap<Date,Pill> pillSchedule =new HashMap();
    OffsetTime breakfast;
    OffsetTime dinner;
    OffsetTime lunch;
    public OffsetTime wakingUp;
    public OffsetTime goingToSleep;
}

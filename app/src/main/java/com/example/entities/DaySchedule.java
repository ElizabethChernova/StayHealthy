package com.example.entities;

import java.time.OffsetTime;
import java.util.Date;
import java.util.HashMap;

public class DayScedule {
    /**
     *dependance on time to something
     */
    HashMap<OffsetTime,String> schedule =new HashMap();
    /**
     *dependance on time to Pills
     */
    HashMap<Date,Pill> pillSchedule =new HashMap();
    Date breakfast;
    Date dinner;
    Date lunch;
    Date wakingUp;
    Date goingToSleep;
}

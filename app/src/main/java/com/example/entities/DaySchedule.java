package com.example.entities;

import java.time.OffsetTime;
import java.util.Date;
import java.util.HashMap;

public class DaySchedule {
    public DaySchedule(OffsetTime wakingUp, OffsetTime goingToSleep) {
        this.wakingUp = wakingUp;
        this.goingToSleep = goingToSleep;
    }

    public OffsetTime wakingUp;
    public OffsetTime goingToSleep;
}

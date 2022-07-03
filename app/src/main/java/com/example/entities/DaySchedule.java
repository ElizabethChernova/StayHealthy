package com.example.entities;

import java.time.LocalDateTime;
import java.time.OffsetTime;
import java.util.Date;
import java.util.HashMap;

public class DaySchedule {
    public DaySchedule(int wakingUpH,int wakingUpM,int goingToSleepH,int goingToSleepM) {
        this.wakingUpH=wakingUpH;
        this.wakingUpM=wakingUpM;

        this.goingToSleepH=goingToSleepH;
        this.goingToSleepM=goingToSleepM;

        this.wakingUp = OffsetTime.of(this.wakingUpH,this.wakingUpM,0,0,OffsetTime.now().getOffset());
        this.goingToSleep = OffsetTime.of(this.goingToSleepH,this.goingToSleepM,0,0,OffsetTime.now().getOffset());
    }

    public int wakingUpH,wakingUpM,goingToSleepH,goingToSleepM;
    public OffsetTime wakingUp;
    public OffsetTime goingToSleep;
}

package com.heroku.spacey.entity;

import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
public class Timeslots {
    private Date date;
    private List<Timestamp> timeSlots = new ArrayList<>();
}

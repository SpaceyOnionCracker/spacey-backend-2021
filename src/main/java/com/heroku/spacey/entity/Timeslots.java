package com.heroku.spacey.entity;

import lombok.Data;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Data
public class Timeslots {
    private Date date;
    private List<Time> timeSlots;
}

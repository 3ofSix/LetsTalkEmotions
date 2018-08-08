package com.garrettvernon.letstalkemotions.heartData;

import android.arch.persistence.room.Entity;

import org.json.JSONObject;

import java.util.Date;

@Entity
public class HeartData {
    private int id;
    private Date date;
    private JSONObject heartBeats;
}

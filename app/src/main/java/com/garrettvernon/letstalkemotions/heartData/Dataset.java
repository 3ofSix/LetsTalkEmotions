package com.garrettvernon.letstalkemotions.heartData;


public class Dataset {
    private String time;

    private String value;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ClassPojo [time = " + time + ", value = " + value + "]";
    }
}


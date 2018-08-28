package com.garrettvernon.letstalkemotions.heartData;


public class Dataset {
    private String time;

    private String value;

    public String getTime() {
        return time;
    }

    public String getValue() {
        return value;
    }

    public Float getValueFloat() {
        Float valueFloat = Float.parseFloat(getValue());
        return valueFloat;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ClassPojo [time = " + time + ", value = " + value + "]";
    }
}


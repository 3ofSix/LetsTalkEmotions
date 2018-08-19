package com.garrettvernon.letstalkemotions.heartData;


public class ActivitiesHeart {
    private String dateTime;

    private HeartRateZones[] heartRateZones;

    private String[] customHeartRateZones;

    private String value;

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public HeartRateZones[] getHeartRateZones() {
        return heartRateZones;
    }

    public void setHeartRateZones(HeartRateZones[] heartRateZones) {
        this.heartRateZones = heartRateZones;
    }

    public String[] getCustomHeartRateZones() {
        return customHeartRateZones;
    }

    public void setCustomHeartRateZones(String[] customHeartRateZones) {
        this.customHeartRateZones = customHeartRateZones;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ClassPojo [dateTime = " + dateTime + ", heartRateZones = " + heartRateZones + ", customHeartRateZones = " + customHeartRateZones + ", value = " + value + "]";
    }
}
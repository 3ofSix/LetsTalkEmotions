package com.garrettvernon.letstalkemotions.heartData;

public class ActivitiesHeart {

    private String dateTime;

    private Value value;

    public String getDateTime ()
    {
        return dateTime;
    }

    public void setDateTime (String dateTime)
    {
        this.dateTime = dateTime;
    }

    public Value getValue ()
    {
        return value;
    }

    public void setValue (Value value)
    {
        this.value = value;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [dateTime = "+dateTime+", value = "+value+"]";
    }
}

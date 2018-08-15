package com.garrettvernon.letstalkemotions.heartData;

public class HeartRateZones {

    private String min;

    private String minutes;

    private String max;

    private String name;

    private String caloriesOut;

    public String getMin ()
    {
        return min;
    }

    public void setMin (String min)
    {
        this.min = min;
    }

    public String getMinutes ()
    {
        return minutes;
    }

    public void setMinutes (String minutes)
    {
        this.minutes = minutes;
    }

    public String getMax ()
    {
        return max;
    }

    public void setMax (String max)
    {
        this.max = max;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getCaloriesOut ()
    {
        return caloriesOut;
    }

    public void setCaloriesOut (String caloriesOut)
    {
        this.caloriesOut = caloriesOut;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [min = "+min+", minutes = "+minutes+", max = "+max+", name = "+name+", caloriesOut = "+caloriesOut+"]";
    }
}

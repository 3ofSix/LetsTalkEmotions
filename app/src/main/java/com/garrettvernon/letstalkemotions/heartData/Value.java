package com.garrettvernon.letstalkemotions.heartData;

public class Value {
    private String restingHeartRate;

    private HeartRateZones[] heartRateZones;

    private String[] customHeartRateZones;

    public String getRestingHeartRate ()
    {
        return restingHeartRate;
    }

    public void setRestingHeartRate (String restingHeartRate)
    {
        this.restingHeartRate = restingHeartRate;
    }

    public HeartRateZones[] getHeartRateZones ()
    {
        return heartRateZones;
    }

    public void setHeartRateZones (HeartRateZones[] heartRateZones)
    {
        this.heartRateZones = heartRateZones;
    }

    public String[] getCustomHeartRateZones ()
    {
        return customHeartRateZones;
    }

    public void setCustomHeartRateZones (String[] customHeartRateZones)
    {
        this.customHeartRateZones = customHeartRateZones;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [restingHeartRate = "+restingHeartRate+", heartRateZones = "+heartRateZones+", customHeartRateZones = "+customHeartRateZones+"]";
    }
}

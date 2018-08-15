package com.garrettvernon.letstalkemotions.heartData;


public class FitBitJson {
    private ActivitiesHeartIntraday activitiesHeartIntraday;

    private ActivitiesHeart[] activitiesHeart;

    public ActivitiesHeartIntraday getActivitiesHeartIntraday() {
        return activitiesHeartIntraday;
    }

    public void setActivitiesHeartIntraday(ActivitiesHeartIntraday activitiesHeartIntraday) {
        this.activitiesHeartIntraday = activitiesHeartIntraday;
    }

    public ActivitiesHeart[] getActivitiesHeart() {
        return activitiesHeart;
    }

    public void setActivitiesHeart(ActivitiesHeart[] activitiesHeart) {
        this.activitiesHeart = activitiesHeart;
    }

    @Override
    public String toString() {
        return "ClassPojo [activitiesHeartIntraday = " + activitiesHeartIntraday + ", activitiesHeart = " + activitiesHeart + "]";
    }
}


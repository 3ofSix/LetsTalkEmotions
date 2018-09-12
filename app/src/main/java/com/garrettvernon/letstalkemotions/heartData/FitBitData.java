package com.garrettvernon.letstalkemotions.heartData;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;


import com.google.gson.Gson;



//TODO This is proof of concept and works. TIdy up code to a useable class
//NOTE THIS SOLUTION CURRENTLY ONLY WORKS FRO GiVEN TIME SLOTS
/*GET https://api.fitbit.com/1/user/[user-id]/activities/heart/date/[date]/[period].json
        GET https://api.fitbit.com/1/user/[user-id]/activities/heart/date/[base-date]/[end-date].json

        user-id	The encoded ID of the user. Use "-" (dash) for current logged-in user.
        base-date	The range start date, in the format yyyy-MM-dd or today.
        end-date	The end date of the range.
        date	The end date of the period specified in the format yyyy-MM-dd or today.
        period	The range for which data will be returned. Options are 1d, 7d, 30d, 1w, 1m.
        user id: 6SKNR6
        Greater DETAIL

        GET https://api.fitbit.com/1/user/-/activities/heart/date/[date]/[end-date]/[detail-level].json
        GET https://api.fitbit.com/1/user/-/activities/heart/date/[date]/[end-date]/[detail-level]/time/[start-time]/[end-time].json
        GET https://api.fitbit.com/1/user/-/activities/heart/date/[date]/1d/[detail-level].json`
        GET https://api.fitbit.com/1/user/-/activities/heart/date/[date]/1d/[detail-level]/time/[start-time]/[end-time].json
        "https://api.fitbit.com/1/user/-/activities/heart/date/2018-08-05/1d/1sec.json";*/
public class FitBitData implements AsyncResponse {
    private static final String TAG = "FitBitData";
    private final String baseUrl = "https://api.fitbit.com/1/user/-/activities/heart/date/2018-08-05/1d/1sec/time/18:35/18:40.json";
    private final String headerAuthorisationToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI2U0tOUjYiLCJhdWQiOiIyMkNYTTYiLCJpc3MiOiJGaXRiaXQiLCJ0eXAiOiJhY2Nlc3NfdG9rZW4iLCJzY29wZXMiOiJyc29jIHJzZXQgcmFjdCBybG9jIHJ3ZWkgcmhyIHJwcm8gcm51dCByc2xlIiwiZXhwIjoxNTM2MzU0MzMxLCJpYXQiOjE1MzM3NjIzMzF9.kIaNR4Sh-rBMvDF5FVqkIcPJbYN4ZOJ-p7oaCOAHxTc";
    private Dataset[] fitbitData;
    private Context context;

    public FitBitData(Context context) {
        this.context = context;
        new DataRequest(this).execute(this);
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getHeaderAuthorisationToken() {
        return headerAuthorisationToken;

    }

    public Dataset[] getFitbitData() {
        return fitbitData;
    }

    private void setFitbitData(Dataset[] fitbitData) {
        Log.d(TAG, "setFitbitData: called");
        this.fitbitData = fitbitData;
        Log.d(TAG, "setFitbitData: Brodacasting ready");
        Intent intent = new Intent("fitbitdata_ready");
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    @Override
    public void processFinish(String result) {
        Log.d(TAG, "processFinish: in FitBitData");
        String json = result;

        //Remove the unwanted hyphen in Activities-heart-intraday and Activiites-heart
        json = json.replaceAll("s-h", "sH");
        json = json.replaceAll("t-i", "tI");

        Gson gson = new Gson();

        FitBitJson fitBitJson = gson.fromJson(json, FitBitJson.class);
        ActivitiesHeartIntraday activitiesHeartIntraday = fitBitJson.getActivitiesHeartIntraday();
        setFitbitData(activitiesHeartIntraday.getDataset());
    }

}

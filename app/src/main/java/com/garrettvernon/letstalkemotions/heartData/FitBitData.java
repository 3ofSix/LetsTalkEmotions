package com.garrettvernon.letstalkemotions.heartData;

import android.util.Log;


import com.google.gson.Gson;

import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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
public class FitBitData {
    private static final String TAG = "FitBitData";
    private final OkHttpClient client = new OkHttpClient();
    private final String baseUrl = "https://api.fitbit.com/1/user/-/activities/heart/date/2018-08-05/1d/1sec/time/18:35/18:40.json";
    private final String headerAuthorisationToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI2U0tOUjYiLCJhdWQiOiIyMkNYTTYiLCJpc3MiOiJGaXRiaXQiLCJ0eXAiOiJhY2Nlc3NfdG9rZW4iLCJzY29wZXMiOiJyc29jIHJzZXQgcmFjdCBybG9jIHJ3ZWkgcmhyIHJwcm8gcm51dCByc2xlIiwiZXhwIjoxNTM2MzU0MzMxLCJpYXQiOjE1MzM3NjIzMzF9.kIaNR4Sh-rBMvDF5FVqkIcPJbYN4ZOJ-p7oaCOAHxTc";

    public String getFitbitJSON() throws IOException {
        Request request = new Request.Builder()
                .url(baseUrl)
                .get()
                .addHeader("authorization", headerAuthorisationToken)
                .addHeader("cache-control", "no-cache")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                Log.d(TAG, "getFitBitData: IOException" + response);
                throw new IOException("Unexpected code " + response);
            }
            //String jsonData = response.body().string().toString();
            return response.body().string();
        }
    }

    public Dataset[] getFitbitData(){
        String json = null;
        try {
            json = getFitbitJSON();
        } catch (IOException e){
            Log.d(TAG, "getFitbitData: Exception " + e);
        }
        //Remove the unwanted hyphen in Activities-heart-intraday and Activiites-heart
        json = json.replaceAll("s-h", "sH");
        json = json.replaceAll("t-i", "tI");

        Gson gson = new Gson();

        FitBitJson fitBitJson = gson.fromJson(json, FitBitJson.class);
        ActivitiesHeartIntraday activitiesHeartIntraday = fitBitJson.getActivitiesHeartIntraday();
        System.out.println("Dataset type: " +activitiesHeartIntraday.getDatasetType());
        return activitiesHeartIntraday.getDataset();
    }



    /*//TODO Remove main method before final packaging
    public static void main(String[] args) throws Exception {
        //do something cool
        FitBitData fitBitData = new FitBitData();
        for (Dataset data : fitBitData.getFitbitData()){
            System.out.println("Time: " + data.getTime() + " HeartRate: " + data.getValue());
        }
    }*/

}

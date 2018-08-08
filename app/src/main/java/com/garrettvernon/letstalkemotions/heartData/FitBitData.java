package com.garrettvernon.letstalkemotions.heartData;

import android.util.Log;
import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

//TODO This is proof of concept and works. TIdy up code to a useable class
/*GET https://api.fitbit.com/1/user/[user-id]/activities/heart/date/[date]/[period].json
        GET https://api.fitbit.com/1/user/[user-id]/activities/heart/date/[base-date]/[end-date].json

        user-id	The encoded ID of the user. Use "-" (dash) for current logged-in user.
        base-date	The range start date, in the format yyyy-MM-dd or today.
        end-date	The end date of the range.
        date	The end date of the period specified in the format yyyy-MM-dd or today.
        period	The range for which data will be returned. Options are 1d, 7d, 30d, 1w, 1m.

        Greater DETAIL

        GET https://api.fitbit.com/1/user/-/activities/heart/date/[date]/[end-date]/[detail-level].json
        GET https://api.fitbit.com/1/user/-/activities/heart/date/[date]/[end-date]/[detail-level]/time/[start-time]/[end-time].json
        GET https://api.fitbit.com/1/user/-/activities/heart/date/[date]/1d/[detail-level].json`
        GET https://api.fitbit.com/1/user/-/activities/heart/date/[date]/1d/[detail-level]/time/[start-time]/[end-time].json*/
public class FitBitData {
    private static final String TAG = "FitBitData";
    private final OkHttpClient client = new OkHttpClient();
    private final String baseUrl = "https://api.fitbit.com/1/user/-/activities/heart/date/05-08-2018/1d/1sec.json";
    private final String headerAuthorisationToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI2U0tOUjYiLCJhdWQiOiIyMkNYTTYiLCJpc3MiOiJGaXRiaXQiLCJ0eXAiOiJhY2Nlc3NfdG9rZW4iLCJzY29wZXMiOiJyc29jIHJhY3QgcnNldCBybG9jIHJ3ZWkgcmhyIHJudXQgcnBybyByc2xlIiwiZXhwIjoxNTMzNzUzNDQ0LCJpYXQiOjE1MzMxNDg2NDR9.JrrPOUXJDcllWK3kkrLhXaNNiGs6-OJ34DB8Qyhaoww";

    public String getFitBitData() throws IOException {
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

            return response.body().string();
        }
    }

    //TODO Remove main method before final packaging
    public static void main(String[] args) throws Exception {
        System.out.println(new FitBitData().getFitBitData());
    }

}

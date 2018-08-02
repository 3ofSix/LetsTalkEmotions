package com.garrettvernon.letstalkemotions;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
//TODO This is proof of concept and works. TIdy up code to a useable class
public class FitBitData {
    //    private final String baseUrl = "https://api.fitbit.com/1/user/-/activities/heart/date/";
    private final String baseUrl = "https://api.fitbit.com/1/user/-/activities/heart/date/today/1d/1sec/time/18:35/18:40.json";
    private final String headerAuthorisationToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI2U0tOUjYiLCJhdWQiOiIyMkNYTTYiLCJpc3MiOiJGaXRiaXQiLCJ0eXAiOiJhY2Nlc3NfdG9rZW4iLCJzY29wZXMiOiJyc29jIHJhY3QgcnNldCBybG9jIHJ3ZWkgcmhyIHJudXQgcnBybyByc2xlIiwiZXhwIjoxNTMzNzUzNDQ0LCJpYXQiOjE1MzMxNDg2NDR9.JrrPOUXJDcllWK3kkrLhXaNNiGs6-OJ34DB8Qyhaoww";
    private final OkHttpClient client = new OkHttpClient();


    public String getFitBitData() throws IOException {
        Request request = new Request.Builder()
                .url(baseUrl)
                .get()
                .addHeader("authorization", headerAuthorisationToken)
                .addHeader("cache-control", "no-cache")
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
    //TODO Remove main method before final packaging
    public static void main(String[] args) throws IOException {
        FitBitData example = new FitBitData();
        //String response = example.run("https://raw.github.com/square/okhttp/master/README.md");
        String response = example.getFitBitData();
        System.out.println(response);
    }
}

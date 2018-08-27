package com.garrettvernon.letstalkemotions.heartData;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DataRequest extends AsyncTask<FitBitData,Void,String> {
    private static final String TAG = "DataRequest";
    private OkHttpClient client = new OkHttpClient();
    public AsyncResponse delegate = null;

    public DataRequest(AsyncResponse delegate){
        this.delegate = delegate;
    }


    @Override
    protected String doInBackground(FitBitData... data) {
        Log.d(TAG, "doInBackground: Async Request Running");
        Request request = new Request.Builder()
                .url(data[0].getBaseUrl())
                .get()
                .addHeader("authorization", data[0].getHeaderAuthorisationToken())
                .addHeader("cache-control", "no-cache")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                Log.d(TAG, "DataRequest: IOException" + response);
                throw new IOException("Unexpected code " + response);
            }
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    /*
    * Override of AsyncTask onPostExecute
    * Calling class must implement Interface AsyncResponse
    */
    @Override
    protected void onPostExecute(String result){
        Log.d(TAG, "onPostExecute: Post Execution starting");
        delegate.processFinish(result);
    }

}

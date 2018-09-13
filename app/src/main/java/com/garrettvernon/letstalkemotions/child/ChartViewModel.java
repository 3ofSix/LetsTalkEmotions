package com.garrettvernon.letstalkemotions.child;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.garrettvernon.letstalkemotions.heartData.Dataset;
import com.garrettvernon.letstalkemotions.heartData.FitBitData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

public class ChartViewModel extends AndroidViewModel {
    /*
    The ViewModel for the ChartFragment view. This feeds data to the view
     */

    private static final String TAG = "ChartViewModel";
    private LineDataSet dataSet;
    private MutableLiveData<LineData> liveLineData = new MutableLiveData<>();
    private FitBitData fitBitData;

    private List<String> times;

    public ChartViewModel(Application application) {
        super(application);
        Log.d(TAG, "ChartViewModel: Instantiation!");
        //Register a broadcast manager
        LocalBroadcastManager.getInstance(application).registerReceiver(mMessageReceiver,
                new IntentFilter("fitbitdata_ready"));
        fitBitData = new FitBitData(application);
    }

    public MutableLiveData<LineData> getLiveLineData() {
        return liveLineData;
    }


    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("receiver", "ChartFragment got message");
            //LineDataset objects hold data that belong together and can be styled
            dataSet = new LineDataSet(getData(),"Heart Rate"); //Label appears if Legend is enabled
            LineData lineData = new LineData(dataSet);
            liveLineData.postValue(lineData);
        }
    };

    public List<Entry> getData(){
        Float timeCount = 0f;
        times = new ArrayList<>();
        List<Entry> entries = new ArrayList<>();
        Dataset[] dataObjects = fitBitData.getFitbitData();
        for (Dataset data: dataObjects){
            //Make data Entry objects
            entries.add(new Entry(timeCount, data.getValueFloat()));
            times.add(data.getTime());
            timeCount++;
        }
        return entries;
    }

    public List<String> getTimes() {
        return times;
    }

}

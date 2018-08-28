package com.garrettvernon.letstalkemotions.child;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.garrettvernon.letstalkemotions.R;
import com.garrettvernon.letstalkemotions.heartData.Dataset;
import com.garrettvernon.letstalkemotions.heartData.FitBitData;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;


public class ChartFragment extends Fragment {

    private static final String TAG = "ChartFragment";
    private LineChart chart;
    private LineDataSet dataSet;
    FitBitData fitBitData;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Register a broadcast manager
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(mMessageReceiver,
                new IntentFilter("fitbitdata_ready"));
        fitBitData = new FitBitData(getContext());
    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            String message = intent.getStringExtra("msg");
            Log.d("receiver", "Got message: " + message);
            //LineDataset objects hold data that belong together and can be styled
            dataSet = new LineDataSet(getData(),"Label"); //Label appears if Legend is enabled
            //LineData objects hold all LineDatasets for the chart. Cn be styled
            LineData lineData = new LineData(dataSet);
            chart.setData(lineData);
            chart.invalidate(); //refresh chart
        }
    };

    @Override
    public void onDestroy() {
        // Unregister since the activity is about to be closed.
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_chart, container, false);
        Log.d(TAG, "onCreateView: Started");
        chart = (LineChart) view.findViewById(R.id.chart);
        return view;
    }

    public List<Entry> getData(){
        Float timeCount = 0f;
        List<Entry> entries = new ArrayList<>();
        Dataset[] dataObjects = fitBitData.getFitbitData();
        for (Dataset data: dataObjects){
            //Make data Entry objects
            //entries.add(new Entry(data.getTimeFloat(), data.getValueFloat()));
            entries.add(new Entry(timeCount, data.getValueFloat()));
            timeCount++;
        }
      /*  entries.add(new Entry(0,2));
        entries.add(new Entry(2,4));
        entries.add(new Entry(4,6));
        entries.add(new Entry(6,8));
        entries.add(new Entry(8,10));
        entries.add(new Entry(10,12));*/
        return entries;
    }
}

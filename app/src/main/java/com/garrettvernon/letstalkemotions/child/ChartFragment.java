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
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;


public class ChartFragment extends Fragment {

    private static final String TAG = "ChartFragment";
    private LineChart chart;
    private LineDataSet dataSet;
    FitBitData fitBitData;
    List<String> times;

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
            Log.d("receiver", "ChartFragment got message");
            //LineDataset objects hold data that belong together and can be styled
            dataSet = new LineDataSet(getData(),"Heart Rate"); //Label appears if Legend is enabled
            //Format labels for xAxis
            XAxis xAxis = chart.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setDrawAxisLine(true);
            xAxis.setGranularity(1f);
            xAxis.setValueFormatter(new xAxisValueFormatter(times));
            //Format yAxis
            chart.getAxisRight().setEnabled(false);
            //LineData objects hold all LineDatasets for the chart. Can be styled
            LineData lineData = new LineData(dataSet);
            chart.setData(lineData);
            chart.invalidate(); //refresh chart
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_chart, container, false);
        Log.d(TAG, "onCreateView: Started");
        chart = (LineChart) view.findViewById(R.id.chart);
        return view;
    }

    @Override
    public void onDestroy() {
        // Unregister since the activity is about to be closed.
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }

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

    public class xAxisValueFormatter implements IAxisValueFormatter {

        private List<String> mValues;

        public xAxisValueFormatter(List<String> mValues) {
            this.mValues = mValues;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return mValues.get((int) value);
        }
    }
}

package com.garrettvernon.letstalkemotions.child;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.garrettvernon.letstalkemotions.heartData.Dataset;
import com.garrettvernon.letstalkemotions.heartData.FitBitData;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

public class ChartViewModel extends AndroidViewModel {
    /*
    The ViewModel for the ChartFragment view. This feeds data to the view
     */

    private static final String TAG = "ChartViewModel";
    private LineChart chart;
    private LiveData<LineChart> chartLiveData;
    private LineDataSet dataSet;
    FitBitData fitBitData;
    List<String> times;

    public ChartViewModel(Application application) {
        super(application);
        Log.d(TAG, "ChartViewModel: Instantiation!");
        //Register a broadcast manager
        LocalBroadcastManager.getInstance(application).registerReceiver(mMessageReceiver,
                new IntentFilter("fitbitdata_ready"));
        fitBitData = new FitBitData(application.getApplicationContext());
    }

    public LiveData<LineChart> getChartLiveData() {
        return chartLiveData;
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
            //chart.invalidate(); //refresh chart
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

package com.garrettvernon.letstalkemotions.child;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.garrettvernon.letstalkemotions.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;


import java.util.List;


public class ChartFragment extends Fragment {

    private static final String TAG = "ChartFragment";
    private LineChart chart;
    private List<String> times;
    private ChartViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(ChartViewModel.class);
        // Set up observer. OnChanged() should refresh the chart when new data received
        viewModel.getLiveLineData().observe(this, new Observer<LineData>() {
            @Override
            public void onChanged(@Nullable LineData lineData) {
                times = viewModel.getTimes();
                chartInit();
                //Update the chart
                //LineData objects hold all LineDatasets for the chart. Can be styled
                chart.setData(lineData);
                chart.invalidate(); //refresh chart*/
            }
        });
    }

    private void chartInit() {
        //Format labels for xAxis
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawAxisLine(true);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new xAxisValueFormatter(times));
        //Format yAxis
        chart.getAxisRight().setEnabled(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_chart, container, false);
        Log.d(TAG, "onCreateView: Started");
        chart = (LineChart) view.findViewById(R.id.chart);
        return view;
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

package com.garrettvernon.letstalkemotions.heartData;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.anychart.anychart.AnyChart;
import com.anychart.anychart.AnyChartView;
import com.anychart.anychart.Cartesian;
import com.anychart.anychart.CartesianSeriesLine;
import com.anychart.anychart.DataEntry;
import com.anychart.anychart.EnumsAnchor;
import com.anychart.anychart.Mapping;
import com.anychart.anychart.MarkerType;
import com.anychart.anychart.Set;
import com.anychart.anychart.Stroke;
import com.anychart.anychart.TooltipPositionMode;
import com.anychart.anychart.ValueDataEntry;
import com.garrettvernon.letstalkemotions.R;

import java.util.ArrayList;
import java.util.List;

public class LineChart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_common);

        AnyChartView anyChartView = findViewById(R.id.any_chart_view);
        anyChartView.setProgressBar(findViewById(R.id.progress_bar));

        //Line Chart
        Cartesian cartesian = AnyChart.line();
        cartesian.setAnimation(true);
        cartesian.setPadding(10d,20d,5d,20d);
        //Set up crosshair for selection
        cartesian.getCrosshair().setEnabled(true);
        cartesian.getCrosshair()
                .setYLabel(true)
                .setYStroke((Stroke) null, null, null, null, null);

        cartesian.getTooltip().setPositionMode(TooltipPositionMode.POINT);
        //TODO put correct date corresponding to the data
        cartesian.setTitle("Heart data for DATE");

        cartesian.getYAxis().setTitle("Heart Beat per minute");
        cartesian.getXAxis().getLabels().setPadding(5d, 5d, 5d, 5d);

        /*
        Populate an Array list with the heart data
        Format is
        List<DataEntry> seriesData = new ArrayList<>();
        seriesData.add(new CustomDataEntry("1986", 3.6, 2.3, 2.8));
        In this example 1986 is the X value with 3 y values denoting 3 different series
        This requires extending ValueDataEntry
         */
        // Get Fitbit data
        FitBitData fitBitData = new FitBitData();
        List<DataEntry> seriesData = new ArrayList<>();
        /*for (Dataset fitBit : fitBitData.getFitbitData()){
            seriesData.add(new ValueDataEntry(fitBit.getTime(),Integer.parseInt(fitBit.getValue())));
        }*/
        seriesData.add(new ValueDataEntry("12:00", 56));
        seriesData.add(new ValueDataEntry("13:00", 65));
        seriesData.add(new ValueDataEntry("14:00", 47));
        Set set = new Set(seriesData);
        Mapping series1Mapping = set.mapAs("{ x: 'x', value: 'value' }");

        CartesianSeriesLine series1 = cartesian.line(series1Mapping);
        series1.setName("Brandy");
        series1.getHovered().getMarkers().setEnabled(true);
        series1.getHovered().getMarkers()
                .setType(MarkerType.CIRCLE)
                .setSize(4d);
        series1.getTooltip()
                .setPosition("right")
                .setAnchor(EnumsAnchor.LEFT_CENTER)
                .setOffsetX(5d)
                .setOffsetY(5d);

        cartesian.getLegend().setEnabled(true);
        cartesian.getLegend().setFontSize(13d);
        cartesian.getLegend().setPadding(0d, 0d, 10d, 0d);

        anyChartView.setChart(cartesian);
    }
}

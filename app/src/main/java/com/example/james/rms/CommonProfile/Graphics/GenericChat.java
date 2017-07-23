package com.example.james.rms.CommonProfile.Graphics;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;

import com.example.james.rms.CommonProfile.Graphics.Model.BarChatModel;
import com.example.james.rms.R;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by james on 22/3/2017.
 */

public class GenericChat {

    public void horizontalBarChart(Context c, HorizontalBarChart horizontalBarChart, BarChatModel barChatModel) {

        //one set
        BarDataSet w_barDataSet = new BarDataSet(barChatModel.getWeight_value(), c.getString(R.string.label_full_weight));
        w_barDataSet.setColor(ContextCompat.getColor(c,R.color.grayA3C2C2));
        BarDataSet qty_barDataSet = new BarDataSet(barChatModel.getQty_value(), c.getString(R.string.label_full_qty));
        qty_barDataSet.setColor(ContextCompat.getColor(c,R.color.redFF3333));
        //multiple set
            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(w_barDataSet);
            dataSets.add(qty_barDataSet);
            //A BarData
            BarData Data = new BarData(dataSets);

            XAxis xl = horizontalBarChart.getXAxis();
            xl.setPosition(XAxis.XAxisPosition.BOTTOM);
            xl.setTypeface(Typeface.DEFAULT_BOLD);
            xl.setDrawAxisLine(true);
            xl.setDrawGridLines(false);
            xl.setGranularity(1);
            xl.setValueFormatter(new IndexAxisValueFormatter(barChatModel.getWeight_name()));
//        xl.setLabelRotationAngle(12);
            //
            YAxis yl = horizontalBarChart.getAxisLeft();
            yl.setTypeface(Typeface.DEFAULT_BOLD);
            yl.setDrawAxisLine(true);
            yl.setDrawGridLines(true);
            yl.setAxisMinimum(0f); // this replaces setStartAtZero(true)
//        yl.setValueFormatter(new IndexAxisValueFormatter(setLabel()));
            //
            YAxis yr = horizontalBarChart.getAxisRight();
            yr.setTypeface(Typeface.DEFAULT_BOLD);
            yr.setDrawAxisLine(true);
            yr.setDrawGridLines(false);
            yr.setAxisMinimum(0f); // this replaces setStartAtZero(true)
            //
            Legend l = horizontalBarChart.getLegend();
            l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
            l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
            l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
            l.setDrawInside(false);
            l.setFormSize(8f);
            l.setXEntrySpace(4f);

            horizontalBarChart.setDoubleTapToZoomEnabled(false);
            horizontalBarChart.setFitBars(true);
            horizontalBarChart.setDescription(null);
            horizontalBarChart.animateX(100);
            horizontalBarChart.invalidate();

            horizontalBarChart.setData(Data);
    }
}

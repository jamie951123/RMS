package com.example.james.rms.common.graphic

import android.content.Context
import android.graphics.Typeface
import android.support.v4.content.ContextCompat

import com.example.james.rms.R
import com.github.mikephil.charting.charts.HorizontalBarChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet

import java.util.ArrayList

/**
 * Created by james on 22/3/2017.
 */

class GenericChart {

    fun horizontalBarChart(c: Context, horizontalBarChart: HorizontalBarChart, barChatModel: BarChatModel) {

        //one set
        val w_barDataSet = BarDataSet(barChatModel.weight_value, c.getString(R.string.label_full_weight))
        w_barDataSet.color = ContextCompat.getColor(c, R.color.grayA3C2C2)
        val qty_barDataSet = BarDataSet(barChatModel.qty_value, c.getString(R.string.label_full_qty))
        qty_barDataSet.color = ContextCompat.getColor(c, R.color.redFF3333)
        //multiple set
        val dataSets = ArrayList<IBarDataSet>()
        dataSets.add(w_barDataSet)
        dataSets.add(qty_barDataSet)
        //A BarData
        val Data = BarData(dataSets)

        val xl = horizontalBarChart.xAxis
        xl.position = XAxis.XAxisPosition.BOTTOM
        xl.typeface = Typeface.DEFAULT_BOLD
        xl.setDrawAxisLine(true)
        xl.setDrawGridLines(false)
        xl.granularity = 1f
        xl.valueFormatter = IndexAxisValueFormatter(barChatModel.weight_name)
        //        xl.setLabelRotationAngle(12);
        //
        val yl = horizontalBarChart.axisLeft
        yl.typeface = Typeface.DEFAULT_BOLD
        yl.setDrawAxisLine(true)
        yl.setDrawGridLines(true)
        yl.axisMinimum = 0f // this replaces setStartAtZero(true)
        //        yl.setValueFormatter(new IndexAxisValueFormatter(setLabel()));
        //
        val yr = horizontalBarChart.axisRight
        yr.typeface = Typeface.DEFAULT_BOLD
        yr.setDrawAxisLine(true)
        yr.setDrawGridLines(false)
        yr.axisMinimum = 0f // this replaces setStartAtZero(true)
        //
        val l = horizontalBarChart.legend
        l.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
        l.orientation = Legend.LegendOrientation.HORIZONTAL
        l.setDrawInside(false)
        l.formSize = 8f
        l.xEntrySpace = 4f

        horizontalBarChart.isDoubleTapToZoomEnabled = false
        horizontalBarChart.setFitBars(true)
        horizontalBarChart.description = null
        horizontalBarChart.animateX(100)
        horizontalBarChart.invalidate()

        horizontalBarChart.data = Data
    }
}

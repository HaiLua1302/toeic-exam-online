package com.example.user.ui.setting;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import com.example.user.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;


public class achievement_user extends AppCompatActivity {

     BarChart mBarChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.achievement_user);


        mBarChart = (BarChart) findViewById(R.id.btn_achievement);

        ArrayList<BarEntry> visitor = new ArrayList<>();
        visitor.add(new BarEntry(0, 350));
        visitor.add(new BarEntry(1, 450));
        visitor.add(new BarEntry(2, 750));
        visitor.add(new BarEntry(3, 950));
        visitor.add(new BarEntry(4, 150));
        visitor.add(new BarEntry(5, 550));
        visitor.add(new BarEntry(6, 350));


        ArrayList<String> theDays = new ArrayList<>();
        theDays.add("T2");
        theDays.add("T3");
        theDays.add("T4");
        theDays.add("T5");
        theDays.add("T6");
        theDays.add("T7");
        theDays.add("CN");

        BarDataSet barDataSet = new BarDataSet(visitor,"The Days");
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        BarData barData = new BarData(barDataSet);

        mBarChart.setTouchEnabled(true);
        mBarChart.setDragEnabled(true);
        mBarChart.setScaleEnabled(true);
        mBarChart.setData(barData);
        mBarChart.getDescription().setText("Toeic Achievement Weekly");
        mBarChart.animateY(2000);

    }
}
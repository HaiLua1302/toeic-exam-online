package com.example.user.ui.setting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.user.R;
import com.example.user.models.CauHoi;
import com.example.user.ui.class_user.cls_achievement;
import com.example.user.ui.class_user.cls_user_info;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class achievement_user extends AppCompatActivity {
    private DatabaseReference reference;
    private FirebaseUser userInfo;
    private String userID;
    private ArrayList<cls_achievement> list;
    private Spinner spnFilter;
    BarChart mBarChart;
    private String selected;
    private long aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Achievement");
        userInfo = FirebaseAuth.getInstance().getCurrentUser();
        userID = userInfo.getUid();

        //customsize
        actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24);

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.achievement_user);
        spnFilter = findViewById(R.id.spn_part);

        mBarChart = (BarChart) findViewById(R.id.btn_achievement);
        filterDataChart();
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                ArrayList<BarEntry> visitor = new ArrayList<BarEntry>();
//                if(snapshot.hasChildren()){
//                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){
//                        cls_achievement achie = dataSnapshot.getValue(cls_achievement.class);
//                        visitor.add(new BarEntry(0, achie.getScore()));
//                    }
//
//                }
//                else{
//                    mBarChart.clear();
//                    mBarChart.invalidate();
//                }
//                XAxis xAxis = mBarChart.getXAxis();
//                YAxis yAxis = mBarChart.getAxisLeft();
//                YAxis yAxis1 = mBarChart.getAxisRight();
//                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//                xAxis.setValueFormatter(new ValueFormatter() {
//                    @Override
//                    public String getAxisLabel(float value, AxisBase axis) {
//                        SimpleDateFormat getTime = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
//                        return getTime.format(new Date());
//                    }
//                });
//                BarDataSet barDataSet = new BarDataSet(visitor,"Score");
//                barDataSet.setValueTextColor(Color.BLACK);
//                barDataSet.setValueTextSize(16f);
//
//                BarData barData = new BarData(barDataSet);
//                barData.setBarWidth(0.3f);
//                mBarChart.setTouchEnabled(true);
//                mBarChart.setDragEnabled(true);
//                mBarChart.setScaleEnabled(true);
//                mBarChart.setData(barData);
//                mBarChart.getDescription().setText("Toeic Achievement Weekly");
//                mBarChart.animateY(2000);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//        spnFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//        ArrayList<BarEntry> visitor = new ArrayList<>();
//        visitor.add(new BarEntry(0, 350));
//        visitor.add(new BarEntry(1, 450));
//        visitor.add(new BarEntry(2, 750));
//        visitor.add(new BarEntry(3, 950));
//        visitor.add(new BarEntry(4, 150));
//        visitor.add(new BarEntry(5, 550));
//        visitor.add(new BarEntry(6, 350));


//        ArrayList<String> theDays = new ArrayList<>();
//        theDays.add("T2");
//        theDays.add("T3");
//        theDays.add("T4");
//        theDays.add("T5");
//        theDays.add("T6");
//        theDays.add("T7");
//        theDays.add("CN");

//        BarDataSet barDataSet = new BarDataSet(visitor,"The Days");
//        barDataSet.setValueTextColor(Color.BLACK);
//        barDataSet.setValueTextSize(16f);
//
//        BarData barData = new BarData(barDataSet);
//
//        mBarChart.setTouchEnabled(true);
//        mBarChart.setDragEnabled(true);
//        mBarChart.setScaleEnabled(true);
//        mBarChart.setData(barData);
//        mBarChart.getDescription().setText("Toeic Achievement Weekly");
//        mBarChart.animateY(2000);

    }

    public void filterDataChart() {
        spnFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                reference = FirebaseDatabase.getInstance().getReference().child("Achievement").child(userID);//+"/Exam"
                if (spnFilter.getSelectedItemPosition() == i) {
                    Toast.makeText(achievement_user.this,"Exam",Toast.LENGTH_LONG).show();
                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            ArrayList<BarEntry> visitor = new ArrayList<BarEntry>();
                            if (snapshot.hasChildren()) {
                                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                    cls_achievement achie = dataSnapshot.getValue(cls_achievement.class);
                                    //float timee = Float.parseFloat(achie.getTime());

                                    visitor.add(new BarEntry(0,achie.getScore()));
                                }

                            } else {
                                mBarChart.clear();
                                mBarChart.invalidate();
                            }
                            XAxis xAxis = mBarChart.getXAxis();
                            YAxis yAxis = mBarChart.getAxisLeft();
                            YAxis yAxis1 = mBarChart.getAxisRight();
                            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                            xAxis.setValueFormatter(new ValueFormatter() {
                                @Override
                                public String getAxisLabel(float value, AxisBase axis) {
                                    SimpleDateFormat getTime = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
//                                    for(cls_achievement achie : list){
//                                        if(achie.getTime() != null){
//                                            Toast.makeText(achievement_user.this,"test",Toast.LENGTH_LONG).show();
//                                        }
//                                    }
                                    return getTime.format(new Date());
                                }
                            });
                            BarDataSet barDataSet = new BarDataSet(visitor, "Score");
                            barDataSet.setValueTextColor(Color.BLACK);
                            barDataSet.setValueTextSize(16f);

                            BarData barData = new BarData(barDataSet);
                            barData.setBarWidth(0.3f);
                            mBarChart.setTouchEnabled(true);
                            mBarChart.setDragEnabled(true);
                            mBarChart.setScaleEnabled(true);
                            mBarChart.setData(barData);
                            mBarChart.getDescription().setText("Toeic Achievement Weekly");
                            mBarChart.animateY(2000);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
                if(spnFilter.getSelectedItemPosition() != i){
                    Toast.makeText(achievement_user.this, "Null", Toast.LENGTH_SHORT).show();
//                    reference.addValueEventListener(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//                            Toast.makeText(achievement_user.this,"Part1",Toast.LENGTH_LONG).show();
//                            ArrayList<BarEntry> visitor = new ArrayList<BarEntry>();
//                            if (snapshot.hasChildren()) {
//                                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                                    cls_achievement achie = dataSnapshot.getValue(cls_achievement.class);
//                                    visitor.add(new BarEntry(0, achie.getScore()));
//                                }
//
//                            } else {
//                                mBarChart.clear();
//                                mBarChart.invalidate();
//                            }
//                            XAxis xAxis = mBarChart.getXAxis();
//                            YAxis yAxis = mBarChart.getAxisLeft();
//                            YAxis yAxis1 = mBarChart.getAxisRight();
//                            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//                            xAxis.setValueFormatter(new ValueFormatter() {
//                                @Override
//                                public String getAxisLabel(float value, AxisBase axis) {
//                                    SimpleDateFormat getTime = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
//                                    return getTime.format(new Date());
//                                }
//                            });
//                            BarDataSet barDataSet = new BarDataSet(visitor, "Score");
//                            barDataSet.setValueTextColor(Color.BLACK);
//                            barDataSet.setValueTextSize(16f);
//
//                            BarData barData = new BarData(barDataSet);
//                            barData.setBarWidth(0.3f);
//                            mBarChart.setTouchEnabled(true);
//                            mBarChart.setDragEnabled(true);
//                            mBarChart.setScaleEnabled(true);
//                            mBarChart.setData(barData);
//                            mBarChart.getDescription().setText("Toeic Achievement Weekly");
//                            mBarChart.animateY(2000);
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError error) {
//
//                        }
//                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    private int setSelectedSpinner(Spinner spinner , String o)
    {
        for (int i=0; i<spinner.getCount();i++)
        {
            if (spinner.getItemAtPosition(i).toString().equals(o))
            {
                return i;
            }
        }
        return 0;
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
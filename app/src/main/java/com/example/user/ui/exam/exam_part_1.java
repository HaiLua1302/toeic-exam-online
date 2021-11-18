package com.example.user.ui.exam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.user.R;

import com.example.user.ui.class_exam.cls_part_1;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class exam_part_1 extends  FragmentActivity {
    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 5;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter pagerAdapter;

    private DatabaseReference getData_Exam;
    private List<cls_part_1> clsPart_p1 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_question);

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.viewPage_exam);

        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(pagerAdapter);

        mPager.setPageTransformer(true,new DepthPageTransformer());
        getData_Exam = FirebaseDatabase.getInstance().getReference().child("Ques_P1");
        getData_Exam.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                clsPart_p1.clear();
                for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                    cls_part_1 clsPart1 = snapshot.getValue(cls_part_1.class);
                    clsPart_p1.add(clsPart1);
                    Toast.makeText(exam_part_1.this, clsPart_p1.toString(), Toast.LENGTH_SHORT).show();
                }
                // adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    //get data
    public ArrayList<cls_part_1> getData_p1()
    {
        return (ArrayList<cls_part_1>) clsPart_p1;
    }
    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new exam_slide_p1();
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
    public class DepthPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.75f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0f);

            } else if (position <= 0) { // [-1,0]
                // Use the default slide transition when moving to the left page
                view.setAlpha(1f);
                view.setTranslationX(0f);
                view.setScaleX(1f);
                view.setScaleY(1f);

            } else if (position <= 1) { // (0,1]
                // Fade the page out.
                view.setAlpha(1 - position);

                // Counteract the default slide transition
                view.setTranslationX(pageWidth * -position);

                // Scale the page down (between MIN_SCALE and 1)
                float scaleFactor = MIN_SCALE
                        + (1 - MIN_SCALE) * (1 - Math.abs(position));
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0f);
            }
        }
    }
}

    /*private DatabaseReference getData_Exam;
    private List<cls_part_1> clsPart_p1 = new ArrayList<>();

    private ViewPager2 mPage;
    private PagerAdapter mPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_question);
        *//*getSupportFragmentManager().setTitle("Phần 1");
        // calling the action bar
        ActionBar actionBar = getSupportActionBar();

        // Customize the back button
        actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24);

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);*//*
        mPage = findViewById(R.id.viewPage_exam);
        mPagerAdapter = new exam_part_1(getSupportFragmentManager());
        mPage.setAdapter(mPagerAdapter);
 *//*
        final ArrayList<String> arrLst_examP1 = new ArrayList();
        *//*

        getData_Exam = FirebaseDatabase.getInstance().getReference().child("Ques_P1");
        getData_Exam.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                clsPart_p1.clear();
                for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                    cls_part_1 clsPart1 = snapshot.getValue(cls_part_1.class);
                    clsPart_p1.add(clsPart1);
                    Toast.makeText(exam_part_1.this, clsPart_p1.toString(), Toast.LENGTH_SHORT).show();
                }
               // adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void onBackPressed(){
        if (mPage.getCurrentItem() == 0){
            super.onBackPressed();
        }else {
            mPage.setCurrentItem(mPage.getCurrentItem()-1);
        }
    }
    //get data
    public ArrayList<cls_part_1> getData_p1()
    {
        return (ArrayList<cls_part_1>) clsPart_p1;
    }
    // this event will enable the back
    // function to the button on press
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    };

    private class ScreenSlidePageAdapter extends FragmentStatePagerAdapter{

        public ScreenSlidePageAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return slide_exam_p1.create(position);
        }

        @Override
        public int getCount() {
            return 0;
        }
    }
}*/
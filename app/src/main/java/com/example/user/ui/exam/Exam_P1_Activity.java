package com.example.user.ui.exam;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import android.os.Handler;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.R;

import com.example.user.ui.adapter.adapter_exam_p1;
import com.example.user.ui.class_exam.cls_part_1;

import com.example.user.ui.listener.IFirebaseLoadDone;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class Exam_P1_Activity extends  FragmentActivity implements IFirebaseLoadDone, ValueEventListener {

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;
    private adapter_exam_p1 adapter_exam_p1;
    private MediaPlayer mediaPlayer;
    private ImageView imgPlayPause;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter pagerAdapter;

    private DatabaseReference getData_Exam;
    private List<cls_part_1> clsPart_p1 = new ArrayList<>();
    private IFirebaseLoadDone iFirebaseLoadDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main_question);

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.viewPage_exam);

        mPager.setPageTransformer(true,new DepthPageTransformer());
        mediaPlayer = new MediaPlayer();

        clsPart_p1 = new ArrayList<>();
        //init firebase
        getData_Exam = FirebaseDatabase.getInstance().getReference("Ques_P1");
        // init event
        iFirebaseLoadDone = this;

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

    @Override
    public void onFirebaseLoadSuccess(List<cls_part_1> cls_part_1List) {
        adapter_exam_p1 = new adapter_exam_p1(this,cls_part_1List);
        mPager.setAdapter(adapter_exam_p1);
        /*mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    mediaPlayer = null;
                } else return;

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });*/
    }

    @Override
    public void onFirebaseLoadFailed(String message) {

    }

    @Override
    public void onDataChange(@NonNull DataSnapshot datasnapshot) {
        List<cls_part_1> cls_part_1List = new ArrayList<>();
        for (DataSnapshot snapshot : datasnapshot.getChildren()) {
            cls_part_1List.add(snapshot.getValue(cls_part_1.class));
            iFirebaseLoadDone.onFirebaseLoadSuccess(cls_part_1List);
            //Toast.makeText(Exam_1_Activity.this, clsPart_p1.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }

    @Override
    protected void onDestroy() {
        getData_Exam.removeEventListener(this);
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData_Exam.addValueEventListener(this);
    }

    @Override
    protected void onStop() {
        getData_Exam.removeEventListener(this);
        super.onStop();
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
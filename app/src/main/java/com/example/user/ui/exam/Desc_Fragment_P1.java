package com.example.user.ui.exam;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.user.R;
import com.example.user.ui.adapter.adt_desc_P1;
import com.example.user.ui.class_exam.cls_part_1;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Desc_Fragment_P1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Desc_Fragment_P1 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private DatabaseReference getData_Exam;
    private Button prevQues_P1,nextQues_P1;
    String id_exam,url_audio;
    int pos;
    //private ViewPager viewPager_P1;
    private adt_desc_P1 adapterExamP1;
    RecyclerView Rec_Decs_P1;
    //private IFirebaseLoadDone iFirebaseLoadDone;
    private List<cls_part_1> clsPart_p1 ;

    public Desc_Fragment_P1(String id_exam, String url_audio,int pos) {
        this.id_exam = id_exam;
        this.url_audio = url_audio;
        this.pos = pos;
    }

    public Desc_Fragment_P1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Desc_Fragment_P1.
     */
    // TODO: Rename and change types and number of parameters
    public static Desc_Fragment_P1 newInstance(String param1, String param2) {
        Desc_Fragment_P1 fragment = new Desc_Fragment_P1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_desc_p1, container, false);
        ImageView img_pausePlayHolder;
        SeekBar seekBarAudioHolder;
        TextView txtCountDownHolder, txtTotal_timeHolder,url_audioHolder;
        MediaPlayer mediaPlayer = new MediaPlayer();

        img_pausePlayHolder = view.findViewById(R.id.img_play_1);
        clsPart_p1 = new ArrayList<>();
        //adapterExamP1 = new test();

        Rec_Decs_P1 = view.findViewById(R.id.Rec_Question_P1);
        Rec_Decs_P1.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

        LinearSnapHelper snapHelper = new LinearSnapHelper() {
            @Override
            public int findTargetSnapPosition(RecyclerView.LayoutManager lm, int velocityX, int velocityY) {
                View centerView = findSnapView(lm);
                if (centerView == null)
                    return RecyclerView.NO_POSITION;

                int position = lm.getPosition(centerView);
                int targetPosition = -1;
                if (lm.canScrollHorizontally()) {
                    if (velocityX < 0) {
                        targetPosition = position - 1;
                    } else {
                        targetPosition = position + 1;
                    }
                }

                if (lm.canScrollVertically()) {
                    if (velocityY < 0) {
                        targetPosition = position - 1;
                    } else {
                        targetPosition = position + 1;
                    }
                }

                final int firstItem = 0;
                final int lastItem = lm.getItemCount() - 1;
                targetPosition = Math.min(lastItem, Math.max(targetPosition, firstItem));
                return targetPosition;
            }
        };
        snapHelper.attachToRecyclerView(Rec_Decs_P1);
        // Instantiate a ViewPager and a PagerAdapter.
        // init event
        FirebaseRecyclerOptions<cls_part_1> options =
                new FirebaseRecyclerOptions.Builder<cls_part_1>()
                        .setQuery(FirebaseDatabase.getInstance().getReference("Cauhoi_Ques1").child(id_exam), cls_part_1.class)
                        .build();


        adapterExamP1 = new adt_desc_P1(options);

        adapterExamP1.setOnNextQuestionListener(currentQuestion -> {
            Rec_Decs_P1.smoothScrollToPosition(currentQuestion + 1);
        });
       //adapterExamP1.notifyDataSetChanged();
        Rec_Decs_P1.setAdapter(adapterExamP1);
        /*Rec_Decs_P1.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }
        });*/
        //viewPager_P1.setAdapter(adapterExamP1);

        /*img_pausePlayHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    String url = url_audio; // your URL here

                    mediaPlayer.setAudioAttributes(
                            new AudioAttributes.Builder()
                                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                                    .setUsage(AudioAttributes.USAGE_MEDIA)
                                    .build()
                    );
                    img_pausePlayHolder.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                }else {
                    mediaPlayer.start();
                    img_pausePlayHolder.setImageResource(R.drawable.ic_baseline_pause_24);
                }
            }
        });*/

        return  view;
    }


    private String milliSecondsToTimer(long milliSecons){
        String timeString = "";
        String secondString = "";

        int hours = (int) (milliSecons / (1000*60*60));
        int mins = (int) (milliSecons % (1000*60*60)) / (1000*60);
        int secs = (int) (milliSecons % (1000*60*60) % (1000*60) / 1000);

        if (hours > 0){
            timeString = hours + ":";
        }
        if (secs < 10){
            secondString = "0" + secs;
        } else {  secondString = "" + secs;     }
        timeString = timeString + mins + ":" + secondString;
        return timeString;
    }


    @Override
    public void onStart() {
        super.onStart();
        adapterExamP1.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapterExamP1.stopListening();
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
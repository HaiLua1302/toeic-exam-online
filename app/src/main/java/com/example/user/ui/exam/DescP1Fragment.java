package com.example.user.ui.exam;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.user.R;
import com.example.user.ui.adapter.AdtDescP1;
import com.example.user.ui.class_exam.ClsPartP1;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DescP1Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DescP1Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private MediaPlayer mediaPlayer;
    private SeekBar seekBarPlayer;
    private ImageView imageViewPlayPause;
    private TextView txtCurrentTime , txtTotalTime;
    private Handler handler = new Handler();

    String id_exam,url_audio;
    int pos;
    private AdtDescP1 adapterExamP1;
    RecyclerView Rec_Decs_P1;

    public DescP1Fragment(String id_exam, String url_audio, int pos) {
        this.id_exam = id_exam;
        this.url_audio = url_audio;
        this.pos = pos;
    }

    public DescP1Fragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static DescP1Fragment newInstance(String param1, String param2) {
        DescP1Fragment fragment = new DescP1Fragment();
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

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_desc_p1, container, false);

        imageViewPlayPause = view.findViewById(R.id.img_play_1);
        seekBarPlayer = view.findViewById(R.id.playerSeekBar1);
        txtCurrentTime = view.findViewById(R.id.txtCurrentTime1);
        txtTotalTime = view.findViewById(R.id.txtTimeTotal1);

        seekBarPlayer.setMax(100);
        mediaPlayer = new MediaPlayer();

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
        FirebaseRecyclerOptions<ClsPartP1> options =
                new FirebaseRecyclerOptions.Builder<ClsPartP1>()
                        .setQuery(FirebaseDatabase.getInstance().getReference("Cauhoi_Ques1").child(id_exam), ClsPartP1.class)
                        .build();

        adapterExamP1 = new AdtDescP1(options);

        //next question
        adapterExamP1.setOnNextQuestionListener(currentQuestion -> {
            Rec_Decs_P1.smoothScrollToPosition(currentQuestion + 1);
        });

        Rec_Decs_P1.setAdapter(adapterExamP1);

        imageViewPlayPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    handler.removeCallbacks(runnable);
                    mediaPlayer.pause();
                    imageViewPlayPause.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                }else {
                    mediaPlayer.start();
                    imageViewPlayPause.setImageResource(R.drawable.ic_baseline_pause_24);
                    updateSeekbar();
                }
            }
        });
        prepareMediaPlayer();
        seekBarPlayer.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                SeekBar seekBar = (SeekBar) v;
                int playPostion = (mediaPlayer.getDuration()/100)*seekBar.getProgress();
                mediaPlayer.seekTo(playPostion);
                txtCurrentTime.setText(milliSecondsToTimer(mediaPlayer.getCurrentPosition()));
                return false;
            }
        });
        mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                seekBarPlayer.setSecondaryProgress(percent);
            }
        });


//        Toast.makeText(getContext(),url_audio,Toast.LENGTH_LONG).show();
        return  view;
    }

    private void prepareMediaPlayer(){
        try{
            mediaPlayer.setDataSource(url_audio);
            mediaPlayer.prepare();
            txtTotalTime.setText(milliSecondsToTimer(mediaPlayer.getDuration()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            updateSeekbar();
            long currentDuration = mediaPlayer.getCurrentPosition();
            txtCurrentTime.setText(milliSecondsToTimer(currentDuration));
        }
    };

    private void updateSeekbar(){
        if(mediaPlayer.isPlaying()){
            seekBarPlayer.setProgress((int)(((float)mediaPlayer.getCurrentPosition() / mediaPlayer.getDuration()) * 100));
            handler.postDelayed(runnable,1000);
        }
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

}
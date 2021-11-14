package com.example.user.ui.exam2;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.R;
import com.example.user.ui.adapter.AdtDescP2;
import com.example.user.ui.class_exam.ClsPartP2;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DescFragmentP2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DescFragmentP2 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private MediaPlayer mediaPlayer;
    private SeekBar seekBarPlayer2;
    private ImageView imageViewPlayPaus2;
    private TextView txtCurrentTime2 , txtTotalTime2;
    private Handler handler = new Handler();

    String id_exam2,url_audio2;
    private AdtDescP2 adapterExamP2;

    RecyclerView Rec_Decs_P2;


    public DescFragmentP2() {
        // Required empty public constructor
    }

    public DescFragmentP2(String id_exam, String url_audio) {
        this.id_exam2 = id_exam;
        this.url_audio2 = url_audio;
    }


    // TODO: Rename and change types and number of parameters
    public static DescFragmentP2 newInstance(String param1, String param2) {
        DescFragmentP2 fragment = new DescFragmentP2();
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
        View view = inflater.inflate(R.layout.fragment_desc_p2, container, false);

        Toast.makeText(getContext(),id_exam2,Toast.LENGTH_LONG).show();

        imageViewPlayPaus2 = view.findViewById(R.id.imgPlayP2);
        seekBarPlayer2 = view.findViewById(R.id.playerSeekBar2);
        txtCurrentTime2 = view.findViewById(R.id.txtCurrentTime2);
        txtTotalTime2 = view.findViewById(R.id.txtTimeTotal2);

        seekBarPlayer2.setMax(100);
        mediaPlayer = new MediaPlayer();

        //truyen du lieu adapter
        Rec_Decs_P2 = view.findViewById(R.id.recQuestionP2);
        Rec_Decs_P2.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

        LinearSnapHelper snapHelper = new LinearSnapHelper(){
            @Override
            public int findTargetSnapPosition(RecyclerView.LayoutManager lm, int velocityX, int velocityY){
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

        snapHelper.attachToRecyclerView(Rec_Decs_P2);

//        getdat
        FirebaseRecyclerOptions<ClsPartP2> options = new FirebaseRecyclerOptions.Builder<ClsPartP2>()
                .setQuery(FirebaseDatabase.getInstance().getReference("List_Ques2").child(id_exam2), ClsPartP2.class)
                .build();

        adapterExamP2 = new AdtDescP2(options);

        //next question
        adapterExamP2.setOnNextQuestionListener(currentQuestion -> {
            Rec_Decs_P2.smoothScrollToPosition(currentQuestion + 1);
        });

        Rec_Decs_P2.setAdapter(adapterExamP2);

        imageViewPlayPaus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    handler.removeCallbacks(runnable);
                    mediaPlayer.pause();
                    imageViewPlayPaus2.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                }else {
                    mediaPlayer.start();
                    imageViewPlayPaus2.setImageResource(R.drawable.ic_baseline_pause_24);
                    updateSeekbar();
                }
            }
        });

        prepareMediaPlayer();
        seekBarPlayer2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                SeekBar seekBar = (SeekBar) v;
                int playPostion = (mediaPlayer.getDuration()/100)*seekBar.getProgress();
                mediaPlayer.seekTo(playPostion);
                txtCurrentTime2.setText(milliSecondsToTimer(mediaPlayer.getCurrentPosition()));
                return false;
            }
        });

        mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                seekBarPlayer2.setSecondaryProgress(percent);
            }
        });

        return view;
    }

    private void prepareMediaPlayer(){
        try{
            mediaPlayer.setDataSource(url_audio2);
            mediaPlayer.prepare();
            txtTotalTime2.setText(milliSecondsToTimer(mediaPlayer.getDuration()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            updateSeekbar();
            long currentDuration = mediaPlayer.getCurrentPosition();
            txtCurrentTime2.setText(milliSecondsToTimer(currentDuration));
        }
    };

    private void updateSeekbar(){
        if(mediaPlayer.isPlaying()){
            seekBarPlayer2.setProgress((int)(((float)mediaPlayer.getCurrentPosition() / mediaPlayer.getDuration()) * 100));
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
        adapterExamP2.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapterExamP2.stopListening();
    }

}
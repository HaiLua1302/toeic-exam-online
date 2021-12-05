package com.example.user.ui.exam2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
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

import com.example.user.R;
import com.example.user.ui.adapterUser.AdtDescP2;
import com.example.user.ui.classExam.ClsPartP2;
import com.example.user.ui.exam1.DescP1Fragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DescP2Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DescP2Fragment extends Fragment implements AdtDescP2.OnNextQuestionsListener{

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

    String keyExam;
    String url_audio;
    private AdtDescP2 adtDescP2;

    private List<ClsPartP2>clsPartP2s;
    RecyclerView recDecsP2;
    private DescP2Fragment.OnFragmentInteractionListener mListener;

    public DescP2Fragment() {
        // Required empty public constructor
    }

    public DescP2Fragment(DescP2Fragment.OnFragmentInteractionListener mListener) {
        this.mListener = mListener;
    }

    public DescP2Fragment(String keyExam) {
        this.keyExam = keyExam;
    }

    // TODO: Rename and change types and number of parameters
    public static DescP2Fragment newInstance(String param1, String param2) {
        DescP2Fragment fragment = new DescP2Fragment();
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
        if (mListener != null) {
            mListener.onFragmentInteraction("Part II : Question - Response");
        }
        imageViewPlayPaus2 = view.findViewById(R.id.imgPlayP2);
        seekBarPlayer2 = view.findViewById(R.id.playerSeekBar2);
        txtCurrentTime2 = view.findViewById(R.id.txtCurrentTime2);
        txtTotalTime2 = view.findViewById(R.id.txtTimeTotal2);

        seekBarPlayer2.setMax(100);
        mediaPlayer = new MediaPlayer();

        //truyen du lieu adapter
        recDecsP2 = view.findViewById(R.id.recQuestionP2);
        recDecsP2.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

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

        snapHelper.attachToRecyclerView(recDecsP2);

        // Instantiate a ViewPager and a PagerAdapter.
        // init event
        clsPartP2s = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("List_Ques2").child(keyExam);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    ClsPartP2 partP2 = dataSnapshot.getValue(ClsPartP2.class);
                    clsPartP2s.add(partP2);
                }
                adtDescP2 = new AdtDescP2(clsPartP2s);
                recDecsP2.setAdapter(adtDescP2);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        recDecsP2.setAdapter(adtDescP2);

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
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Ques_2").child(keyExam);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                url_audio = snapshot.child("url_audio").getValue().toString();
                try {
                    mediaPlayer.setDataSource(url_audio);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                txtTotalTime2.setText(milliSecondsToTimer(mediaPlayer.getDuration()));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
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
    public void shouldNextQuestions(int currentPostion) {
        //next question
        recDecsP2.smoothScrollToPosition(currentPostion + 1);
    }


    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (DescP2Fragment.OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(String title);
    }
}
package com.example.user.ui.exam1;

import android.annotation.SuppressLint;
import android.app.Activity;
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

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.user.R;
import com.example.user.ui.adapter.AdtDescP1;
import com.example.user.ui.classExam.ClsPartP1;
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
 * Use the {@link DescP1Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DescP1Fragment extends Fragment implements AdtDescP1.OnNextQuestionListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private List<ClsPartP1> clsPartP1s;

    private MediaPlayer mediaPlayer;
    private SeekBar seekBarPlayer;
    private ImageView imageViewPlayPause;
    private TextView txtCurrentTime , txtTotalTime;
    private Handler handler = new Handler();

   // String keyExam,url_audio;
    private String keyExam,url_audio;
    private AdtDescP1 adtDescP1;
    private AdtDescP1 adtDescP1Next = new AdtDescP1();
    RecyclerView recDecsP1;

    public DescP1Fragment(String keyExam) {
        this.keyExam = keyExam;
    }

    private OnFragmentInteractionListener mListener;

    public DescP1Fragment(OnFragmentInteractionListener mListener) {
        this.mListener = mListener;
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

        //  getActivity().getActionBar().setTitle("Part I : Picture Description");
        if (mListener != null) {
            mListener.onFragmentInteraction("Part I : Picture Description");
        }

        imageViewPlayPause = view.findViewById(R.id.img_play_1);
        seekBarPlayer = view.findViewById(R.id.playerSeekBar1);
        txtCurrentTime = view.findViewById(R.id.txtCurrentTime1);
        txtTotalTime = view.findViewById(R.id.txtTimeTotal1);

        seekBarPlayer.setMax(100);
        mediaPlayer = new MediaPlayer();

        recDecsP1 = view.findViewById(R.id.recQuestionP1);
        recDecsP1.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

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

        snapHelper.attachToRecyclerView(recDecsP1);

        // Instantiate a ViewPager and a PagerAdapter.
        // init event
        clsPartP1s = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("List_Ques1").child(keyExam);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    ClsPartP1 partP1 = dataSnapshot.getValue(ClsPartP1.class);
                    clsPartP1s.add(partP1);
                }
                adtDescP1 = new AdtDescP1(clsPartP1s);
                recDecsP1.setAdapter(adtDescP1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
       /* FirebaseRecyclerOptions<ClsPartP1> options =
                new FirebaseRecyclerOptions.Builder<ClsPartP1>()
                        .setQuery(FirebaseDatabase.getInstance().getReference("Cauhoi_Ques1").child(keyExam), ClsPartP1.class)
                        .build();

        adapterExamP1 = new AdtDescP1(options);*/

      /*  //next question
        adtDescP1.setOnNextQuestionListener(currentQuestion -> {

        });*/
      this.adtDescP1Next.setOnItemClickListener(this::shouldNextQuestion);

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
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Ques_1").child(keyExam);
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
                txtTotalTime.setText(milliSecondsToTimer(mediaPlayer.getDuration()));
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


    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
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

    @Override
    public void shouldNextQuestion(int currentQuestion) {
        recDecsP1.smoothScrollToPosition(currentQuestion + 1);
    }

    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(String title);
    }

}
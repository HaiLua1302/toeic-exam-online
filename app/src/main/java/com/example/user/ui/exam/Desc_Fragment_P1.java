package com.example.user.ui.exam;

import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.user.R;
import com.example.user.ui.adapter.adapter_desc_p1;
import com.example.user.ui.adapter.adapter_exam_list_p1;
import com.example.user.ui.adapter.adapter_exam_p1;
import com.example.user.ui.class_exam.cls_part_1;
import com.example.user.ui.class_exam.list_exam_1;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;

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

    String id_exam,url_audio;
    private ViewPager viewPager_P1;
    private PagerAdapter pagerAdapter;
    private adapter_exam_p1 adapterExamP1;

    public Desc_Fragment_P1(String id_exam, String url_audio) {
        this.id_exam = id_exam;
        this.url_audio = url_audio;
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
        viewPager_P1 = view.findViewById(R.id.viewPage_exam);
        pagerAdapter = new adapter_desc_p1();
        viewPager_P1.setAdapter(pagerAdapter);

        FirebaseRecyclerOptions<cls_part_1> options =
                new FirebaseRecyclerOptions.Builder<cls_part_1>()
                        .setQuery(FirebaseDatabase.getInstance().getReference("Cauhoi_Ques_1").child(id_exam), cls_part_1.class)
                        .build();


        img_pausePlayHolder = view.findViewById(R.id.img_play_1);
        img_pausePlayHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img_pausePlayHolder.setImageResource(R.drawable.ic_baseline_pause_24);
                String url = url_audio; // your URL here
                MediaPlayer mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioAttributes(
                        new AudioAttributes.Builder()
                                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                                .setUsage(AudioAttributes.USAGE_MEDIA)
                                .build()
                );

                try {
                    mediaPlayer.setDataSource(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    mediaPlayer.prepare(); // might take long! (for buffering, etc)
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mediaPlayer.start();
            }
        });

        return  view;
    }
    
}
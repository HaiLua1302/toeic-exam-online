package com.example.user.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.jean.jcplayer.model.JcAudio;
import com.example.jean.jcplayer.view.JcPlayerView;
import com.example.user.R;
import com.example.user.ui.class_exam.cls_part_1;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class adapter_exam_p1 extends PagerAdapter {

    Context context;
    List<cls_part_1> cls_part_1sList;
    LayoutInflater inflater_ex_p1;

    private Button btn_A,btn_B,btn_C,btn_D;
    private TextView txttimeCurrent, txtTotalDuration;
    private SeekBar playSeekBar;
    private MediaPlayer mediaPlayer;
    private Handler handler = new Handler();
    private ImageView imgPlayPause;


    public adapter_exam_p1(Context context, List<cls_part_1> cls_part_1sList) {
        this.context = context;
        this.cls_part_1sList = cls_part_1sList;
        inflater_ex_p1 = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return cls_part_1sList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ((ViewPager)container).removeView(((View)object));
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = inflater_ex_p1.inflate(R.layout.exam_p1,container,false);

       /* imgPlayPause = view.findViewById(R.id.img_play_1);
        txttimeCurrent = view.findViewById(R.id.txt_time_play_1);
        txtTotalDuration = view.findViewById(R.id.txt_time_total_1);
        playSeekBar = view.findViewById(R.id.skbar_audio_1);*/
       /* mediaPlayer = new MediaPlayer();
        playSeekBar.setMax(100);*/

        btn_A = (Button) view.findViewById(R.id.btn_A1);
        btn_B = (Button) view.findViewById(R.id.btn_B1);
        btn_C = (Button) view.findViewById(R.id.btn_C1);
        btn_D = (Button) view.findViewById(R.id.btn_D1);

        btn_A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_A.setBackgroundColor(R.drawable.select_answer);

            }
        });

        ImageView exam_img = view.findViewById(R.id.img_p1);


        TextView txt_id = view.findViewById(R.id.txt_NumQuestion);

        /*imgPlayPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    imgPlayPause.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                }else {
                    mediaPlayer.start();
                    imgPlayPause.setImageResource(R.drawable.ic_baseline_pause_24);
                }

            }
        });*/



        //Picasso.get().load(cls_part_1sList.get(position).getUrl_IMG()).into(exam_img);

        txt_id.setText("Câu " + (position + 1));

        container.addView(view);
        return view;
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

   /* private void playAudio(){
        String url = cls_part_1sList.get(0).getUrl_Audio(); // your URL here
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
    }*/
}

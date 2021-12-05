package com.example.user.ui.adapterUser;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.user.R;
import com.example.user.ui.classExam.ClsListQuestionP4;
import com.example.user.ui.classExam.ClsRecExamP4;
import com.example.user.ui.exam4.ResultP4Activity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdtDescP4 extends FirebaseRecyclerAdapter<ClsRecExamP4,AdtDescP4.DescP4Holder> {

    public AdtDescP4(@NonNull FirebaseRecyclerOptions<ClsRecExamP4> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull AdtDescP4.DescP4Holder holder, int position, @NonNull ClsRecExamP4 model) {
        holder.setData(model);
        holder.showBtnSubmit(position);
    }

    @NonNull
    @Override
    public AdtDescP4.DescP4Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_exam_p4,parent,false);
        return new AdtDescP4.DescP4Holder(view);
    }

    public class DescP4Holder extends RecyclerView.ViewHolder {
        RecyclerView recyclerViewListQuestionP4Holder;
        AdtDescListQuestionP4 listQuestionP4Holder;
        private List<ClsListQuestionP4> questionP4Holder ;


        MediaPlayer mediaPlayerP4Holder;
        SeekBar seekBarPlayerP4Holder;
        ImageView imageViewPlayPauseP4Holder;
        TextView txtCurrentTimeP4Holder, txtTotalTimeP4Holder;
        Handler handlerP4Holder = new Handler();
        Button btnSubmit4Holder;

        public DescP4Holder(@NonNull View itemView) {
            super(itemView);
            recyclerViewListQuestionP4Holder = itemView.findViewById(R.id.recListQuestionP4);
            questionP4Holder = new ArrayList<>();

            mediaPlayerP4Holder = new MediaPlayer();
            seekBarPlayerP4Holder = itemView.findViewById(R.id.playerSeekBarP4);
            imageViewPlayPauseP4Holder = itemView.findViewById(R.id.imgPlayP4);
            txtCurrentTimeP4Holder = itemView.findViewById(R.id.txtCurrentTimeP4);
            txtTotalTimeP4Holder = itemView.findViewById(R.id.txtTimeTotalP4);
            btnSubmit4Holder = itemView.findViewById(R.id.btnSubmitP4);
            btnSubmit4Holder.setVisibility(View.INVISIBLE);
            btnSubmit4Holder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), ResultP4Activity.class);
                    itemView.getContext().startActivity(intent);
                }
            });
        }
        private void showBtnSubmit(int pos)
        {
            if((pos+1) == getItemCount()){
                btnSubmit4Holder.setVisibility(View.VISIBLE);
            }
        }
        @SuppressLint("ClickableViewAccessibility")
        private void setData(ClsRecExamP4 model){

            String child = model.getId_exam() + "/" + model.getId_question() + "/Question";

            DatabaseReference ref1 = FirebaseDatabase.getInstance()
                    .getReference("List_Ques4").child(child);

            recyclerViewListQuestionP4Holder.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
            ref1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        ClsListQuestionP4 recP4 = dataSnapshot.getValue(ClsListQuestionP4.class);
                        questionP4Holder.add(recP4);
                    }
                    listQuestionP4Holder = new AdtDescListQuestionP4(questionP4Holder);
                    recyclerViewListQuestionP4Holder.setAdapter(listQuestionP4Holder);
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            imageViewPlayPauseP4Holder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mediaPlayerP4Holder.isPlaying()){
                        handlerP4Holder.removeCallbacks(runnable);
                        mediaPlayerP4Holder.pause();
                        imageViewPlayPauseP4Holder.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                    }else {
                        mediaPlayerP4Holder.start();
                        imageViewPlayPauseP4Holder.setImageResource(R.drawable.ic_baseline_pause_24);
                        updateSeekbar();
                    }
                }
            });

            prepareMediaPlayer(model);
            seekBarPlayerP4Holder.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    SeekBar seekBar = (SeekBar) v;
                    int playPostion = (mediaPlayerP4Holder.getDuration()/100)*seekBar.getProgress();
                    mediaPlayerP4Holder.seekTo(playPostion);
                    txtCurrentTimeP4Holder.setText(milliSecondsToTimer(mediaPlayerP4Holder.getCurrentPosition()));
                    return false;
                }
            });

            mediaPlayerP4Holder.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
                @Override
                public void onBufferingUpdate(MediaPlayer mp, int percent) {
                    seekBarPlayerP4Holder.setSecondaryProgress(percent);
                }
            });

        }

        private void prepareMediaPlayer(ClsRecExamP4 model){
            try{
                mediaPlayerP4Holder.setDataSource(model.getUrl_audio());
                mediaPlayerP4Holder.prepare();
                txtTotalTimeP4Holder.setText(milliSecondsToTimer(mediaPlayerP4Holder.getDuration()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private Runnable runnable = new Runnable() {
            @Override
            public void run() {
                updateSeekbar();
                long currentDuration = mediaPlayerP4Holder.getCurrentPosition();
                txtCurrentTimeP4Holder.setText(milliSecondsToTimer(currentDuration));
            }
        };

        private void updateSeekbar(){
            if(mediaPlayerP4Holder.isPlaying()){
                seekBarPlayerP4Holder.setProgress((int)(((float) mediaPlayerP4Holder.getCurrentPosition() / mediaPlayerP4Holder.getDuration()) * 100));
                handlerP4Holder.postDelayed(runnable,1000);
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
    }
}

package com.example.user.ui.adapter;

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
import com.example.user.ui.fullExam.TutorialFullExamP5Activity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdtDescFullP4 extends RecyclerView.Adapter<AdtDescFullP4.DescFullP4Holder> {
    private List<ClsRecExamP4> clsRecExamP4s;

    public AdtDescFullP4() {
    }

    public AdtDescFullP4(List<ClsRecExamP4> clsRecExamP4s) {
        this.clsRecExamP4s = clsRecExamP4s;
    }

    @NonNull
    @Override
    public AdtDescFullP4.DescFullP4Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_exam_p3,parent,false);
        return new AdtDescFullP4.DescFullP4Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdtDescFullP4.DescFullP4Holder holder, int position) {
        holder.setData(position);
        holder.showBtnSubmit(position);
    }

    @Override
    public int getItemCount() {
        return clsRecExamP4s.size();
    }

    public class DescFullP4Holder extends RecyclerView.ViewHolder {
        RecyclerView recyclerViewListQuestionP4Holder;
        AdtDescFullListQuestionP4 listQuestionP4Holder;
        Button btnSubmitHolder;
        private List<ClsListQuestionP4> questionP4sHolder ;

        MediaPlayer mediaPlayer4Holder;
        SeekBar seekBarPlayer4Holder;
        ImageView imageViewPlayPause4Holder;
        TextView txtCurrentTime4Holder, txtTotalTime4Holder;
        Handler handlerHolder = new Handler();
        
         public DescFullP4Holder(@NonNull View itemView) {
            super(itemView);

             recyclerViewListQuestionP4Holder = itemView.findViewById(R.id.recListQuestionP3);
             questionP4sHolder = new ArrayList<>();
             btnSubmitHolder = itemView.findViewById(R.id.btnSubmitP3);
             btnSubmitHolder.setText("NEXT");
             btnSubmitHolder.setVisibility(View.INVISIBLE);

             mediaPlayer4Holder = new MediaPlayer();
             seekBarPlayer4Holder = itemView.findViewById(R.id.playerSeekBar3);
             imageViewPlayPause4Holder = itemView.findViewById(R.id.imgPlayP3);
             txtCurrentTime4Holder = itemView.findViewById(R.id.txtCurrentTime3);
             txtTotalTime4Holder = itemView.findViewById(R.id.txtTimeTotal3);

             btnSubmitHolder.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     Intent intent = new Intent(itemView.getContext(), TutorialFullExamP5Activity.class);
                     itemView.getContext().startActivity(intent);
                 }
             });
        }
        private void showBtnSubmit(int pos)
        {
            if((pos+1) == getItemCount()){
                btnSubmitHolder.setVisibility(View.VISIBLE);
            }
        }
        @SuppressLint("ClickableViewAccessibility")
        private void setData (int pos){
            String child = clsRecExamP4s.get(pos).getId_exam() + "/" + clsRecExamP4s.get(pos).getId_question() + "/Question";
            DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference("List_Ques4").child(child);
            recyclerViewListQuestionP4Holder.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
            ref1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        ClsListQuestionP4 recP4 = dataSnapshot.getValue(ClsListQuestionP4.class);
                        questionP4sHolder.add(recP4);
                    }
                    listQuestionP4Holder = new AdtDescFullListQuestionP4(questionP4sHolder);
                    recyclerViewListQuestionP4Holder.setAdapter(listQuestionP4Holder);
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            imageViewPlayPause4Holder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mediaPlayer4Holder.isPlaying()){
                        handlerHolder.removeCallbacks(runnable);
                        mediaPlayer4Holder.pause();
                        imageViewPlayPause4Holder.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                    }else {
                        mediaPlayer4Holder.start();
                        imageViewPlayPause4Holder.setImageResource(R.drawable.ic_baseline_pause_24);
                        updateSeekbar();
                    }
                }
            });

            prepareMediaPlayer(pos);
            seekBarPlayer4Holder.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    SeekBar seekBar = (SeekBar) v;
                    int playPostion = (mediaPlayer4Holder.getDuration()/100)*seekBar.getProgress();
                    mediaPlayer4Holder.seekTo(playPostion);
                    txtCurrentTime4Holder.setText(milliSecondsToTimer(mediaPlayer4Holder.getCurrentPosition()));
                    return false;
                }
            });

            mediaPlayer4Holder.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
                @Override
                public void onBufferingUpdate(MediaPlayer mp, int percent) {
                    seekBarPlayer4Holder.setSecondaryProgress(percent);
                }
            });

        }

        private void prepareMediaPlayer(int pos){
            try{
                mediaPlayer4Holder.setDataSource(clsRecExamP4s.get(pos).getUrl_audio());
                mediaPlayer4Holder.prepare();
                txtTotalTime4Holder.setText(milliSecondsToTimer(mediaPlayer4Holder.getDuration()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private Runnable runnable = new Runnable() {
            @Override
            public void run() {
                updateSeekbar();
                long currentDuration = mediaPlayer4Holder.getCurrentPosition();
                txtCurrentTime4Holder.setText(milliSecondsToTimer(currentDuration));
            }
        };

        private void updateSeekbar(){
            if(mediaPlayer4Holder.isPlaying()){
                seekBarPlayer4Holder.setProgress((int)(((float) mediaPlayer4Holder.getCurrentPosition() / mediaPlayer4Holder.getDuration()) * 100));
                handlerHolder.postDelayed(runnable,1000);
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

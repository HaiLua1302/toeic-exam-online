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
import com.example.user.ui.classExam.ClsListQuestionP3;
import com.example.user.ui.classExam.ClsRecExamP3;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdtDescFullP3 extends RecyclerView.Adapter<AdtDescFullP3.DescFullP3Holder> {
    private List<ClsRecExamP3> clsRecExamP3s;

    public AdtDescFullP3() {
    }

    public AdtDescFullP3(List<ClsRecExamP3> clsRecExamP3s) {
        this.clsRecExamP3s = clsRecExamP3s;
    }

    @NonNull
    @Override
    public DescFullP3Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_exam_p3,parent,false);
        return new DescFullP3Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DescFullP3Holder holder, int position) {
        holder.setData(position);
        holder.showBtnSubmit(position);
    }

    @Override
    public int getItemCount() {
        return clsRecExamP3s.size();
    }

    public class DescFullP3Holder extends RecyclerView.ViewHolder {
        RecyclerView recyclerViewListQuestionP3Holder;
        AdtDescFullListQuestionP3 listQuestionP3Holder;
        Button btnSubmitHolder;
        private List<ClsListQuestionP3> questionP3sHolder ;

        MediaPlayer mediaPlayer3Holder;
        SeekBar seekBarPlayer3Holder;
        ImageView imageViewPlayPause3Holder;
        TextView txtCurrentTime3Holder, txtTotalTime3Holder;
        Handler handlerHolder = new Handler();

        public DescFullP3Holder(@NonNull View itemView) {
            super(itemView);
            recyclerViewListQuestionP3Holder = itemView.findViewById(R.id.recListQuestionP3);
            questionP3sHolder = new ArrayList<>();
            btnSubmitHolder = itemView.findViewById(R.id.btnSubmitP3);
            btnSubmitHolder.setVisibility(View.INVISIBLE);
            btnSubmitHolder.setText("NEXT");

            mediaPlayer3Holder = new MediaPlayer();
            seekBarPlayer3Holder = itemView.findViewById(R.id.playerSeekBar3);
            imageViewPlayPause3Holder = itemView.findViewById(R.id.imgPlayP3);
            txtCurrentTime3Holder = itemView.findViewById(R.id.txtCurrentTime3);
            txtTotalTime3Holder = itemView.findViewById(R.id.txtTimeTotal3);

            btnSubmitHolder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   /* Intent intent = new Intent(itemView.getContext(), TutorialFullExamP4Activity.class);
                    itemView.getContext().startActivity(intent);*/
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
            String child = clsRecExamP3s.get(pos).getId_exam() + "/" + clsRecExamP3s.get(pos).getId_question() + "/Question";
            DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference("List_Ques3").child(child);
            recyclerViewListQuestionP3Holder.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
            ref1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        ClsListQuestionP3 recP3 = dataSnapshot.getValue(ClsListQuestionP3.class);
                        questionP3sHolder.add(recP3);
                    }
                    listQuestionP3Holder = new AdtDescFullListQuestionP3(questionP3sHolder);
                    recyclerViewListQuestionP3Holder.setAdapter(listQuestionP3Holder);
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            imageViewPlayPause3Holder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mediaPlayer3Holder.isPlaying()){
                        handlerHolder.removeCallbacks(runnable);
                        mediaPlayer3Holder.pause();
                        imageViewPlayPause3Holder.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                    }else {
                        mediaPlayer3Holder.start();
                        imageViewPlayPause3Holder.setImageResource(R.drawable.ic_baseline_pause_24);
                        updateSeekbar();
                    }
                }
            });

            prepareMediaPlayer(pos);
            seekBarPlayer3Holder.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    SeekBar seekBar = (SeekBar) v;
                    int playPostion = (mediaPlayer3Holder.getDuration()/100)*seekBar.getProgress();
                    mediaPlayer3Holder.seekTo(playPostion);
                    txtCurrentTime3Holder.setText(milliSecondsToTimer(mediaPlayer3Holder.getCurrentPosition()));
                    return false;
                }
            });

            mediaPlayer3Holder.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
                @Override
                public void onBufferingUpdate(MediaPlayer mp, int percent) {
                    seekBarPlayer3Holder.setSecondaryProgress(percent);
                }
            });

        }

        private void prepareMediaPlayer(int pos){
            try{
                mediaPlayer3Holder.setDataSource(clsRecExamP3s.get(pos).getUrl_audio());
                mediaPlayer3Holder.prepare();
                txtTotalTime3Holder.setText(milliSecondsToTimer(mediaPlayer3Holder.getDuration()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private Runnable runnable = new Runnable() {
            @Override
            public void run() {
                updateSeekbar();
                long currentDuration = mediaPlayer3Holder.getCurrentPosition();
                txtCurrentTime3Holder.setText(milliSecondsToTimer(currentDuration));
            }
        };

        private void updateSeekbar(){
            if(mediaPlayer3Holder.isPlaying()){
                seekBarPlayer3Holder.setProgress((int)(((float) mediaPlayer3Holder.getCurrentPosition() / mediaPlayer3Holder.getDuration()) * 100));
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

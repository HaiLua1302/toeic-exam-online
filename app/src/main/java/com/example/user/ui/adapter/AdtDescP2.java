package com.example.user.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.user.R;
import com.example.user.ui.classExam.ClsPartP2;
import com.example.user.ui.classUser.cls_achievement;
import com.example.user.ui.exam1.ResultP1Activity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AdtDescP2 extends RecyclerView.Adapter<AdtDescP2.DescP2Holder> {

    private List<ClsPartP2> clsPartP2List;
    //click chuyen cau hoi callback
    private OnNextQuestionsListener onNextQuestionListener;

    public AdtDescP2() {
    }

    public AdtDescP2(List<ClsPartP2> clsPartP2List) {
        this.clsPartP2List = clsPartP2List;
    }

    public AdtDescP2(OnNextQuestionsListener onNextQuestionListener) {
        this.onNextQuestionListener = onNextQuestionListener;
    }

    @NonNull
    @Override
    public DescP2Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.exam_p2,parent,false);
        return new DescP2Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DescP2Holder holder, int position) {
        holder.setData(position);
        holder.invisibleSubmit(position);

    }

    @Override
    public int getItemCount() {
        return clsPartP2List.size();
    }

    /*public AdtDescP2(@NonNull FirebaseRecyclerOptions<ClsPartP2> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull DescP2Holder holder, int position, @NonNull ClsPartP2 model) {
        holder.setData(model,position);
    }

    @NonNull
    @Override
    public DescP2Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.exam_p2,parent,false);
        return new AdtDescP2.DescP2Holder(view);
    }

    public class DescP2Holder extends RecyclerView.ViewHolder {

        Button a2Holder,b2Holder,c2Holder,SubmitHolder2;
        TextView numQuestionHolder;
        int correct = 0;
        int currentPosUp = 1;
        SimpleDateFormat getTime = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
        String timeCurrent = getTime.format(new Date());

        public DescP2Holder(@NonNull View itemView) {
            super(itemView);

            a2Holder = itemView.findViewById(R.id.btn_A2);
            b2Holder = itemView.findViewById(R.id.btn_B2);
            c2Holder = itemView.findViewById(R.id.btn_C2);
            SubmitHolder2 = itemView.findViewById(R.id.btnSubmit2);
            SubmitHolder2.setVisibility(View.INVISIBLE);
        }

        private void updateQuestion(int pos)
        {
            if((pos+1) == getItemCount()){
                SubmitHolder2.setVisibility(View.VISIBLE);
            }
            else {
                currentPosUp++;
            }
        }

        private void setEnableButton(){
            a2Holder.setEnabled(false);
            b2Holder.setEnabled(false);
            c2Holder.setEnabled(false);
        }

        private void setData(ClsPartP2 ClsPartP2, int pos){

            a2Holder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (a2Holder.getText().toString().equals(clsPartP2List.get(pos).getResult())){
                        a2Holder.setBackgroundResource(R.drawable.chosse_answer);
                        correct++;
                    }else {
                        a2Holder.setBackgroundResource(R.drawable.wrong_answer);
                        if (b2Holder.getText().toString().equals(clsPartP2List.get(pos).getResult())){
                            b2Holder.setBackgroundResource(R.drawable.chosse_answer);
                            c2Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                        else {
                            c2Holder.setBackgroundResource(R.drawable.chosse_answer);
                            b2Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                    }
                    setEnableButton();
                    updateQuestion(pos);

                    a2Holder.postDelayed(() -> {
                        if (onNextQuestionListener != null){
                            onNextQuestionListener.shouldNextQuestions(pos);
                        }
                    },1500);
                }
            });

            b2Holder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (b2Holder.getText().toString().equals(clsPartP2List.get(pos).getResult())){
                        b2Holder.setBackgroundResource(R.drawable.chosse_answer);
                        correct++;
                    }else {
                        b2Holder.setBackgroundResource(R.drawable.wrong_answer);
                        if (a2Holder.getText().toString().equals(clsPartP2List.get(pos).getResult())){
                            a2Holder.setBackgroundResource(R.drawable.chosse_answer);
                            c2Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                        else {
                            c2Holder.setBackgroundResource(R.drawable.chosse_answer);
                            a2Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                    }
                    setEnableButton();
                    updateQuestion(pos);

                    a2Holder.postDelayed(() -> {
                        if (onNextQuestionListener != null){
                            onNextQuestionListener.shouldNextQuestions(pos);
                        }
                    },1500);
                }
            });

            c2Holder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (c2Holder.getText().toString().equals(clsPartP2List.get(pos).getResult())){
                        c2Holder.setBackgroundResource(R.drawable.chosse_answer);
                        correct++;
                    }else {
                        c2Holder.setBackgroundResource(R.drawable.wrong_answer);
                        if (a2Holder.getText().toString().equals(clsPartP2List.get(pos).getResult())){
                            a2Holder.setBackgroundResource(R.drawable.chosse_answer);
                            b2Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                        else {
                            b2Holder.setBackgroundResource(R.drawable.chosse_answer);
                            a2Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                    }
                    setEnableButton();
                    updateQuestion(pos);

                    a2Holder.postDelayed(() -> {
                        if (onNextQuestionListener != null){
                            onNextQuestionListener.shouldNextQuestions(pos);
                        }
                    },1500);
                }
            });

            SubmitHolder2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), ResultP1Activity.class);
                    intent.putExtra("TotalQuestion",getItemCount());
                    intent.putExtra("CorrectQuestion",correct);
                    v.getContext().startActivity(intent);
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    String userID = user.getUid();
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Achievement").child(userID+"/Part2");
                    cls_achievement cls_achievement = new cls_achievement(correct,timeCurrent);
                    ref.setValue(cls_achievement);
                }
            });

        }
    }*/

    public interface OnNextQuestionsListener{
        void shouldNextQuestions(int currentPostion);
    }

    public class DescP2Holder extends RecyclerView.ViewHolder {
        Button a2Holder,b2Holder,c2Holder,SubmitHolder2;
        TextView numQuestionHolder;
        int correct = 0;
        int currentPosUp = 1;
        SimpleDateFormat getTime = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
        String timeCurrent = getTime.format(new Date());
        
        public DescP2Holder(@NonNull View itemView) {
            super(itemView);
            a2Holder = itemView.findViewById(R.id.btn_A2);
            b2Holder = itemView.findViewById(R.id.btn_B2);
            c2Holder = itemView.findViewById(R.id.btn_C2);
            numQuestionHolder = itemView.findViewById(R.id.txtNumQuestion2);
            SubmitHolder2 = itemView.findViewById(R.id.btnSubmit2);
            SubmitHolder2.setVisibility(View.INVISIBLE);
        }
        private void invisibleSubmit(int pos)
        {
            if((pos+1) == clsPartP2List.size()){
                SubmitHolder2.setVisibility(View.VISIBLE);
            }

        }

        private void setEnableButton(){
            a2Holder.setEnabled(false);
            b2Holder.setEnabled(false);
            c2Holder.setEnabled(false);
        }

        @SuppressLint("SetTextI18n")
        private void setData(final int pos){
            numQuestionHolder.setText("Question : "+(pos+1));
            a2Holder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (a2Holder.getText().toString().equals(clsPartP2List.get(pos).getResult())){
                        a2Holder.setBackgroundResource(R.drawable.chosse_answer);
                        correct++;
                    }else {
                        a2Holder.setBackgroundResource(R.drawable.wrong_answer);
                        if (b2Holder.getText().toString().equals(clsPartP2List.get(pos).getResult())){
                            b2Holder.setBackgroundResource(R.drawable.chosse_answer);
                            c2Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                        else {
                            c2Holder.setBackgroundResource(R.drawable.chosse_answer);
                            b2Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                    }
                    setEnableButton();
                    a2Holder.postDelayed(() -> {
                        if (onNextQuestionListener != null){
                            onNextQuestionListener.shouldNextQuestions(pos);
                        }
                    },1500);
                }
            });

            b2Holder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (b2Holder.getText().toString().equals(clsPartP2List.get(pos).getResult())){
                        b2Holder.setBackgroundResource(R.drawable.chosse_answer);
                        correct++;
                    }else {
                        b2Holder.setBackgroundResource(R.drawable.wrong_answer);
                        if (a2Holder.getText().toString().equals(clsPartP2List.get(pos).getResult())){
                            a2Holder.setBackgroundResource(R.drawable.chosse_answer);
                            c2Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                        else {
                            c2Holder.setBackgroundResource(R.drawable.chosse_answer);
                            a2Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                    }
                    setEnableButton();


                    a2Holder.postDelayed(() -> {
                        if (onNextQuestionListener != null){
                            onNextQuestionListener.shouldNextQuestions(pos);
                        }
                    },1500);
                }
            });

            c2Holder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (c2Holder.getText().toString().equals(clsPartP2List.get(pos).getResult())){
                        c2Holder.setBackgroundResource(R.drawable.chosse_answer);
                        correct++;
                    }else {
                        c2Holder.setBackgroundResource(R.drawable.wrong_answer);
                        if (a2Holder.getText().toString().equals(clsPartP2List.get(pos).getResult())){
                            a2Holder.setBackgroundResource(R.drawable.chosse_answer);
                            b2Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                        else {
                            b2Holder.setBackgroundResource(R.drawable.chosse_answer);
                            a2Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                    }
                    setEnableButton();

                    a2Holder.postDelayed(() -> {
                        if (onNextQuestionListener != null){
                            onNextQuestionListener.shouldNextQuestions(pos);
                        }
                    },1500);
                }
            });

            SubmitHolder2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), ResultP1Activity.class);
                    intent.putExtra("TotalQuestion",getItemCount());
                    intent.putExtra("CorrectQuestion",correct);
                    v.getContext().startActivity(intent);
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    String userID = user.getUid();
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Achievement").child(userID+"/Part2");
                    cls_achievement cls_achievement = new cls_achievement(correct,timeCurrent);
                    ref.setValue(cls_achievement);
                }
            });

        }
    }
}

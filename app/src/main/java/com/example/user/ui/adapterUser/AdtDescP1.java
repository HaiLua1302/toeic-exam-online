package com.example.user.ui.adapterUser;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.user.R;
import com.example.user.ui.classExam.ClsPartP1;
import com.example.user.ui.classUser.cls_achievement;
import com.example.user.ui.user.exam1.ResultP1Activity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AdtDescP1 extends RecyclerView.Adapter<AdtDescP1.DescP1Holder> {

    private List<ClsPartP1> clsPartP1s;
    //click chuyen cau hoi callback
    private static  OnNextQuestionListener onNextQuestionListener;

    public void setOnItemClickListener(OnNextQuestionListener onNextQuestionListener) {
        this.onNextQuestionListener = onNextQuestionListener;
    }

    public AdtDescP1() {
    }

    public AdtDescP1(List<ClsPartP1> clsPartP1s) {
        this.clsPartP1s = clsPartP1s;
    }

    @NonNull
    @Override
    public DescP1Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.exam_p1,parent,false);
        return new DescP1Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DescP1Holder holder, int position) {
        holder.setData(position);
        holder.invisibleSumit(position);

    }

    @Override
    public int getItemCount() {
        return clsPartP1s.size();
    }

    public class DescP1Holder extends RecyclerView.ViewHolder {
        TextView numQuestionHolder ;
        ImageView exam_imgHolder;
        Button A1Holder,B1Holder,C1Holder,D1Holder,SubmitHolder;
        int correct = 0;
        SimpleDateFormat getTime = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
        String timeCurrent = getTime.format(new Date());
        
        public DescP1Holder(@NonNull View itemView) {
            super(itemView);
            numQuestionHolder = itemView.findViewById(R.id.txt_NumQuestion);
            exam_imgHolder = itemView.findViewById(R.id.img_p1);
            A1Holder = itemView.findViewById(R.id.btn_A1);
            B1Holder = itemView.findViewById(R.id.btn_B1);
            C1Holder = itemView.findViewById(R.id.btn_C1);
            D1Holder = itemView.findViewById(R.id.btn_D1);
            SubmitHolder = itemView.findViewById(R.id.btn_submit_p1);
            SubmitHolder.setVisibility(View.INVISIBLE);
        }
        private void invisibleSumit (int pos)
        {
            if((pos+1) == clsPartP1s.size()){
                SubmitHolder.setVisibility(View.VISIBLE);
            }
        }

        private void setEnableButton(){
            A1Holder.setEnabled(false);
            B1Holder.setEnabled(false);
            C1Holder.setEnabled(false);
            D1Holder.setEnabled(false);
        }

        private void setData(int pos){
            Picasso.get().load(clsPartP1s.get(pos).getUrl_img()).into(exam_imgHolder);
            numQuestionHolder.setText("Question " + (pos+1));
            A1Holder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (A1Holder.getText().toString().equals(clsPartP1s.get(pos).getResult())) {

                        // khi dap an A  chinh xac
                        // set hinh nen thanh mau xanh la
                        A1Holder.setBackgroundResource(R.drawable.chosse_answer);
                        A1Holder.setTextColor(Color.WHITE);
                        //cong 1 diem dung
//                        correctAnswer();
                        correct++;
                    }
                    else
                    {
                        A1Holder.setBackgroundResource(R.drawable.wrong_answer);
                        A1Holder.setTextColor(Color.WHITE);
                        if (B1Holder.getText().toString().equals(clsPartP1s.get(pos).getResult())){
                            B1Holder.setBackgroundResource(R.drawable.chosse_answer);
                            B1Holder.setTextColor(Color.WHITE);
                            C1Holder.setBackgroundResource(R.drawable.bnt_answer);
                            D1Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                        else if(C1Holder.getText().toString().equals(clsPartP1s.get(pos).getResult())){
                            C1Holder.setBackgroundResource(R.drawable.chosse_answer);
                            C1Holder.setTextColor(Color.WHITE);
                            B1Holder.setBackgroundResource(R.drawable.bnt_answer);
                            D1Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                        else if (D1Holder.getText().toString().equals(clsPartP1s.get(pos).getResult())){
                            D1Holder.setBackgroundResource(R.drawable.chosse_answer);
                            D1Holder.setTextColor(Color.WHITE);
                            C1Holder.setBackgroundResource(R.drawable.bnt_answer);
                            B1Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }

                    }
                    setEnableButton();
                    // doi. 2s thi chay ham ben trong
                    A1Holder.postDelayed(() -> {
                        if (onNextQuestionListener != null) {
                            onNextQuestionListener.shouldNextQuestion(getAdapterPosition());
                        }
                    }, 1200);
                }});

            B1Holder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (B1Holder.getText().toString().equals(clsPartP1s.get(pos).getResult())) {
                        B1Holder.setBackgroundResource(R.drawable.chosse_answer);
                        B1Holder.setTextColor(Color.WHITE);
                        //cong 1 diem dung
//                        correctAnswer();
                        correct++;
                    }
                    else
                    {

                        B1Holder.setBackgroundResource(R.drawable.wrong_answer);
                        B1Holder.setTextColor(Color.WHITE);

                        if (A1Holder.getText().toString().equals(clsPartP1s.get(pos).getResult())){
                            A1Holder.setBackgroundResource(R.drawable.chosse_answer);
                            A1Holder.setTextColor(Color.WHITE);
                            C1Holder.setBackgroundResource(R.drawable.bnt_answer);
                            D1Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                        else if(C1Holder.getText().toString().equals(clsPartP1s.get(pos).getResult())){
                            C1Holder.setBackgroundResource(R.drawable.chosse_answer);
                            C1Holder.setTextColor(Color.WHITE);
                            A1Holder.setBackgroundResource(R.drawable.bnt_answer);
                            D1Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                        else if (D1Holder.getText().toString().equals(clsPartP1s.get(pos).getResult())){
                            D1Holder.setBackgroundResource(R.drawable.chosse_answer);
                            D1Holder.setTextColor(Color.WHITE);
                            C1Holder.setBackgroundResource(R.drawable.bnt_answer);
                            A1Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                    }
                    setEnableButton();
                    // doi. 2s thi chay ham ben trong
                    B1Holder.postDelayed(() -> {
                        if (onNextQuestionListener != null) {
                            onNextQuestionListener.shouldNextQuestion(getAdapterPosition());
                        }
                    }, 1200);
                }
            });

            C1Holder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (C1Holder.getText().toString().equals(clsPartP1s.get(pos).getResult())) {
                        C1Holder.setBackgroundResource(R.drawable.chosse_answer);
                        C1Holder.setTextColor(Color.WHITE);
//                        correctAnswer();
                        correct++;
                    }
                    else
                    {
                        C1Holder.setBackgroundResource(R.drawable.wrong_answer);
                        C1Holder.setTextColor(Color.WHITE);
                        if (B1Holder.getText().toString().equals(clsPartP1s.get(pos).getResult())){
                            B1Holder.setBackgroundResource(R.drawable.chosse_answer);
                            B1Holder.setTextColor(Color.WHITE);
                            A1Holder.setBackgroundResource(R.drawable.bnt_answer);
                            D1Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                        else if(A1Holder.getText().toString().equals(clsPartP1s.get(pos).getResult())){
                            A1Holder.setBackgroundResource(R.drawable.chosse_answer);
                            A1Holder.setTextColor(Color.WHITE);
                            B1Holder.setBackgroundResource(R.drawable.bnt_answer);
                            D1Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                        else if (D1Holder.getText().toString().equals(clsPartP1s.get(pos).getResult())){
                            D1Holder.setBackgroundResource(R.drawable.chosse_answer);
                            D1Holder.setTextColor(Color.WHITE);
                            A1Holder.setBackgroundResource(R.drawable.bnt_answer);
                            B1Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }

                    }
                    setEnableButton();
                    // doi. 2s thi chay ham ben trong
                    C1Holder.postDelayed(() -> {
                        if (onNextQuestionListener != null) {
                            onNextQuestionListener.shouldNextQuestion(getAdapterPosition());
                        }
                    }, 1200);
                }
            });

            D1Holder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (D1Holder.getText().toString().equals(clsPartP1s.get(pos).getResult())) {
                        D1Holder.setBackgroundResource(R.drawable.chosse_answer);
//                        correctAnswer();
                        correct++;
                    }

                    else
                    {

                        D1Holder.setBackgroundResource(R.drawable.wrong_answer);
                        D1Holder.setTextColor(Color.WHITE);
                        if (B1Holder.getText().toString().equals(clsPartP1s.get(pos).getResult())){
                            B1Holder.setBackgroundResource(R.drawable.chosse_answer);
                            B1Holder.setTextColor(Color.WHITE);
                            C1Holder.setBackgroundResource(R.drawable.bnt_answer);
                            A1Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                        else if(C1Holder.getText().toString().equals(clsPartP1s.get(pos).getResult())){
                            C1Holder.setBackgroundResource(R.drawable.chosse_answer);
                            C1Holder.setTextColor(Color.WHITE);
                            B1Holder.setBackgroundResource(R.drawable.bnt_answer);
                            A1Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                        else if (A1Holder.getText().toString().equals(clsPartP1s.get(pos).getResult())){
                            A1Holder.setBackgroundResource(R.drawable.chosse_answer);
                            A1Holder.setTextColor(Color.WHITE);
                            C1Holder.setBackgroundResource(R.drawable.bnt_answer);
                            B1Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }

                    }
                    setEnableButton();
                    // doi. 2s thi chay ham ben trong
                    D1Holder.postDelayed(() -> {
                        if (onNextQuestionListener != null) {
                            onNextQuestionListener.shouldNextQuestion(getAdapterPosition());
                        }
                    }, 1200);
                }
            });

            SubmitHolder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(),ResultP1Activity.class);
                    intent.putExtra("TotalQuestion",getItemCount());
                    intent.putExtra("CorrectQuestion",correct);
                    v.getContext().startActivity(intent);
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    String userID = user.getUid();
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Achievement").child(userID+"/Part1");
                    cls_achievement cls_achievement = new cls_achievement(correct,timeCurrent);
                    ref.setValue(cls_achievement);
                }
            });

        }
    }
    public interface OnNextQuestionListener {
        void shouldNextQuestion(int currentQuestion);
    }
}
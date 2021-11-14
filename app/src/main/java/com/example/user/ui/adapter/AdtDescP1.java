package com.example.user.ui.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.user.R;
import com.example.user.ui.class_exam.ClsPartP1;
import com.example.user.ui.class_user.cls_achievement;
import com.example.user.ui.exam.ResultP1Activity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AdtDescP1 extends FirebaseRecyclerAdapter<ClsPartP1, AdtDescP1.exam_ques_p1_holder> {
    //click chuyen cau hoi callback
    private OnNextQuestionListener onNextQuestionListener;

    public AdtDescP1(@NonNull FirebaseRecyclerOptions options) {
        super(options);
    }

    public void setOnNextQuestionListener(OnNextQuestionListener onNextQuestionListener) {
        this.onNextQuestionListener = onNextQuestionListener;
    }

    @NonNull
    @Override
    public exam_ques_p1_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.exam_p1,parent,false);
        return new AdtDescP1.exam_ques_p1_holder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull exam_ques_p1_holder holder, int position, @NonNull ClsPartP1 model) {
        Picasso.get().load(model.getUrl_img()).into(holder.exam_imgHolder);
        holder.setData(model,position);

    }

    public class exam_ques_p1_holder extends RecyclerView.ViewHolder {
        TextView numQuestionHolder ;
        ImageView exam_imgHolder;
        Button A1Holder,B1Holder,C1Holder,D1Holder,SubmitHolder;
        int currentPosUp = 1;
        int correct = 0;
        SimpleDateFormat getTime = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
        String timeCurrent = getTime.format(new Date());


        public exam_ques_p1_holder(@NonNull View itemView) {
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

        private void updateQuestion(int pos)
        {
            if((pos+1) == getItemCount()){
                SubmitHolder.setVisibility(View.VISIBLE);
            }
            else {
                currentPosUp++;
            }
        }

        private int correctAnswer(){
            correct++;
            return correct;
        }

        private void setEnableButton(){
            A1Holder.setEnabled(false);
            B1Holder.setEnabled(false);
            C1Holder.setEnabled(false);
            D1Holder.setEnabled(false);
        }

        private void setData(ClsPartP1 model, int pos){
           numQuestionHolder.setText("Question " + (pos+1));
            A1Holder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (A1Holder.getText().toString().equals(model.getResult())) {
                        // khi dap an A  chinh xac
                        // set hinh nen thanh mau xanh la
                        A1Holder.setBackgroundResource(R.drawable.chosse_answer);
                        //cong 1 diem dung
//                        correctAnswer();
                        correct++;
                    }
                    else
                    {
                        A1Holder.setBackgroundResource(R.drawable.wrong_answer);
                        if (B1Holder.getText().toString().equals(model.getResult())){
                            B1Holder.setBackgroundResource(R.drawable.chosse_answer);
                            C1Holder.setBackgroundResource(R.drawable.bnt_answer);
                            D1Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                        else if(C1Holder.getText().toString().equals(model.getResult())){
                            C1Holder.setBackgroundResource(R.drawable.chosse_answer);
                            B1Holder.setBackgroundResource(R.drawable.bnt_answer);
                            D1Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                        else if (D1Holder.getText().toString().equals(model.getResult())){
                            D1Holder.setBackgroundResource(R.drawable.chosse_answer);
                            C1Holder.setBackgroundResource(R.drawable.bnt_answer);
                            B1Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }

                    } updateQuestion(pos);
                      setEnableButton();
                    // doi. 2s thi chay ham ben trong
                    A1Holder.postDelayed(() -> {
                        if (onNextQuestionListener != null) {
                            onNextQuestionListener.shouldNextQuestion(getAdapterPosition());
                        }
                    }, 2000);
                }});

            B1Holder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (B1Holder.getText().toString().equals(model.getResult())) {
                        B1Holder.setBackgroundResource(R.drawable.chosse_answer);
                        //cong 1 diem dung
//                        correctAnswer();
                        correct++;
                    }
                    else
                    {

                        B1Holder.setBackgroundResource(R.drawable.wrong_answer);

                        if (A1Holder.getText().toString().equals(model.getResult())){
                            A1Holder.setBackgroundResource(R.drawable.chosse_answer);
                            C1Holder.setBackgroundResource(R.drawable.bnt_answer);
                            D1Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                        else if(C1Holder.getText().toString().equals(model.getResult())){
                            C1Holder.setBackgroundResource(R.drawable.chosse_answer);
                            A1Holder.setBackgroundResource(R.drawable.bnt_answer);
                            D1Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                        else if (D1Holder.getText().toString().equals(model.getResult())){
                            D1Holder.setBackgroundResource(R.drawable.chosse_answer);
                            C1Holder.setBackgroundResource(R.drawable.bnt_answer);
                            A1Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                    }updateQuestion(pos);
                    setEnableButton();
                    // doi. 2s thi chay ham ben trong
                    B1Holder.postDelayed(() -> {
                        if (onNextQuestionListener != null) {
                            onNextQuestionListener.shouldNextQuestion(getAdapterPosition());
                        }
                    }, 2000);
                }
            });

            C1Holder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (C1Holder.getText().toString().equals(model.getResult())) {
                        C1Holder.setBackgroundResource(R.drawable.chosse_answer);
//                        correctAnswer();
                        correct++;
                    }
                    else
                    {
                            C1Holder.setBackgroundResource(R.drawable.wrong_answer);
                        if (B1Holder.getText().toString().equals(model.getResult())){
                            B1Holder.setBackgroundResource(R.drawable.chosse_answer);
                            A1Holder.setBackgroundResource(R.drawable.bnt_answer);
                            D1Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                        else if(A1Holder.getText().toString().equals(model.getResult())){
                            A1Holder.setBackgroundResource(R.drawable.chosse_answer);
                            B1Holder.setBackgroundResource(R.drawable.bnt_answer);
                            D1Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                        else if (D1Holder.getText().toString().equals(model.getResult())){
                            D1Holder.setBackgroundResource(R.drawable.chosse_answer);
                            A1Holder.setBackgroundResource(R.drawable.bnt_answer);
                            B1Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }

                    }updateQuestion(pos);
                    setEnableButton();
                    // doi. 2s thi chay ham ben trong
                    C1Holder.postDelayed(() -> {
                        if (onNextQuestionListener != null) {
                            onNextQuestionListener.shouldNextQuestion(getAdapterPosition());
                        }
                    }, 2000);
                }
            });

            D1Holder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (D1Holder.getText().toString().equals(model.getResult())) {
                        D1Holder.setBackgroundResource(R.drawable.chosse_answer);
//                        correctAnswer();
                        correct++;
                    }

                    else
                    {

                        D1Holder.setBackgroundResource(R.drawable.wrong_answer);
                        if (B1Holder.getText().toString().equals(model.getResult())){
                            B1Holder.setBackgroundResource(R.drawable.chosse_answer);
                            C1Holder.setBackgroundResource(R.drawable.bnt_answer);
                            A1Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                        else if(C1Holder.getText().toString().equals(model.getResult())){
                            C1Holder.setBackgroundResource(R.drawable.chosse_answer);
                            B1Holder.setBackgroundResource(R.drawable.bnt_answer);
                            A1Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                        else if (A1Holder.getText().toString().equals(model.getResult())){
                            A1Holder.setBackgroundResource(R.drawable.chosse_answer);
                            C1Holder.setBackgroundResource(R.drawable.bnt_answer);
                            B1Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }

                    }updateQuestion(pos);
                    setEnableButton();
                    // doi. 2s thi chay ham ben trong
                    D1Holder.postDelayed(() -> {
                        if (onNextQuestionListener != null) {
                            onNextQuestionListener.shouldNextQuestion(getAdapterPosition());
                        }
                    }, 2000);
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
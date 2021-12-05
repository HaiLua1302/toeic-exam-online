package com.example.user.ui.adapterUser;

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
import com.example.user.ui.classExam.ClsPartP1;
import com.example.user.ui.fullExam.TutorialFullExamP2Activity;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AdtDescFullP1 extends RecyclerView.Adapter<AdtDescFullP1.DescFullP1Holder> {
    private List<ClsPartP1> clsPartP1s;
    private String getKey;

    private AdtDescFullP1.OnNextQuestionListener onNextQuestionListener;

    public AdtDescFullP1() {
    }

    public AdtDescFullP1(List<ClsPartP1> clsPartP1s, String getKey) {
        this.clsPartP1s = clsPartP1s;
        this.getKey = getKey;
    }

    public AdtDescFullP1(OnNextQuestionListener onNextQuestionListener) {
        this.onNextQuestionListener = onNextQuestionListener;
    }

    @NonNull
    @Override
    public DescFullP1Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.exam_p1,parent,false);
        return new DescFullP1Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DescFullP1Holder holder, int position) {
        holder.setData(position);
    }

    @Override
    public int getItemCount() {
        return clsPartP1s.size();
    }

    public class DescFullP1Holder extends RecyclerView.ViewHolder {
        TextView numQuestionHolder;
        ImageView exam_imgHolder;
        Button A1Holder, B1Holder, C1Holder, D1Holder, SubmitHolder;
        int currentPosUp = 1;
        int correct = 0;
        SimpleDateFormat getTime = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
        String timeCurrent = getTime.format(new Date());

        public DescFullP1Holder(@NonNull View itemView) {
            super(itemView);
            numQuestionHolder = itemView.findViewById(R.id.txt_NumQuestion);
            exam_imgHolder = itemView.findViewById(R.id.img_p1);
            A1Holder = itemView.findViewById(R.id.btn_A1);
            B1Holder = itemView.findViewById(R.id.btn_B1);
            C1Holder = itemView.findViewById(R.id.btn_C1);
            D1Holder = itemView.findViewById(R.id.btn_D1);
            SubmitHolder = itemView.findViewById(R.id.btn_submit_p1);
            SubmitHolder.setText("Next");
            SubmitHolder.setVisibility(View.INVISIBLE);
        }

        private void invisibleSubmit(int pos) {
            if ((pos + 1) == getItemCount()) {
                SubmitHolder.setVisibility(View.VISIBLE);
            } else {
                currentPosUp++;
            }
        }

        private int correctAnswer() {
            correct++;
            return correct;
        }


        private void setEnableButton() {
            A1Holder.setEnabled(false);
            B1Holder.setEnabled(false);
            C1Holder.setEnabled(false);
            D1Holder.setEnabled(false);
        }

        private void setData(int pos) {
            Picasso.get().load(clsPartP1s.get(pos).getUrl_img()).into(exam_imgHolder);
            numQuestionHolder.setText("Question " + (pos + 1));

            A1Holder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (A1Holder.getText().toString().equals(clsPartP1s.get(pos).getResult())) {
                        // khi dap an A  chinh xac
                        // set hinh nen thanh mau xanh la
                        A1Holder.setBackgroundResource(R.drawable.chosse_answer);
                        //cong 1 diem dung
//                        correctAnswer();
                        correct++;
                    } else {
                        A1Holder.setBackgroundResource(R.drawable.wrong_answer);
                        if (B1Holder.getText().toString().equals(clsPartP1s.get(pos).getResult())) {
                            B1Holder.setBackgroundResource(R.drawable.chosse_answer);
                            C1Holder.setBackgroundResource(R.drawable.bnt_answer);
                            D1Holder.setBackgroundResource(R.drawable.bnt_answer);
                        } else if (C1Holder.getText().toString().equals(clsPartP1s.get(pos).getResult())) {
                            C1Holder.setBackgroundResource(R.drawable.chosse_answer);
                            B1Holder.setBackgroundResource(R.drawable.bnt_answer);
                            D1Holder.setBackgroundResource(R.drawable.bnt_answer);
                        } else if (D1Holder.getText().toString().equals(clsPartP1s.get(pos).getResult())) {
                            D1Holder.setBackgroundResource(R.drawable.chosse_answer);
                            C1Holder.setBackgroundResource(R.drawable.bnt_answer);
                            B1Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }

                    }
                    invisibleSubmit(pos);
                    setEnableButton();
                    // doi. 2s thi chay ham ben trong
                    A1Holder.postDelayed(() -> {
                        if (onNextQuestionListener != null) {
                            onNextQuestionListener.shouldNextQuestion(getAdapterPosition());
                        }
                    }, 2000);
                }
            });

            B1Holder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (B1Holder.getText().toString().equals(clsPartP1s.get(pos).getResult())) {
                        B1Holder.setBackgroundResource(R.drawable.chosse_answer);
                        //cong 1 diem dung
//                        correctAnswer();
                        correct++;
                    } else {

                        B1Holder.setBackgroundResource(R.drawable.wrong_answer);

                        if (A1Holder.getText().toString().equals(clsPartP1s.get(pos).getResult())) {
                            A1Holder.setBackgroundResource(R.drawable.chosse_answer);
                            C1Holder.setBackgroundResource(R.drawable.bnt_answer);
                            D1Holder.setBackgroundResource(R.drawable.bnt_answer);
                        } else if (C1Holder.getText().toString().equals(clsPartP1s.get(pos).getResult())) {
                            C1Holder.setBackgroundResource(R.drawable.chosse_answer);
                            A1Holder.setBackgroundResource(R.drawable.bnt_answer);
                            D1Holder.setBackgroundResource(R.drawable.bnt_answer);
                        } else if (D1Holder.getText().toString().equals(clsPartP1s.get(pos).getResult())) {
                            D1Holder.setBackgroundResource(R.drawable.chosse_answer);
                            C1Holder.setBackgroundResource(R.drawable.bnt_answer);
                            A1Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                    }
                    invisibleSubmit(pos);
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
                    if (C1Holder.getText().toString().equals(clsPartP1s.get(pos).getResult())) {
                        C1Holder.setBackgroundResource(R.drawable.chosse_answer);
//                        correctAnswer();
                        correct++;
                    } else {
                        C1Holder.setBackgroundResource(R.drawable.wrong_answer);
                        if (B1Holder.getText().toString().equals(clsPartP1s.get(pos).getResult())) {
                            B1Holder.setBackgroundResource(R.drawable.chosse_answer);
                            A1Holder.setBackgroundResource(R.drawable.bnt_answer);
                            D1Holder.setBackgroundResource(R.drawable.bnt_answer);
                        } else if (A1Holder.getText().toString().equals(clsPartP1s.get(pos).getResult())) {
                            A1Holder.setBackgroundResource(R.drawable.chosse_answer);
                            B1Holder.setBackgroundResource(R.drawable.bnt_answer);
                            D1Holder.setBackgroundResource(R.drawable.bnt_answer);
                        } else if (D1Holder.getText().toString().equals(clsPartP1s.get(pos).getResult())) {
                            D1Holder.setBackgroundResource(R.drawable.chosse_answer);
                            A1Holder.setBackgroundResource(R.drawable.bnt_answer);
                            B1Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }

                    }
                    invisibleSubmit(pos);
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
                    if (D1Holder.getText().toString().equals(clsPartP1s.get(pos).getResult())) {
                        D1Holder.setBackgroundResource(R.drawable.chosse_answer);
//                        correctAnswer();
                        correct++;
                    } else {

                        D1Holder.setBackgroundResource(R.drawable.wrong_answer);
                        if (B1Holder.getText().toString().equals(clsPartP1s.get(pos).getResult())) {
                            B1Holder.setBackgroundResource(R.drawable.chosse_answer);
                            C1Holder.setBackgroundResource(R.drawable.bnt_answer);
                            A1Holder.setBackgroundResource(R.drawable.bnt_answer);
                        } else if (C1Holder.getText().toString().equals(clsPartP1s.get(pos).getResult())) {
                            C1Holder.setBackgroundResource(R.drawable.chosse_answer);
                            B1Holder.setBackgroundResource(R.drawable.bnt_answer);
                            A1Holder.setBackgroundResource(R.drawable.bnt_answer);
                        } else if (A1Holder.getText().toString().equals(clsPartP1s.get(pos).getResult())) {
                            A1Holder.setBackgroundResource(R.drawable.chosse_answer);
                            C1Holder.setBackgroundResource(R.drawable.bnt_answer);
                            B1Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }

                    }
                    invisibleSubmit(pos);
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
                    Intent intent = new Intent(itemView.getContext(), TutorialFullExamP2Activity.class);
                   /* Intent intent2 = new Intent("custom-message");
                    intent2.putExtra("keyExam",getKey);
                    LocalBroadcastManager.getInstance(itemView.getContext()).sendBroadcast(intent);*/
                    itemView.getContext().startActivity(intent);

                }
            });
        }
    }

    public interface OnNextQuestionListener {
    void shouldNextQuestion(int currentQuestion);}
}

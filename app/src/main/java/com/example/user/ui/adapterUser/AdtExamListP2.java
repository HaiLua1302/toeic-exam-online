package com.example.user.ui.adapterUser;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.user.R;
import com.example.user.ui.user.exam2.DescP2Fragment;

import java.util.List;

public class AdtExamListP2 extends RecyclerView.Adapter<AdtExamListP2.ExamListP2Holder> {
    private List<String> keyExam;

    public AdtExamListP2() {
    }

    public AdtExamListP2(List<String> keyExam) {
        this.keyExam = keyExam;
    }

    @NonNull
    @Override
    public ExamListP2Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_exam_p1,parent,false);
        return new ExamListP2Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExamListP2Holder holder, int position) {
        holder.setData(position);

    }

    @Override
    public int getItemCount() {
        return keyExam.size();
    }

    public class ExamListP2Holder extends RecyclerView.ViewHolder {
        TextView idExam2Holder,totalQuestion;
        Button getToDataExamHolder;
        public ExamListP2Holder(@NonNull View itemView) {
            super(itemView);
            idExam2Holder = itemView.findViewById(R.id.txt_list_Exam_p1);
            getToDataExamHolder = itemView.findViewById(R.id.btn_start_list_p1);
            totalQuestion = itemView.findViewById(R.id.txtTotalQues);
            totalQuestion.setText("| 30 Câu hỏi");
        }
        private void setData(int pos){
            idExam2Holder.setText(keyExam.get(pos).toString());
            getToDataExamHolder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AppCompatActivity activity=(AppCompatActivity)v.getContext();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.wrapper2,new DescP2Fragment(keyExam.get(pos).toString())).addToBackStack(null).commit();
                }
            });
        }
    }
}

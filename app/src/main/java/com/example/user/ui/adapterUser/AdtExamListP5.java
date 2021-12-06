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
import com.example.user.ui.user.exam5.DescP5Fragment;

import java.util.List;

public class AdtExamListP5 extends RecyclerView.Adapter<AdtExamListP5.ExamListP5Holder> {
    private List<String> clsPartP5sKeyExamP5s;

    public AdtExamListP5(List<String> clsPartP5sKeyExamP5s) {
        this.clsPartP5sKeyExamP5s = clsPartP5sKeyExamP5s;
    }

    public AdtExamListP5() {
    }

    @NonNull
    @Override
    public ExamListP5Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_exam_p1,parent,false);
        return new ExamListP5Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExamListP5Holder holder, int position) {
        holder.idExamHolder.setText(clsPartP5sKeyExamP5s.get(position).toString());
        holder.getToDataExamHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.getToDataExamHolder.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AppCompatActivity activity = (AppCompatActivity) v.getContext();
                        activity.getSupportFragmentManager().beginTransaction().
                                replace(R.id.wraper5,
                                        new DescP5Fragment(clsPartP5sKeyExamP5s.get(position).toString()))
                                .addToBackStack(null).commit();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return clsPartP5sKeyExamP5s.size();
    }

    public class ExamListP5Holder extends RecyclerView.ViewHolder {
        TextView idExamHolder,totalQuestion;
        Button getToDataExamHolder;
        public ExamListP5Holder(@NonNull View itemView) {
            super(itemView);
            idExamHolder = itemView.findViewById(R.id.txt_list_Exam_p1);
            getToDataExamHolder = itemView.findViewById(R.id.btn_start_list_p1);
            totalQuestion = itemView.findViewById(R.id.txtTotalQues);
            totalQuestion.setText("| 40 Câu hỏi");
        }
    }
}

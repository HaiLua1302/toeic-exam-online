package com.example.user.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.user.R;
import com.example.user.ui.class_exam.clsRecP3;
import com.example.user.ui.exam3.DecsP3Fragment;

import java.util.List;


public class adtExamListP3 extends RecyclerView.Adapter<adtExamListP3.ExamListHolder3> {
    private List<clsRecP3> clsRecP3s;

    public adtExamListP3(List<clsRecP3> clsRecP3s) {
        this.clsRecP3s = clsRecP3s;
    }

    public adtExamListP3() {
    }

    @NonNull
    @Override
    public adtExamListP3.ExamListHolder3 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_p1,parent,false);
        return new adtExamListP3.ExamListHolder3(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adtExamListP3.ExamListHolder3 holder, int position) {
        holder.idExamHolder.setText("Exam :"+(position+1));
        holder.getToDataExamHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                activity.getSupportFragmentManager().beginTransaction().
                        replace(R.id.wrapper3,
                                new DecsP3Fragment(clsRecP3s.get(position).getId_exam(),
                                        clsRecP3s.get(position).getId_question(),
                                        clsRecP3s.get(position).getUrl_audio()))
                        .addToBackStack(null).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return clsRecP3s.size();
    }

   /* @Override
    public int getItemCount() {
        return clsRecP3s.size();
    }*/

    public class ExamListHolder3 extends RecyclerView.ViewHolder {
        TextView idExamHolder;
        Button getToDataExamHolder;
        public ExamListHolder3(@NonNull View itemView) {
            super(itemView);
            idExamHolder = itemView.findViewById(R.id.txt_list_Exam_p1);
            getToDataExamHolder = itemView.findViewById(R.id.btn_start_list_p1);
        }
    }
}

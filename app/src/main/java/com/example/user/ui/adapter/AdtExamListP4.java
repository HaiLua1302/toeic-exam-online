package com.example.user.ui.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.user.R;
import com.example.user.ui.classExam.ClsRecExamP4;
import com.example.user.ui.exam4.DecsP4Fragment;

import java.util.List;

public class AdtExamListP4 extends RecyclerView.Adapter<AdtExamListP4.ExamListHolder4> {
    private List<ClsRecExamP4> ClsRecExamP4s;

    public AdtExamListP4() {
    }

    public AdtExamListP4(List<ClsRecExamP4> clsRecExamP4s) {
        this.ClsRecExamP4s = clsRecExamP4s;
    }
    @NonNull
    @Override
    public AdtExamListP4.ExamListHolder4 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_exam_p1,parent,false);
        return new AdtExamListP4.ExamListHolder4(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ExamListHolder4 holder, int position) {
        holder.idExamHolder.setText("Exam : "+(position+1));
        holder.getToDataExamHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                activity.getSupportFragmentManager().beginTransaction().
                        replace(R.id.wraper4,
                                new DecsP4Fragment(ClsRecExamP4s.get(position).getId_exam(),
                                        ClsRecExamP4s.get(position).getId_question(),
                                        ClsRecExamP4s.get(position).getUrl_audio()))
                        .addToBackStack(null).commit();

            }
        });
    }

    @Override
    public int getItemCount() {
        return ClsRecExamP4s.size()-1;
    }

    public class ExamListHolder4 extends RecyclerView.ViewHolder {
        TextView idExamHolder;
        Button getToDataExamHolder;

        public ExamListHolder4(@NonNull View itemView) {
            super(itemView);
            idExamHolder = itemView.findViewById(R.id.txt_list_Exam_p1);
            getToDataExamHolder = itemView.findViewById(R.id.btn_start_list_p1);
        }
    }

}

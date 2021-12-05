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
import com.example.user.ui.classExam.ClsRecExamP3;
import com.example.user.ui.exam3.DecsP3Fragment;

import java.util.List;


public class AdtExamListP3 extends RecyclerView.Adapter<AdtExamListP3.ExamListHolder3> {
    private List<ClsRecExamP3> ClsRecExamP3s;
    private List<String> Key;

    public AdtExamListP3() {
    }

    public AdtExamListP3(List<ClsRecExamP3> clsRecExamP3s, List<String> key) {
        ClsRecExamP3s = clsRecExamP3s;
        Key = key;
    }

    @NonNull
    @Override
    public AdtExamListP3.ExamListHolder3 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_exam_p1,parent,false);
        return new AdtExamListP3.ExamListHolder3(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdtExamListP3.ExamListHolder3 holder, int position) {
        holder.idExamHolder.setText(Key.get(position).toString());
        holder.getToDataExamHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                activity.getSupportFragmentManager().beginTransaction().
                        replace(R.id.wraper3,
                                new DecsP3Fragment(/*ClsRecExamP3s.get(position).getId_exam(),
                                        ClsRecExamP3s.get(position).getUrl_audio(),
                                        ClsRecExamP3s.get(position).getId_question()*/Key.get(position).toString()))
                        .addToBackStack(null).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return Key.size();
    }

   /* @Override
    public int getItemCount() {
        return clsRecP3s.size();
    }*/

    public class ExamListHolder3 extends RecyclerView.ViewHolder {
        TextView idExamHolder,totalQuestion;
        Button getToDataExamHolder;
        public ExamListHolder3(@NonNull View itemView) {
            super(itemView);
            idExamHolder = itemView.findViewById(R.id.txt_list_Exam_p1);
            getToDataExamHolder = itemView.findViewById(R.id.btn_start_list_p1);
            totalQuestion = itemView.findViewById(R.id.txtTotalQues);
            totalQuestion.setText("| 30 Câu hỏi");
        }
    }
}

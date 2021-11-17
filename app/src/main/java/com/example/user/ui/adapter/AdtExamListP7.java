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
import com.example.user.ui.exam6.DescP6Fragment;
import com.example.user.ui.exam7.DescP7Fragment;

import java.util.List;

public class AdtExamListP7 extends RecyclerView.Adapter<AdtExamListP7.ExamListP7Holder>{
    private List<String> getKey7;

    public AdtExamListP7() {
    }

    public AdtExamListP7(List<String> getKey7) {
        this.getKey7 = getKey7;
    }

    @NonNull
    @Override
    public ExamListP7Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_exam_p1,parent,false);
        return new ExamListP7Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExamListP7Holder holder, int position) {
        holder.SetData(position);
    }

    @Override
    public int getItemCount() {
        return getKey7.size();
    }

    public class ExamListP7Holder extends RecyclerView.ViewHolder {
        TextView idExamHolder;
        Button getToDataExamHolder;
        public ExamListP7Holder(@NonNull View itemView) {
            super(itemView);
            idExamHolder = itemView.findViewById(R.id.txt_list_Exam_p1);
            getToDataExamHolder = itemView.findViewById(R.id.btn_start_list_p1);
        }
        public void SetData(int Pos){
            idExamHolder.setText(getKey7.get(Pos).toString());
            getToDataExamHolder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getToDataExamHolder.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AppCompatActivity activity = (AppCompatActivity) v.getContext();
                            activity.getSupportFragmentManager().beginTransaction().
                                    replace(R.id.wraper7,
                                            new DescP7Fragment(getKey7.get(Pos).toString()))
                                    .addToBackStack(null).commit();
                        }
                    });
                }
            });
        }
    }
}

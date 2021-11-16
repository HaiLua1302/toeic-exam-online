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
import com.example.user.ui.exam5.DescP5Fragment;
import com.example.user.ui.exam6.DescP6Fragment;

import java.util.ArrayList;
import java.util.List;

public class AdtExamListP6 extends RecyclerView.Adapter<AdtExamListP6.ExamListP6Holder>{
    private List<String> getKey6 ;

    public AdtExamListP6() {
    }

    public AdtExamListP6(List<String> getKey6) {
        this.getKey6 = getKey6;
    }

    @NonNull
    @Override
    public ExamListP6Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_exam_p1,parent,false);
        return new ExamListP6Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExamListP6Holder holder, int position) {
        holder.SetData(position);
    }

    @Override
    public int getItemCount() {
        return getKey6.size();
    }

    public class ExamListP6Holder extends RecyclerView.ViewHolder {
        TextView idExamHolder;
        Button getToDataExamHolder;
        public ExamListP6Holder(@NonNull View itemView) {
            super(itemView);
            idExamHolder = itemView.findViewById(R.id.txt_list_Exam_p1);
            getToDataExamHolder = itemView.findViewById(R.id.btn_start_list_p1);
        }
        public void SetData(int Pos){
            idExamHolder.setText(getKey6.get(Pos).toString());
            getToDataExamHolder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getToDataExamHolder.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AppCompatActivity activity = (AppCompatActivity) v.getContext();
                            activity.getSupportFragmentManager().beginTransaction().
                                    replace(R.id.wraper6,
                                            new DescP6Fragment(getKey6.get(Pos).toString()))
                                    .addToBackStack(null).commit();
                        }
                    });
                }
            });
        }
    }
}

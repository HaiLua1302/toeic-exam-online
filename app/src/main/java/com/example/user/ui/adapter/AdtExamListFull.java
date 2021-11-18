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
import com.example.user.ui.exam7.DescP7Fragment;
import com.example.user.ui.fullExam.DescFullExamFragment;

import java.util.List;

public class AdtExamListFull extends RecyclerView.Adapter<AdtExamListFull.ExamListFullHolder> {
    private List<String> getKeyFull;

    public AdtExamListFull() {
    }

    public AdtExamListFull(List<String> getKeyFull) {
        this.getKeyFull = getKeyFull;
    }

    @NonNull
    @Override
    public ExamListFullHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_exam_full,parent,false);
        return new ExamListFullHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExamListFullHolder holder, int position) {
        holder.SetData(position);
    }

    @Override
    public int getItemCount() {
        return getKeyFull.size();
    }

    public class ExamListFullHolder extends RecyclerView.ViewHolder {
        TextView idExamHolder;
        Button getToDataExamHolder;
        public ExamListFullHolder(@NonNull View itemView) {
            super(itemView);
            idExamHolder = itemView.findViewById(R.id.txt_nameExam);
            getToDataExamHolder = itemView.findViewById(R.id.btn_start_list_p1);
        }
        public void SetData(int Pos){
            idExamHolder.setText(getKeyFull.get(Pos).toString());
            getToDataExamHolder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getToDataExamHolder.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AppCompatActivity activity = (AppCompatActivity) v.getContext();
                            activity.getSupportFragmentManager().beginTransaction().
                                    replace(R.id.wraperfull,
                                            new DescFullExamFragment(getKeyFull.get(Pos).toString()))
                                    .addToBackStack(null).commit();
                        }
                    });
                }
            });
        }
    }
}

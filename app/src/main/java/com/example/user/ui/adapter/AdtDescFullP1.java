package com.example.user.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.user.R;
import com.example.user.ui.class_exam.ClsPartP1;
import com.example.user.ui.class_exam.ClsRecExamP1;

import java.util.List;

public class AdtDescFullP1 extends RecyclerView.Adapter<AdtDescFullP1.DescFullP1Holder> {
    private List<ClsRecExamP1> recExamP1List;

    public AdtDescFullP1() {
    }

    public AdtDescFullP1(List<ClsRecExamP1> recExamP1List) {
        this.recExamP1List = recExamP1List;
    }

    @NonNull
    @Override
    public DescFullP1Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.exam_p1,parent,false);
        return new DescFullP1Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DescFullP1Holder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return recExamP1List.size();
    }

    public class DescFullP1Holder extends RecyclerView.ViewHolder {
        public DescFullP1Holder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

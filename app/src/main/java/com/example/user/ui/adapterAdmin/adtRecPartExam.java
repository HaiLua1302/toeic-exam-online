package com.example.user.ui.adapterAdmin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.user.R;
import com.example.user.ui.adapter.AdtDescFullListQuestionP3;
import com.example.user.ui.admin.part1.DescExamEditP1;

import java.util.List;

public class adtRecPartExam extends RecyclerView.Adapter<adtRecPartExam.RecPartExamHolder> {
    private List<String> getKey;
    private List<Integer> countTotal;

    public adtRecPartExam() {
    }

    public adtRecPartExam(List<String> getKey, List<Integer> countTotal) {
        this.getKey = getKey;
        this.countTotal = countTotal;
    }

    @NonNull
    @Override
    public RecPartExamHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_rec_view_question_admin,parent,false);
        return new RecPartExamHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecPartExamHolder holder, int position) {
        holder.setData(position);
        holder.imgEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.sendDataEdit(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return getKey.size();
    }

    public class RecPartExamHolder extends RecyclerView.ViewHolder {
        TextView txtNoun,txtNameExam,txtTotalQuest;
        ImageView imgEdt;
        public RecPartExamHolder(@NonNull View itemView) {
            super(itemView);
            txtNoun = itemView.findViewById(R.id.txtNoun);
            txtNameExam = itemView.findViewById(R.id.txtNameExamAdmin);
            txtTotalQuest = itemView.findViewById(R.id.txtTotalQuestion);
            imgEdt = itemView.findViewById(R.id.imgSelectEditQues);
        }

        @SuppressLint("SetTextI18n")
        private void setData(int pos){
            txtNoun.setText(""+(pos+1));
            txtNameExam.setText(getKey.get(pos));
            txtTotalQuest.setText(countTotal.get(pos).toString());
            imgEdt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        private void sendDataEdit(int pos){
            String idExam = getKey.get(pos).toString().trim();
            int numberQues = pos+1;
            Intent intent = new Intent(itemView.getContext(), DescExamEditP1.class);
            intent.putExtra("idExam",idExam);
            intent.putExtra("numQues",numberQues);
            itemView.getContext().startActivity(intent);
        }
    }
}

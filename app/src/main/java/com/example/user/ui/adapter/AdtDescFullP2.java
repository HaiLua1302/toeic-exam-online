package com.example.user.ui.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.user.R;
import com.example.user.ui.classExam.ClsPartP2;
import com.example.user.ui.fullExam.TutorialFullExamP3Activity;

import java.util.List;

public class AdtDescFullP2 extends RecyclerView.Adapter<AdtDescFullP2.DescFullP2Holder> {
    private List<ClsPartP2> clsPartP2List;

    public AdtDescFullP2() {
    }

    public AdtDescFullP2(List<ClsPartP2> clsPartP2List) {
        this.clsPartP2List = clsPartP2List;
    }

    @NonNull
    @Override
    public DescFullP2Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.exam_p2,parent,false);
        return new DescFullP2Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DescFullP2Holder holder, int position) {
        holder.setData(position);
        holder.invisibleSubmit(position);
    }

    @Override
    public int getItemCount() {
        return clsPartP2List.size();
    }

    public class DescFullP2Holder extends RecyclerView.ViewHolder {
        Button a2Holder,b2Holder,c2Holder,SubmitHolder2;
        int currentPosUp = 0;
        public DescFullP2Holder(@NonNull View itemView) {
            super(itemView);
            a2Holder = itemView.findViewById(R.id.btn_A2);
            b2Holder = itemView.findViewById(R.id.btn_B2);
            c2Holder = itemView.findViewById(R.id.btn_C2);
            SubmitHolder2 = itemView.findViewById(R.id.btnSubmit2);
            SubmitHolder2.setVisibility(View.INVISIBLE);
            SubmitHolder2.setText("NEXT");
            SubmitHolder2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), TutorialFullExamP3Activity.class);
                    itemView.getContext().startActivity(intent);
                }
            });
        }
        private void invisibleSubmit(int pos) {
            if ((pos + 1) == getItemCount()) {
                SubmitHolder2.setVisibility(View.VISIBLE);
            } else {
                currentPosUp++;
            }
        }
        private void setData(int pos){

            a2Holder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (a2Holder.getText().toString().equals(clsPartP2List.get(pos).getResult())){
                        a2Holder.setBackgroundResource(R.drawable.chosse_answer);
                       
                    }else {
                        a2Holder.setBackgroundResource(R.drawable.wrong_answer);
                        if (b2Holder.getText().toString().equals(clsPartP2List.get(pos).getResult())){
                            b2Holder.setBackgroundResource(R.drawable.chosse_answer);
                            c2Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                        else {
                            c2Holder.setBackgroundResource(R.drawable.chosse_answer);
                            b2Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                    }
                    
                }
            });

            b2Holder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (b2Holder.getText().toString().equals(clsPartP2List.get(pos).getResult())){
                        b2Holder.setBackgroundResource(R.drawable.chosse_answer);
                       
                    }else {
                        b2Holder.setBackgroundResource(R.drawable.wrong_answer);
                        if (a2Holder.getText().toString().equals(clsPartP2List.get(pos).getResult())){
                            a2Holder.setBackgroundResource(R.drawable.chosse_answer);
                            c2Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                        else {
                            c2Holder.setBackgroundResource(R.drawable.chosse_answer);
                            a2Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                    }
                   
                }
            });

            c2Holder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (c2Holder.getText().toString().equals(clsPartP2List.get(pos).getResult())){
                        c2Holder.setBackgroundResource(R.drawable.chosse_answer);
                    }else {
                        c2Holder.setBackgroundResource(R.drawable.wrong_answer);
                        if (a2Holder.getText().toString().equals(clsPartP2List.get(pos).getResult())){
                            a2Holder.setBackgroundResource(R.drawable.chosse_answer);
                            b2Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                        else {
                            b2Holder.setBackgroundResource(R.drawable.chosse_answer);
                            a2Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                    }
                   
                }
            });


        }

    }

}

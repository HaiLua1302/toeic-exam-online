package com.example.user.ui.adapterUser;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.user.R;
import com.example.user.ui.classExam.ClsPartP5;
import com.example.user.ui.fullExam.TutorialFullExamP6Activity;

import java.util.List;

public class AdtDescFullP5 extends RecyclerView.Adapter<AdtDescFullP5.DescFullP5Holder> {
    private List<ClsPartP5> clsPartP5List;

    public AdtDescFullP5() {
    }

    public AdtDescFullP5(List<ClsPartP5> clsPartP5List) {
        this.clsPartP5List = clsPartP5List;
    }

    @NonNull
    @Override
    public DescFullP5Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_question_p5,parent,false);
        return new DescFullP5Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DescFullP5Holder holder, int position) {
        holder.SetData(position);
        holder.showBtnSubmit(position);
    }

    @Override
    public int getItemCount() {
        return clsPartP5List.size();
    }

    public class DescFullP5Holder extends RecyclerView.ViewHolder {
        TextView txtContentQuestionP5Holder,txtA5Holder,txtB5Holder,txtC5Holder,txtD5Holder,txtQuestionHolder;
        Button btnA5Holder,btnB5Holder,btnC5Holder,btnD5Holder,btnSubmitP5Holder;
        public DescFullP5Holder(@NonNull View itemView) {
            super(itemView);
            txtContentQuestionP5Holder = itemView.findViewById(R.id.txtContentQuestionP5);
            txtA5Holder = itemView.findViewById(R.id.txtA5);
            txtB5Holder = itemView.findViewById(R.id.txtB5);
            txtC5Holder = itemView.findViewById(R.id.txtC5);
            txtD5Holder = itemView.findViewById(R.id.txtD5);
            txtQuestionHolder = itemView.findViewById(R.id.txtNumberQuestionP5);

            btnA5Holder = itemView.findViewById(R.id.btnA5);
            btnB5Holder = itemView.findViewById(R.id.btnB5);
            btnC5Holder = itemView.findViewById(R.id.btnC5);
            btnD5Holder = itemView.findViewById(R.id.btnD5);
            btnSubmitP5Holder = itemView.findViewById(R.id.btnSubmitP5);
            btnSubmitP5Holder.setVisibility(View.INVISIBLE);

            btnSubmitP5Holder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), TutorialFullExamP6Activity.class);
                    itemView.getContext().startActivity(intent);
                }
            });

        }
        private void showBtnSubmit(int pos)
        {
            if((pos+1) == getItemCount()){
                btnSubmitP5Holder.setVisibility(View.VISIBLE);
            }
        }
        private void SetData(int pos){
            txtContentQuestionP5Holder.setText(clsPartP5List.get(pos).getQues_content());
            txtA5Holder.setText(clsPartP5List.get(pos).getAns_a());
            txtB5Holder.setText(clsPartP5List.get(pos).getAns_b());
            txtC5Holder.setText(clsPartP5List.get(pos).getAns_c());
            txtD5Holder.setText(clsPartP5List.get(pos).getAns_d());
            txtQuestionHolder.setText("Question : " + (pos+1));

            btnA5Holder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (btnA5Holder.getText().toString().equals(clsPartP5List.get(pos).getResult())){
                        btnA5Holder.setTextColor(Color.WHITE);
                        btnA5Holder.setBackgroundResource(R.drawable.chosse_answer);
                    }else{
                        btnA5Holder.setBackgroundResource(R.drawable.wrong_answer);
                        btnA5Holder.setTextColor(Color.WHITE);
                        if (btnB5Holder.getText().toString().equals(clsPartP5List.get(pos).getResult())){
                            btnB5Holder.setTextColor(Color.WHITE);
                            btnB5Holder.setBackgroundResource(R.drawable.chosse_answer);
                            btnD5Holder.setBackgroundResource(R.drawable.bnt_answer);
                            btnC5Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                        else if (btnC5Holder.getText().toString().equals(clsPartP5List.get(pos).getResult())){
                            btnC5Holder.setTextColor(Color.WHITE);
                            btnC5Holder.setBackgroundResource(R.drawable.chosse_answer);
                            btnB5Holder.setBackgroundResource(R.drawable.bnt_answer);
                            btnD5Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                        else {
                            btnD5Holder.setBackgroundResource(R.drawable.chosse_answer);
                            btnD5Holder.setTextColor(Color.WHITE);
                            btnB5Holder.setBackgroundResource(R.drawable.bnt_answer);
                            btnC5Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                    }
                    setEnableButton();
                }
            });

            btnB5Holder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (btnB5Holder.getText().toString().equals(clsPartP5List.get(pos).getResult())){
                        btnB5Holder.setBackgroundResource(R.drawable.chosse_answer);
                        btnB5Holder.setTextColor(Color.WHITE);
                    }else{
                        btnB5Holder.setBackgroundResource(R.drawable.wrong_answer);
                        btnB5Holder.setTextColor(Color.WHITE);
                        if (btnA5Holder.getText().toString().equals(clsPartP5List.get(pos).getResult())){
                            btnA5Holder.setBackgroundResource(R.drawable.chosse_answer);
                            btnA5Holder.setTextColor(Color.WHITE);
                            btnD5Holder.setBackgroundResource(R.drawable.bnt_answer);
                            btnC5Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                        else if (btnC5Holder.getText().toString().equals(clsPartP5List.get(pos).getResult())){
                            btnC5Holder.setBackgroundResource(R.drawable.chosse_answer);
                            btnC5Holder.setTextColor(Color.WHITE);
                            btnA5Holder.setBackgroundResource(R.drawable.bnt_answer);
                            btnD5Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                        else {
                            btnD5Holder.setBackgroundResource(R.drawable.chosse_answer);
                            btnD5Holder.setTextColor(Color.WHITE);
                            btnA5Holder.setBackgroundResource(R.drawable.bnt_answer);
                            btnC5Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                    }
                    setEnableButton();
                }
            });

            btnC5Holder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (btnC5Holder.getText().toString().equals(clsPartP5List.get(pos).getResult())){
                        btnC5Holder.setBackgroundResource(R.drawable.chosse_answer);
                        btnC5Holder.setTextColor(Color.WHITE);
                    }else{
                        btnC5Holder.setBackgroundResource(R.drawable.wrong_answer);
                        btnC5Holder.setTextColor(Color.WHITE);
                        if (btnA5Holder.getText().toString().equals(clsPartP5List.get(pos).getResult())){
                            btnA5Holder.setTextColor(Color.WHITE);
                            btnA5Holder.setBackgroundResource(R.drawable.chosse_answer);
                            btnD5Holder.setBackgroundResource(R.drawable.bnt_answer);
                            btnB5Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                        else if (btnB5Holder.getText().toString().equals(clsPartP5List.get(pos).getResult())){
                            btnB5Holder.setBackgroundResource(R.drawable.chosse_answer);
                            btnB5Holder.setTextColor(Color.WHITE);
                            btnA5Holder.setBackgroundResource(R.drawable.bnt_answer);
                            btnD5Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                        else {
                            btnD5Holder.setBackgroundResource(R.drawable.chosse_answer);
                            btnD5Holder.setTextColor(Color.WHITE);
                            btnA5Holder.setBackgroundResource(R.drawable.bnt_answer);
                            btnB5Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                    }
                    setEnableButton();
                }
            });

            btnD5Holder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (btnD5Holder.getText().toString().equals(clsPartP5List.get(pos).getResult())){
                        btnD5Holder.setBackgroundResource(R.drawable.chosse_answer);
                        btnD5Holder.setTextColor(Color.WHITE);
                    }else{
                        btnD5Holder.setBackgroundResource(R.drawable.wrong_answer);
                        btnD5Holder.setTextColor(Color.WHITE);
                        if (btnA5Holder.getText().toString().equals(clsPartP5List.get(pos).getResult())){
                            btnA5Holder.setBackgroundResource(R.drawable.chosse_answer);
                            btnA5Holder.setTextColor(Color.WHITE);
                            btnB5Holder.setBackgroundResource(R.drawable.bnt_answer);
                            btnC5Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                        else if (btnB5Holder.getText().toString().equals(clsPartP5List.get(pos).getResult())){
                            btnB5Holder.setBackgroundResource(R.drawable.chosse_answer);
                            btnB5Holder.setTextColor(Color.WHITE);
                            btnA5Holder.setBackgroundResource(R.drawable.bnt_answer);
                            btnC5Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                        else {
                            btnC5Holder.setBackgroundResource(R.drawable.chosse_answer);
                            btnC5Holder.setTextColor(Color.WHITE);
                            btnA5Holder.setBackgroundResource(R.drawable.bnt_answer);
                            btnB5Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                    }
                    setEnableButton();
                }
            });

        }

        private void setEnableButton(){
            btnA5Holder.setEnabled(false);
            btnB5Holder.setEnabled(false);
            btnC5Holder.setEnabled(false);
            btnD5Holder.setEnabled(false);
        }
    }
}

package com.example.user.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.user.R;
import com.example.user.ui.class_exam.ClsListQuestionP6;

import java.util.List;

public class AdtDescListQuestionP6 extends RecyclerView.Adapter<AdtDescListQuestionP6.DescListQuestionP6Holder> {
    List<ClsListQuestionP6> clsListQuestionP6List;

    public AdtDescListQuestionP6() {
    }

    public AdtDescListQuestionP6(List<ClsListQuestionP6> clsListQuestionP6List) {
        this.clsListQuestionP6List = clsListQuestionP6List;
    }

    @NonNull
    @Override
    public DescListQuestionP6Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_question_p3,parent,false);
        return new DescListQuestionP6Holder(view);
                
    }

    @Override
    public void onBindViewHolder(@NonNull DescListQuestionP6Holder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return clsListQuestionP6List.size();
    }

    public class DescListQuestionP6Holder extends RecyclerView.ViewHolder {
        TextView txtContentQuestionP6Holder,txtA6Holder,txtB6Holder,txtC6Holder,txtD6Holder;
        Button btnA6Holder,btnB6Holder,btnC6Holder,btnD6Holder;
        public DescListQuestionP6Holder(@NonNull View itemView) {
            super(itemView);
            txtContentQuestionP6Holder = itemView.findViewById(R.id.txtContentQuestionP3);
            txtA6Holder = itemView.findViewById(R.id.txtA3);
            txtB6Holder = itemView.findViewById(R.id.txtB3);
            txtC6Holder = itemView.findViewById(R.id.txtC3);
            txtD6Holder = itemView.findViewById(R.id.txtD3);

            btnA6Holder = itemView.findViewById(R.id.btnA3);
            btnB6Holder = itemView.findViewById(R.id.btnB3);
            btnC6Holder = itemView.findViewById(R.id.btnC3);
            btnD6Holder = itemView.findViewById(R.id.btnD3);
        }
        private void SetData(int pos){
            txtContentQuestionP6Holder.setText(clsListQuestionP6List.get(pos).getQues_content());
            txtA6Holder.setText(clsListQuestionP6List.get(pos).getAns_a());
            txtB6Holder.setText(clsListQuestionP6List.get(pos).getAns_b());
            txtC6Holder.setText(clsListQuestionP6List.get(pos).getAns_c());
            txtD6Holder.setText(clsListQuestionP6List.get(pos).getAns_d());

            btnA6Holder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (btnA6Holder.getText().toString().equals(clsListQuestionP6List.get(pos).getResult())){
                        btnA6Holder.setBackgroundResource(R.drawable.chosse_answer);
                    }else{
                        btnA6Holder.setBackgroundResource(R.drawable.wrong_answer);
                        if (btnB6Holder.getText().toString().equals(clsListQuestionP6List.get(pos).getResult())){
                            btnB6Holder.setBackgroundResource(R.drawable.chosse_answer);
                            btnD6Holder.setBackgroundResource(R.drawable.bnt_answer);
                            btnC6Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                        else if (btnC6Holder.getText().toString().equals(clsListQuestionP6List.get(pos).getResult())){
                            btnC6Holder.setBackgroundResource(R.drawable.chosse_answer);
                            btnB6Holder.setBackgroundResource(R.drawable.bnt_answer);
                            btnD6Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                        else {
                            btnD6Holder.setBackgroundResource(R.drawable.chosse_answer);
                            btnB6Holder.setBackgroundResource(R.drawable.bnt_answer);
                            btnC6Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                    }
                    setEnableButton();
                }
            });

            btnB6Holder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (btnB6Holder.getText().toString().equals(clsListQuestionP6List.get(pos).getResult())){
                        btnB6Holder.setBackgroundResource(R.drawable.chosse_answer);
                    }else{
                        btnB6Holder.setBackgroundResource(R.drawable.wrong_answer);
                        if (btnA6Holder.getText().toString().equals(clsListQuestionP6List.get(pos).getResult())){
                            btnA6Holder.setBackgroundResource(R.drawable.chosse_answer);
                            btnD6Holder.setBackgroundResource(R.drawable.bnt_answer);
                            btnC6Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                        else if (btnC6Holder.getText().toString().equals(clsListQuestionP6List.get(pos).getResult())){
                            btnC6Holder.setBackgroundResource(R.drawable.chosse_answer);
                            btnA6Holder.setBackgroundResource(R.drawable.bnt_answer);
                            btnD6Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                        else {
                            btnD6Holder.setBackgroundResource(R.drawable.chosse_answer);
                            btnA6Holder.setBackgroundResource(R.drawable.bnt_answer);
                            btnC6Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                    }
                    setEnableButton();
                }
            });

            btnC6Holder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (btnC6Holder.getText().toString().equals(clsListQuestionP6List.get(pos).getResult())){
                        btnC6Holder.setBackgroundResource(R.drawable.chosse_answer);
                    }else{
                        btnC6Holder.setBackgroundResource(R.drawable.wrong_answer);
                        if (btnA6Holder.getText().toString().equals(clsListQuestionP6List.get(pos).getResult())){
                            btnA6Holder.setBackgroundResource(R.drawable.chosse_answer);
                            btnD6Holder.setBackgroundResource(R.drawable.bnt_answer);
                            btnB6Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                        else if (btnB6Holder.getText().toString().equals(clsListQuestionP6List.get(pos).getResult())){
                            btnB6Holder.setBackgroundResource(R.drawable.chosse_answer);
                            btnA6Holder.setBackgroundResource(R.drawable.bnt_answer);
                            btnD6Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                        else {
                            btnD6Holder.setBackgroundResource(R.drawable.chosse_answer);
                            btnA6Holder.setBackgroundResource(R.drawable.bnt_answer);
                            btnB6Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                    }
                    setEnableButton();
                }
            });

            btnD6Holder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (btnD6Holder.getText().toString().equals(clsListQuestionP6List.get(pos).getResult())){
                        btnD6Holder.setBackgroundResource(R.drawable.chosse_answer);
                    }else{
                        btnD6Holder.setBackgroundResource(R.drawable.wrong_answer);
                        if (btnA6Holder.getText().toString().equals(clsListQuestionP6List.get(pos).getResult())){
                            btnA6Holder.setBackgroundResource(R.drawable.chosse_answer);
                            btnB6Holder.setBackgroundResource(R.drawable.bnt_answer);
                            btnC6Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                        else if (btnB6Holder.getText().toString().equals(clsListQuestionP6List.get(pos).getResult())){
                            btnB6Holder.setBackgroundResource(R.drawable.chosse_answer);
                            btnA6Holder.setBackgroundResource(R.drawable.bnt_answer);
                            btnC6Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                        else {
                            btnC6Holder.setBackgroundResource(R.drawable.chosse_answer);
                            btnA6Holder.setBackgroundResource(R.drawable.bnt_answer);
                            btnB6Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                    }
                    setEnableButton();
                }
            });

        }

        private void setEnableButton(){
            btnA6Holder.setEnabled(false);
            btnB6Holder.setEnabled(false);
            btnC6Holder.setEnabled(false);
            btnD6Holder.setEnabled(false);
        }
    }
}

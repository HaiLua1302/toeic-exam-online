package com.example.user.ui.adapterUser;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.user.R;
import com.example.user.ui.classExam.ClsListQuestionP4;

import java.util.List;

public class AdtDescFullListQuestionP4 extends RecyclerView.Adapter<AdtDescFullListQuestionP4.DescFullListQuestionP4Holder> {
    private List<ClsListQuestionP4> clsListQuestionP4List;

    public AdtDescFullListQuestionP4() {
    }

    public AdtDescFullListQuestionP4(List<ClsListQuestionP4> clsListQuestionP4List) {
        this.clsListQuestionP4List = clsListQuestionP4List;
    }

    @NonNull
    @Override
    public DescFullListQuestionP4Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_question_p3,parent,false);
        return new DescFullListQuestionP4Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DescFullListQuestionP4Holder holder, int position) {
        holder.SetData(position);
    }

    @Override
    public int getItemCount() {
        return clsListQuestionP4List.size();
    }

    public class DescFullListQuestionP4Holder extends RecyclerView.ViewHolder {
        TextView txtContentQuestionP4Holder,txtA4Holder,txtB4Holder,txtC4Holder,txtD4Holder;
        Button btnA4Holder,btnB4Holder,btnC4Holder,btnD4Holder;
        public DescFullListQuestionP4Holder(@NonNull View itemView) {
            super(itemView);
            txtContentQuestionP4Holder = itemView.findViewById(R.id.txtContentQuestionP3);
            txtA4Holder = itemView.findViewById(R.id.txtA3);
            txtB4Holder = itemView.findViewById(R.id.txtB3);
            txtC4Holder = itemView.findViewById(R.id.txtC3);
            txtD4Holder = itemView.findViewById(R.id.txtD3);

            btnA4Holder = itemView.findViewById(R.id.btnA3);
            btnB4Holder = itemView.findViewById(R.id.btnB3);
            btnC4Holder = itemView.findViewById(R.id.btnC3);
            btnD4Holder = itemView.findViewById(R.id.btnD3);
        }
        private void SetData(int pos){
            txtContentQuestionP4Holder.setText(clsListQuestionP4List.get(pos).getQues_content());
            txtA4Holder.setText(clsListQuestionP4List.get(pos).getAns_a());
            txtB4Holder.setText(clsListQuestionP4List.get(pos).getAns_b());
            txtC4Holder.setText(clsListQuestionP4List.get(pos).getAns_c());
            txtD4Holder.setText(clsListQuestionP4List.get(pos).getAns_d());

            btnA4Holder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (btnA4Holder.getText().toString().equals(clsListQuestionP4List.get(pos).getResult())){
                        btnA4Holder.setBackgroundResource(R.drawable.chosse_answer);
                        btnA4Holder.setTextColor(Color.WHITE);
                    }else{
                        btnA4Holder.setBackgroundResource(R.drawable.wrong_answer);
                        btnA4Holder.setTextColor(Color.WHITE);
                        if (btnB4Holder.getText().toString().equals(clsListQuestionP4List.get(pos).getResult())){
                            btnB4Holder.setBackgroundResource(R.drawable.chosse_answer);
                            btnB4Holder.setTextColor(Color.WHITE);
                            btnD4Holder.setBackgroundResource(R.drawable.bnt_answer);
                            btnC4Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                        else if (btnC4Holder.getText().toString().equals(clsListQuestionP4List.get(pos).getResult())){
                            btnC4Holder.setBackgroundResource(R.drawable.chosse_answer);
                            btnC4Holder.setTextColor(Color.WHITE);
                            btnB4Holder.setBackgroundResource(R.drawable.bnt_answer);
                            btnD4Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                        else {
                            btnD4Holder.setBackgroundResource(R.drawable.chosse_answer);
                            btnD4Holder.setTextColor(Color.WHITE);
                            btnB4Holder.setBackgroundResource(R.drawable.bnt_answer);
                            btnC4Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                    }
                    setEnableButton();
                }
            });

            btnB4Holder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (btnB4Holder.getText().toString().equals(clsListQuestionP4List.get(pos).getResult())){
                        btnB4Holder.setBackgroundResource(R.drawable.chosse_answer);
                        btnB4Holder.setTextColor(Color.WHITE);
                    }else{
                        btnB4Holder.setBackgroundResource(R.drawable.wrong_answer);
                        btnB4Holder.setTextColor(Color.WHITE);
                        if (btnA4Holder.getText().toString().equals(clsListQuestionP4List.get(pos).getResult())){
                            btnA4Holder.setBackgroundResource(R.drawable.chosse_answer);
                            btnA4Holder.setTextColor(Color.WHITE);
                            btnD4Holder.setBackgroundResource(R.drawable.bnt_answer);
                            btnC4Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                        else if (btnC4Holder.getText().toString().equals(clsListQuestionP4List.get(pos).getResult())){
                            btnC4Holder.setBackgroundResource(R.drawable.chosse_answer);
                            btnC4Holder.setTextColor(Color.WHITE);
                            btnA4Holder.setBackgroundResource(R.drawable.bnt_answer);
                            btnD4Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                        else {
                            btnD4Holder.setBackgroundResource(R.drawable.chosse_answer);
                            btnD4Holder.setTextColor(Color.WHITE);
                            btnA4Holder.setBackgroundResource(R.drawable.bnt_answer);
                            btnC4Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                    }
                    setEnableButton();
                }
            });

            btnC4Holder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (btnC4Holder.getText().toString().equals(clsListQuestionP4List.get(pos).getResult())){
                        btnC4Holder.setBackgroundResource(R.drawable.chosse_answer);
                        btnC4Holder.setTextColor(Color.WHITE);
                    }else{
                        btnC4Holder.setBackgroundResource(R.drawable.wrong_answer);
                        btnC4Holder.setTextColor(Color.WHITE);
                        if (btnA4Holder.getText().toString().equals(clsListQuestionP4List.get(pos).getResult())){
                            btnA4Holder.setBackgroundResource(R.drawable.chosse_answer);
                            btnA4Holder.setTextColor(Color.WHITE);
                            btnD4Holder.setBackgroundResource(R.drawable.bnt_answer);
                            btnB4Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                        else if (btnB4Holder.getText().toString().equals(clsListQuestionP4List.get(pos).getResult())){
                            btnB4Holder.setBackgroundResource(R.drawable.chosse_answer);
                            btnB4Holder.setTextColor(Color.WHITE);
                            btnA4Holder.setBackgroundResource(R.drawable.bnt_answer);
                            btnD4Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                        else {
                            btnD4Holder.setBackgroundResource(R.drawable.chosse_answer);
                            btnD4Holder.setTextColor(Color.WHITE);
                            btnA4Holder.setBackgroundResource(R.drawable.bnt_answer);
                            btnB4Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                    }
                    setEnableButton();
                }
            });

            btnD4Holder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (btnD4Holder.getText().toString().equals(clsListQuestionP4List.get(pos).getResult())){
                        btnD4Holder.setBackgroundResource(R.drawable.chosse_answer);
                        btnD4Holder.setTextColor(Color.WHITE);
                    }else{
                        btnD4Holder.setBackgroundResource(R.drawable.wrong_answer);
                        btnD4Holder.setTextColor(Color.WHITE);
                        if (btnA4Holder.getText().toString().equals(clsListQuestionP4List.get(pos).getResult())){
                            btnA4Holder.setBackgroundResource(R.drawable.chosse_answer);
                            btnA4Holder.setTextColor(Color.WHITE);
                            btnB4Holder.setBackgroundResource(R.drawable.bnt_answer);
                            btnC4Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                        else if (btnB4Holder.getText().toString().equals(clsListQuestionP4List.get(pos).getResult())){
                            btnB4Holder.setBackgroundResource(R.drawable.chosse_answer);
                            btnB4Holder.setTextColor(Color.WHITE);
                            btnA4Holder.setBackgroundResource(R.drawable.bnt_answer);
                            btnC4Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                        else {
                            btnC4Holder.setBackgroundResource(R.drawable.chosse_answer);
                            btnC4Holder.setTextColor(Color.WHITE);
                            btnA4Holder.setBackgroundResource(R.drawable.bnt_answer);
                            btnB4Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                    }
                    setEnableButton();
                }
            });

        }

        private void setEnableButton(){
            btnA4Holder.setEnabled(false);
            btnB4Holder.setEnabled(false);
            btnC4Holder.setEnabled(false);
            btnD4Holder.setEnabled(false);
        }
    }
}

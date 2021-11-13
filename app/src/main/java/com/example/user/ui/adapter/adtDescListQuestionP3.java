package com.example.user.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.user.R;
import com.example.user.ui.class_exam.clsQuestionP3;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.List;

public class adtDescListQuestionP3 extends RecyclerView.Adapter<adtDescListQuestionP3.DescListQuestionP3Holder>{

    private List<clsQuestionP3> clsQuestionP3List;

    public adtDescListQuestionP3() {
    }

    public adtDescListQuestionP3(List<clsQuestionP3> clsQuestionP3List) {
        this.clsQuestionP3List = clsQuestionP3List;
    }

    @NonNull
    @Override
    public adtDescListQuestionP3.DescListQuestionP3Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_question_p3,parent,false);
        return new adtDescListQuestionP3.DescListQuestionP3Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adtDescListQuestionP3.DescListQuestionP3Holder holder, int position) {
        holder.SetData(position);
    }

    @Override
    public int getItemCount() {
        return clsQuestionP3List.size();
    }

    public class DescListQuestionP3Holder extends RecyclerView.ViewHolder {
        TextView txtContentQuestionP3Holder,txtA3Holder,txtB3Holder,txtC3Holder,txtD3Holder;
        Button btnA3Holder,btnB3Holder,btnC3Holder,btnD3Holder;

        public DescListQuestionP3Holder(@NonNull View itemView) {
            super(itemView);
            txtContentQuestionP3Holder = itemView.findViewById(R.id.txtContentQuestionP3);
            txtA3Holder = itemView.findViewById(R.id.txtA3);
            txtB3Holder = itemView.findViewById(R.id.txtB3);
            txtC3Holder = itemView.findViewById(R.id.txtC3);
            txtD3Holder = itemView.findViewById(R.id.txtD3);

            btnA3Holder = itemView.findViewById(R.id.btnA3);
            btnB3Holder = itemView.findViewById(R.id.btnB3);
            btnC3Holder = itemView.findViewById(R.id.btnC3);
            btnD3Holder = itemView.findViewById(R.id.btnD3);

        }
        private void SetData(int pos){
            txtContentQuestionP3Holder.setText(clsQuestionP3List.get(pos).getQues_content());
            txtA3Holder.setText(clsQuestionP3List.get(pos).getAns_a());
            txtB3Holder.setText(clsQuestionP3List.get(pos).getAns_b());
            txtC3Holder.setText(clsQuestionP3List.get(pos).getAns_c());
            txtD3Holder.setText(clsQuestionP3List.get(pos).getAns_d());

            btnA3Holder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (btnA3Holder.getText().toString().equals(clsQuestionP3List.get(pos).getResult())){
                        btnA3Holder.setBackgroundResource(R.drawable.chosse_answer);
                    }else{
                        btnA3Holder.setBackgroundResource(R.drawable.wrong_answer);
                        if (btnB3Holder.getText().toString().equals(clsQuestionP3List.get(pos).getResult())){
                            btnB3Holder.setBackgroundResource(R.drawable.chosse_answer);
                            btnD3Holder.setBackgroundResource(R.drawable.bnt_answer);
                            btnC3Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                        else if (btnC3Holder.getText().toString().equals(clsQuestionP3List.get(pos).getResult())){
                            btnC3Holder.setBackgroundResource(R.drawable.chosse_answer);
                            btnB3Holder.setBackgroundResource(R.drawable.bnt_answer);
                            btnD3Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                        else {
                            btnD3Holder.setBackgroundResource(R.drawable.chosse_answer);
                            btnB3Holder.setBackgroundResource(R.drawable.bnt_answer);
                            btnC3Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                    }
                    setEnableButton();
                }
            });

            btnB3Holder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (btnB3Holder.getText().toString().equals(clsQuestionP3List.get(pos).getResult())){
                        btnB3Holder.setBackgroundResource(R.drawable.chosse_answer);
                    }else{
                        btnB3Holder.setBackgroundResource(R.drawable.wrong_answer);
                        if (btnA3Holder.getText().toString().equals(clsQuestionP3List.get(pos).getResult())){
                            btnA3Holder.setBackgroundResource(R.drawable.chosse_answer);
                            btnD3Holder.setBackgroundResource(R.drawable.bnt_answer);
                            btnC3Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                        else if (btnC3Holder.getText().toString().equals(clsQuestionP3List.get(pos).getResult())){
                            btnC3Holder.setBackgroundResource(R.drawable.chosse_answer);
                            btnA3Holder.setBackgroundResource(R.drawable.bnt_answer);
                            btnD3Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                        else {
                            btnD3Holder.setBackgroundResource(R.drawable.chosse_answer);
                            btnA3Holder.setBackgroundResource(R.drawable.bnt_answer);
                            btnC3Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                    }
                    setEnableButton();
                }
            });

            btnC3Holder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (btnC3Holder.getText().toString().equals(clsQuestionP3List.get(pos).getResult())){
                        btnC3Holder.setBackgroundResource(R.drawable.chosse_answer);
                    }else{
                        btnC3Holder.setBackgroundResource(R.drawable.wrong_answer);
                        if (btnA3Holder.getText().toString().equals(clsQuestionP3List.get(pos).getResult())){
                            btnA3Holder.setBackgroundResource(R.drawable.chosse_answer);
                            btnD3Holder.setBackgroundResource(R.drawable.bnt_answer);
                            btnB3Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                        else if (btnB3Holder.getText().toString().equals(clsQuestionP3List.get(pos).getResult())){
                            btnB3Holder.setBackgroundResource(R.drawable.chosse_answer);
                            btnA3Holder.setBackgroundResource(R.drawable.bnt_answer);
                            btnD3Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                        else {
                            btnD3Holder.setBackgroundResource(R.drawable.chosse_answer);
                            btnA3Holder.setBackgroundResource(R.drawable.bnt_answer);
                            btnB3Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                    }
                    setEnableButton();
                }
            });

            btnD3Holder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (btnD3Holder.getText().toString().equals(clsQuestionP3List.get(pos).getResult())){
                        btnD3Holder.setBackgroundResource(R.drawable.chosse_answer);
                    }else{
                        btnD3Holder.setBackgroundResource(R.drawable.wrong_answer);
                        if (btnA3Holder.getText().toString().equals(clsQuestionP3List.get(pos).getResult())){
                            btnA3Holder.setBackgroundResource(R.drawable.chosse_answer);
                            btnB3Holder.setBackgroundResource(R.drawable.bnt_answer);
                            btnC3Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                        else if (btnB3Holder.getText().toString().equals(clsQuestionP3List.get(pos).getResult())){
                            btnB3Holder.setBackgroundResource(R.drawable.chosse_answer);
                            btnA3Holder.setBackgroundResource(R.drawable.bnt_answer);
                            btnC3Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                        else {
                            btnC3Holder.setBackgroundResource(R.drawable.chosse_answer);
                            btnA3Holder.setBackgroundResource(R.drawable.bnt_answer);
                            btnB3Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                    }
                    setEnableButton();
                }
            });

        }

        private void setEnableButton(){
            btnA3Holder.setEnabled(false);
            btnB3Holder.setEnabled(false);
            btnC3Holder.setEnabled(false);
            btnD3Holder.setEnabled(false);
        }

    }
}

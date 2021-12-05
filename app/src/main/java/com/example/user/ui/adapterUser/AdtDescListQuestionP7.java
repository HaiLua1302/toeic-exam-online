package com.example.user.ui.adapterUser;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.user.R;
import com.example.user.ui.classExam.ClsListQuestionP7;
import com.example.user.ui.exam1.ResultP1Activity;
import com.example.user.ui.exam7.ResultP7Activity;

import java.util.List;

public class AdtDescListQuestionP7 extends RecyclerView.Adapter<AdtDescListQuestionP7.DescListQuestionP7Holder> {
    private List<ClsListQuestionP7> clsListQuestionP7List;

    public AdtDescListQuestionP7() {
    }

    public AdtDescListQuestionP7(List<ClsListQuestionP7> clsListQuestionP7List) {
        this.clsListQuestionP7List = clsListQuestionP7List;
    }

    @NonNull
    @Override
    public DescListQuestionP7Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_question_p5,parent,false);
        return new DescListQuestionP7Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DescListQuestionP7Holder holder, int position) {
        holder.SetData(position);
    }

    @Override
    public int getItemCount() {
        return clsListQuestionP7List.size();
    }

    public class DescListQuestionP7Holder extends RecyclerView.ViewHolder {
        TextView txtContentQuestionP7Holder,txtA7Holder,txtB7Holder,txtC7Holder,txtD7Holder,txtNumberQuestionHolder;
        Button btnA7Holder,btnB7Holder,btnC7Holder,btnD7Holder,btnSubmitP7Holder;
        public DescListQuestionP7Holder(@NonNull View itemView) {
            super(itemView);
            txtContentQuestionP7Holder = itemView.findViewById(R.id.txtContentQuestionP5);
            txtA7Holder = itemView.findViewById(R.id.txtA5);
            txtB7Holder = itemView.findViewById(R.id.txtB5);
            txtC7Holder = itemView.findViewById(R.id.txtC5);
            txtD7Holder = itemView.findViewById(R.id.txtD5);
            txtNumberQuestionHolder = itemView.findViewById(R.id.txtNumberQuestionP5);

            btnA7Holder = itemView.findViewById(R.id.btnA5);
            btnB7Holder = itemView.findViewById(R.id.btnB5);
            btnC7Holder = itemView.findViewById(R.id.btnC5);
            btnD7Holder = itemView.findViewById(R.id.btnD5);

            btnSubmitP7Holder = itemView.findViewById(R.id.btnSubmitP5);
            btnSubmitP7Holder.setVisibility(View.INVISIBLE);
            btnSubmitP7Holder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), ResultP7Activity.class);
                    itemView.getContext().startActivity(intent);
                }
            });
        }
        private void SetData(int pos){
            txtNumberQuestionHolder.setText("Question :"+String.valueOf(pos+1));
            txtContentQuestionP7Holder.setText(clsListQuestionP7List.get(pos).getQues_content());
            txtA7Holder.setText(clsListQuestionP7List.get(pos).getAns_a());
            txtB7Holder.setText(clsListQuestionP7List.get(pos).getAns_b());
            txtC7Holder.setText(clsListQuestionP7List.get(pos).getAns_c());
            txtD7Holder.setText(clsListQuestionP7List.get(pos).getAns_d());

            btnA7Holder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (btnA7Holder.getText().toString().equals(clsListQuestionP7List.get(pos).getResult())){
                        btnA7Holder.setBackgroundResource(R.drawable.chosse_answer);
                    }else{
                        btnA7Holder.setBackgroundResource(R.drawable.wrong_answer);
                        if (btnB7Holder.getText().toString().equals(clsListQuestionP7List.get(pos).getResult())){
                            btnB7Holder.setBackgroundResource(R.drawable.chosse_answer);
                            btnD7Holder.setBackgroundResource(R.drawable.bnt_answer);
                            btnC7Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                        else if (btnC7Holder.getText().toString().equals(clsListQuestionP7List.get(pos).getResult())){
                            btnC7Holder.setBackgroundResource(R.drawable.chosse_answer);
                            btnB7Holder.setBackgroundResource(R.drawable.bnt_answer);
                            btnD7Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                        else {
                            btnD7Holder.setBackgroundResource(R.drawable.chosse_answer);
                            btnB7Holder.setBackgroundResource(R.drawable.bnt_answer);
                            btnC7Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                    }
                    setEnableButton();
                }
            });

            btnB7Holder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (btnB7Holder.getText().toString().equals(clsListQuestionP7List.get(pos).getResult())){
                        btnB7Holder.setBackgroundResource(R.drawable.chosse_answer);
                    }else{
                        btnB7Holder.setBackgroundResource(R.drawable.wrong_answer);
                        if (btnA7Holder.getText().toString().equals(clsListQuestionP7List.get(pos).getResult())){
                            btnA7Holder.setBackgroundResource(R.drawable.chosse_answer);
                            btnD7Holder.setBackgroundResource(R.drawable.bnt_answer);
                            btnC7Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                        else if (btnC7Holder.getText().toString().equals(clsListQuestionP7List.get(pos).getResult())){
                            btnC7Holder.setBackgroundResource(R.drawable.chosse_answer);
                            btnA7Holder.setBackgroundResource(R.drawable.bnt_answer);
                            btnD7Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                        else {
                            btnD7Holder.setBackgroundResource(R.drawable.chosse_answer);
                            btnA7Holder.setBackgroundResource(R.drawable.bnt_answer);
                            btnC7Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                    }
                    setEnableButton();
                }
            });

            btnC7Holder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (btnC7Holder.getText().toString().equals(clsListQuestionP7List.get(pos).getResult())){
                        btnC7Holder.setBackgroundResource(R.drawable.chosse_answer);
                    }else{
                        btnC7Holder.setBackgroundResource(R.drawable.wrong_answer);
                        if (btnA7Holder.getText().toString().equals(clsListQuestionP7List.get(pos).getResult())){
                            btnA7Holder.setBackgroundResource(R.drawable.chosse_answer);
                            btnD7Holder.setBackgroundResource(R.drawable.bnt_answer);
                            btnB7Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                        else if (btnB7Holder.getText().toString().equals(clsListQuestionP7List.get(pos).getResult())){
                            btnB7Holder.setBackgroundResource(R.drawable.chosse_answer);
                            btnA7Holder.setBackgroundResource(R.drawable.bnt_answer);
                            btnD7Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                        else {
                            btnD7Holder.setBackgroundResource(R.drawable.chosse_answer);
                            btnA7Holder.setBackgroundResource(R.drawable.bnt_answer);
                            btnB7Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                    }
                    setEnableButton();
                }
            });

            btnD7Holder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (btnD7Holder.getText().toString().equals(clsListQuestionP7List.get(pos).getResult())){
                        btnD7Holder.setBackgroundResource(R.drawable.chosse_answer);
                    }else{
                        btnD7Holder.setBackgroundResource(R.drawable.wrong_answer);
                        if (btnA7Holder.getText().toString().equals(clsListQuestionP7List.get(pos).getResult())){
                            btnA7Holder.setBackgroundResource(R.drawable.chosse_answer);
                            btnB7Holder.setBackgroundResource(R.drawable.bnt_answer);
                            btnC7Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                        else if (btnB7Holder.getText().toString().equals(clsListQuestionP7List.get(pos).getResult())){
                            btnB7Holder.setBackgroundResource(R.drawable.chosse_answer);
                            btnA7Holder.setBackgroundResource(R.drawable.bnt_answer);
                            btnC7Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                        else {
                            btnC7Holder.setBackgroundResource(R.drawable.chosse_answer);
                            btnA7Holder.setBackgroundResource(R.drawable.bnt_answer);
                            btnB7Holder.setBackgroundResource(R.drawable.bnt_answer);
                        }
                    }
                    setEnableButton();
                }
            });

        }

        private void setEnableButton(){
            btnA7Holder.setEnabled(false);
            btnB7Holder.setEnabled(false);
            btnC7Holder.setEnabled(false);
            btnD7Holder.setEnabled(false);
        }
    }
}

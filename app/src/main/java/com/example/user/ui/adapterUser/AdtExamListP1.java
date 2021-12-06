package com.example.user.ui.adapterUser;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.user.R;
import com.example.user.ui.user.exam1.DescP1Fragment;

import java.util.List;


public class AdtExamListP1 extends RecyclerView.Adapter<AdtExamListP1.ExamListP1Holder> {
    private List<String> clsPartP5sKeyExamP1s;

    public AdtExamListP1() {
    }

    public AdtExamListP1(List<String> clsPartP5sKeyExamP1s) {
        this.clsPartP5sKeyExamP1s = clsPartP5sKeyExamP1s;
    }

    @NonNull
    @Override
    public ExamListP1Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_exam_p1,parent,false);
        return new ExamListP1Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExamListP1Holder holder, int position) {
        holder.setData(position);
    }

    @Override
    public int getItemCount() {
        return clsPartP5sKeyExamP1s.size();
    }

    public class ExamListP1Holder extends RecyclerView.ViewHolder {
        TextView id_exam,totalQuestion ;
        Button get_dataTodesc;
        public ExamListP1Holder(@NonNull View itemView) {
            super(itemView);
            id_exam = itemView.findViewById(R.id.txt_list_Exam_p1);

            get_dataTodesc = (Button) itemView.findViewById(R.id.btn_start_list_p1);
            totalQuestion = itemView.findViewById(R.id.txtTotalQues);
            totalQuestion.setText("| 10 Câu hỏi");
        }
        private void setData(int pos){
            id_exam.setText(clsPartP5sKeyExamP1s.get(pos).toString());
            get_dataTodesc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AppCompatActivity activity=(AppCompatActivity)v.getContext();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.wrapper,new DescP1Fragment(clsPartP5sKeyExamP1s.get(pos).toString())).addToBackStack(null).commit();
                }
            });
        }
    }

}

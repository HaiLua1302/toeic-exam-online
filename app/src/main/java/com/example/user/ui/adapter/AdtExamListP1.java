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
import com.example.user.ui.exam1.DescP1Fragment;

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
        TextView id_exam ;
        Button get_dataTodesc;
        public ExamListP1Holder(@NonNull View itemView) {
            super(itemView);
            id_exam = itemView.findViewById(R.id.txt_list_Exam_p1);
            get_dataTodesc = (Button) itemView.findViewById(R.id.btn_start_list_p1);
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

   /* public AdtExamListP1(@NonNull FirebaseRecyclerOptions<ClsRecExamP1> options) {
        super(options);

    }


    @Override
    protected void onBindViewHolder(@NonNull exam_list_p1_holder holder, int position, @NonNull final ClsRecExamP1 model) {
        holder.id_exam.setText("Exam " + (position+1));
        holder.get_dataTodesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity=(AppCompatActivity)v.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.wrapper,new DescP1Fragment(model.getId_exam(),model.getUrl_audio(),0)).addToBackStack(null).commit();
            }
        });
    }

    @NonNull
    @Override
    public exam_list_p1_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_exam_p1,parent,false);
        return new exam_list_p1_holder(view);
    }

    public static class exam_list_p1_holder extends RecyclerView.ViewHolder{

        TextView id_exam ;
        Button get_dataTodesc;
        public exam_list_p1_holder(@NonNull View itemView) {
            super(itemView);
            id_exam = itemView.findViewById(R.id.txt_list_Exam_p1);
            get_dataTodesc = (Button) itemView.findViewById(R.id.btn_start_list_p1);

        }

    }*/
}

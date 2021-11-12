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
import com.example.user.ui.class_exam.listExam2;
import com.example.user.ui.exam.Desc_Fragment_P1;
import com.example.user.ui.exam2.descFragmentP2;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class adtExamList2 extends FirebaseRecyclerAdapter<listExam2,adtExamList2.ExamListHolder2> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public adtExamList2(@NonNull FirebaseRecyclerOptions<listExam2> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull adtExamList2.ExamListHolder2 holder, int position, @NonNull listExam2 model) {
        holder.idExam2Holder.setText("Exam :"+(position+1));
        holder.getToDataExamHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity=(AppCompatActivity)v.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.wrapper2,new descFragmentP2(model.getId_exam(),model.getUrl_audio())).addToBackStack(null).commit();
            }
        });
    }

    @NonNull
    @Override
    public adtExamList2.ExamListHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_p1,parent,false);
        return new adtExamList2.ExamListHolder2(view);
    }

    public class ExamListHolder2 extends RecyclerView.ViewHolder {
        TextView idExam2Holder;
        Button getToDataExamHolder;

        public ExamListHolder2(@NonNull View itemView) {
            super(itemView);
            idExam2Holder = itemView.findViewById(R.id.txt_list_Exam_p1);
            getToDataExamHolder = itemView.findViewById(R.id.btn_start_list_p1);

        }
    }
}

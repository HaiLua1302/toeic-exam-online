package com.example.user.ui.adapterUser;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.user.R;
import com.example.user.ui.classExam.ClsListQuestionP7;
import com.example.user.ui.classExam.ClsRecExamP7;
import com.example.user.ui.exam3.ResultP3Activity;
import com.example.user.ui.exam7.ResultP7Activity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdtDescP7 extends RecyclerView.Adapter<AdtDescP7.DescP7Holder> {
    private List<ClsRecExamP7> recExamP7List;

    public AdtDescP7() {
    }

    public AdtDescP7(List<ClsRecExamP7> recExamP7List) {
        this.recExamP7List = recExamP7List;
    }

    @NonNull
    @Override
    public DescP7Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_exam_p6,parent,false);
        return new DescP7Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DescP7Holder holder, int position) {
        holder.SetData(position);
        holder.InvisibleSubmit(position);
    }

    @Override
    public int getItemCount() {
        return recExamP7List.size();
    }

    public class DescP7Holder extends RecyclerView.ViewHolder {
        TextView txtNumberParagraphHolder,txtParagraphContentHolder;
        Button btnSubmitHolder;
        RecyclerView recListQuestionP7Holder;
        AdtDescListQuestionP7 adtDescListQuestionP7Holder;
        List<ClsListQuestionP7> clsListQuestionP7sHolder;
        
        public DescP7Holder(@NonNull View itemView) {
            super(itemView);
            txtNumberParagraphHolder = itemView.findViewById(R.id.txtNumberParagraphP6);
            txtParagraphContentHolder = itemView.findViewById(R.id.txtParagraphP6);
            recListQuestionP7Holder = itemView.findViewById(R.id.recViewListQuestionP6);
            btnSubmitHolder = itemView.findViewById(R.id.btnSubmitP6);
            btnSubmitHolder.setVisibility(View.INVISIBLE);

            clsListQuestionP7sHolder = new ArrayList<>();

            btnSubmitHolder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), ResultP7Activity.class);
                    itemView.getContext().startActivity(intent);
                }
            });
        }
        public void SetData(int pos){
            txtNumberParagraphHolder.setText("Question : " + recExamP7List.get(pos).getId_question());
            txtParagraphContentHolder.setText(recExamP7List.get(pos).getParagraph());
            InvisibleSubmit(pos);

            String child = recExamP7List.get(pos).getId_exam() + "/" + recExamP7List.get(pos).getId_question() + "/Question";
            DatabaseReference ref = FirebaseDatabase.getInstance()
                    .getReference("List_Ques7").child(child);

            recListQuestionP7Holder.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        ClsListQuestionP7 recP7 = dataSnapshot.getValue(ClsListQuestionP7.class);
                        clsListQuestionP7sHolder.add(recP7);
                    }
                    adtDescListQuestionP7Holder = new AdtDescListQuestionP7(clsListQuestionP7sHolder);
                    recListQuestionP7Holder.setAdapter(adtDescListQuestionP7Holder);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        public void InvisibleSubmit(int pos){
            if ((pos+1) == recExamP7List.size()){
                btnSubmitHolder.setVisibility(View.VISIBLE);
            }
        }
    }
}

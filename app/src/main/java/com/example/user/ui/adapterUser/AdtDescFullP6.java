package com.example.user.ui.adapterUser;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.user.R;
import com.example.user.ui.classExam.ClsListQuestionP6;
import com.example.user.ui.classExam.ClsRecExamP6;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdtDescFullP6 extends RecyclerView.Adapter<AdtDescFullP6.DescFullP6Holder> {
    private List<ClsRecExamP6> recExamP6List;

    public AdtDescFullP6() {
    }

    public AdtDescFullP6(List<ClsRecExamP6> recExamP6List) {
        this.recExamP6List = recExamP6List;
    }

    @NonNull
    @Override
    public DescFullP6Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_exam_p6,parent,false);
        return new DescFullP6Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DescFullP6Holder holder, int position) {
        holder.SetData(position);
    }

    @Override
    public int getItemCount() {
        return recExamP6List.size();
    }

    public class DescFullP6Holder extends RecyclerView.ViewHolder {
        TextView txtNumberParagraphHolder,txtParagraphContentHolder;
        Button btnSubmitHolder;
        RecyclerView recListQuestionP6Holder;
        AdtDescFullListQuestionP6 adtDescListQuestionP6Holder;
        List<ClsListQuestionP6> clsListQuestionP6sHolder;

        public DescFullP6Holder(@NonNull View itemView) {
            super(itemView);

            txtNumberParagraphHolder = itemView.findViewById(R.id.txtNumberParagraphP6);
            txtParagraphContentHolder = itemView.findViewById(R.id.txtParagraphP6);
            recListQuestionP6Holder = itemView.findViewById(R.id.recViewListQuestionP6);
            btnSubmitHolder = itemView.findViewById(R.id.btnSubmitP6);
            btnSubmitHolder.setVisibility(View.INVISIBLE);

            clsListQuestionP6sHolder = new ArrayList<>();
        }
        public void SetData(int pos){
            txtNumberParagraphHolder.setText("Question : " + recExamP6List.get(pos).getId_question());
            txtParagraphContentHolder.setText(recExamP6List.get(pos).getParagraph());

            String child = recExamP6List.get(pos).getId_exam() + "/" + recExamP6List.get(pos).getId_question() + "/Question";
            DatabaseReference ref = FirebaseDatabase.getInstance()
                    .getReference("List_Ques6").child(child);

            recListQuestionP6Holder.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        ClsListQuestionP6 recP6 = dataSnapshot.getValue(ClsListQuestionP6.class);
                        clsListQuestionP6sHolder.add(recP6);
                    }
                    adtDescListQuestionP6Holder = new AdtDescFullListQuestionP6(clsListQuestionP6sHolder);
                    recListQuestionP6Holder.setAdapter(adtDescListQuestionP6Holder);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

    }
}

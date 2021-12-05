package com.example.user.ui.adapterAdmin;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.user.R;
import com.example.user.ui.admin.part3.EditAQuesP3;
import com.example.user.ui.admin.part6.AddNewAQuesP6;
import com.example.user.ui.classExam.ClsListQuestionP4;
import com.example.user.ui.classExam.ClsListQuestionP6;
import com.example.user.ui.classExam.ClsListQuestionP6;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class AdtAddNew2P6 extends FirebaseRecyclerAdapter<ClsListQuestionP6,AdtAddNew2P6.AddNew2P6Holder> {
    private String idExam;
    private String idQues;
    private Context context;

    public AdtAddNew2P6(@NonNull FirebaseRecyclerOptions<ClsListQuestionP6> options, String idExam, Context context) {
        super(options);
        this.idExam = idExam;
        this.idQues = idQues;
        this.context = context;
    }

    public AdtAddNew2P6(@NonNull FirebaseRecyclerOptions<ClsListQuestionP6> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull AddNew2P6Holder holder, int position, @NonNull ClsListQuestionP6 model) {
        holder.setData(model,position);
        holder.imgDelHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.delQuestion(model);
            }
        });
        holder.imgEditAQuesHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.sendDatatoDetail(model,position);
            }
        });
    }

    @NonNull
    @Override
    public AddNew2P6Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_rec_view_question_admin,parent,false);
        return new AddNew2P6Holder(view);
    }

    public class AddNew2P6Holder extends RecyclerView.ViewHolder {
        private TextView nounHolder,resultHolder,idQuesHolder;
        private ImageView imgEditAQuesHolder,imgDelHolder;
        public AddNew2P6Holder(@NonNull View itemView) {
            super(itemView);
            nounHolder = itemView.findViewById(R.id.txtNounAdmin);
            idQuesHolder = itemView.findViewById(R.id.txtNameExamAdmin);
            resultHolder = itemView.findViewById(R.id.txtTotalQuestionAdmin);
            imgEditAQuesHolder = itemView.findViewById(R.id.imgRecSelectEditAdmin);
            imgDelHolder = itemView.findViewById(R.id.imgRecDelEditAdmin);
        }
        private void setData(ClsListQuestionP6 model, int pos){
            idQuesHolder.setText(model.getId_ques());
            nounHolder.setText(String.valueOf(pos+1));
            resultHolder.setText(model.getResult());
        }
        private void sendDatatoDetail(ClsListQuestionP6 model,int pos){
            Intent intent = new Intent(itemView.getContext(), AddNewAQuesP6.class);
            String result = model.getResult().toString();
            String id_ques = model.getId_ques().toString();
            String quesContent = model.getQues_content().toString();
            String ansA = model.getAns_a().toString();
            String ansB = model.getAns_b().toString();
            String ansC = model.getAns_c().toString();
            String ansD = model.getAns_d().toString();
            intent.putExtra("idExam",idExam);
            intent.putExtra("result",result);
            intent.putExtra("idQues",id_ques);
            intent.putExtra("quesContent",quesContent);
            intent.putExtra("ansA",ansA);
            intent.putExtra("ansB",ansB);
            intent.putExtra("ansC",ansC);
            intent.putExtra("ansD",ansD);
            itemView.getContext().startActivity(intent);
        }
        private void delQuestion(ClsListQuestionP6 model){
            String child2 = idExam +"/"+ idQues +"/Question/"+ model.getId_ques();
            FirebaseDatabase.getInstance().getReference("List_Ques7")
                    .child(child2)
                    .setValue(null)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(itemView.getContext(), "Xóa câu hỏi thành công ", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}
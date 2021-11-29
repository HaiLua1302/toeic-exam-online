package com.example.user.ui.adapterAdmin;

import android.app.ProgressDialog;
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
import com.example.user.ui.admin.part1.EditListQuesP1Activity;
import com.example.user.ui.admin.part2.EditListQuesP2Activity;
import com.example.user.ui.classExam.ClsPartP2;
import com.example.user.ui.classExam.ClsPartP2;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdtRecEditQuesP2 extends RecyclerView.Adapter<AdtRecEditQuesP2.RecEditQuesP2Holder> {
    private List<ClsPartP2> clsPartP2List;
    private String idExam;

    public AdtRecEditQuesP2() {
    }

    public AdtRecEditQuesP2(List<ClsPartP2> clsPartP2List, String idExam) {
        this.clsPartP2List = clsPartP2List;
        this.idExam = idExam;
    }

    @NonNull
    @Override
    public RecEditQuesP2Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_rec_view_question_admin,parent,false);
        return new RecEditQuesP2Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecEditQuesP2Holder holder, int position) {
        holder.setData(position);
        holder.imgEditAQuesHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.sendDatatoDetail(position);
            }
        });
        holder.imgDelHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.delQuestion(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return clsPartP2List.size();
        
    }

    public class RecEditQuesP2Holder extends RecyclerView.ViewHolder {
        private TextView nounHolder,resultHolder,idQuesHolder;
        private ImageView imgEditAQuesHolder,imgDelHolder;
        private DatabaseReference ref;
        private String child;
        public RecEditQuesP2Holder(@NonNull View itemView) {
            super(itemView);
            nounHolder = itemView.findViewById(R.id.txtNounAdmin);
            idQuesHolder = itemView.findViewById(R.id.txtNameExamAdmin);
            resultHolder = itemView.findViewById(R.id.txtTotalQuestionAdmin);
            imgEditAQuesHolder = itemView.findViewById(R.id.imgRecSelectEditAdmin);
            imgDelHolder = itemView.findViewById(R.id.imgRecDelEditAdmin);
        }
        private void setData(int pos){
            nounHolder.setText(String.valueOf(pos+1));
            idQuesHolder.setText(clsPartP2List.get(pos).getId_ques());
            resultHolder.setText(clsPartP2List.get(pos).getResult());
        }
        private void sendDatatoDetail(int pos){
            Intent intent = new Intent(itemView.getContext(), EditListQuesP2Activity.class);
            String result = clsPartP2List.get(pos).getResult().toString();
            String id_ques = clsPartP2List.get(pos).getId_ques().toString();
            int numQues = pos+1;
            intent.putExtra("idExam",idExam);
            intent.putExtra("numQues",numQues);
            intent.putExtra("result",result);
            intent.putExtra("idQues",id_ques);
            itemView.getContext().startActivity(intent);
        }
        private void delQuestion(int pos){
            child = idExam +"/"+clsPartP2List.get(pos).getId_ques();
            ref = FirebaseDatabase.getInstance().getReference("List_Ques2").child(child);
            ref.getRef().removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(itemView.getContext(), "Xóa câu hỏi thành công ", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}

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
import com.example.user.ui.admin.part2.EditListQuesP2Activity;
import com.example.user.ui.classExam.ClsPartP2;
import com.example.user.ui.classExam.ClsPartP2;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdtRecQuesP2 extends RecyclerView.Adapter<AdtRecQuesP2.RecQuesP2Holder> {
    private List<ClsPartP2> clsPartP2s;
    private String nameExam;

    public AdtRecQuesP2() {
    }

    public AdtRecQuesP2(List<ClsPartP2> clsPartP2s, String nameExam) {
        this.clsPartP2s = clsPartP2s;
        this.nameExam = nameExam;
    }

    @NonNull
    @Override
    public RecQuesP2Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_rec_view_question_admin,parent,false);
        return new  RecQuesP2Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecQuesP2Holder holder, int position) {
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
        return clsPartP2s.size();
    }

    public class RecQuesP2Holder extends RecyclerView.ViewHolder {
        private TextView nounHolder,resultHolder,idQuesHolder;
        private ImageView imgEditAQuesHolder,imgDelHolder;
        
        public RecQuesP2Holder(@NonNull View itemView) {
            super(itemView);
            nounHolder = itemView.findViewById(R.id.txtNounAdmin);
            idQuesHolder = itemView.findViewById(R.id.txtNameExamAdmin);
            resultHolder = itemView.findViewById(R.id.txtTotalQuestionAdmin);
            imgEditAQuesHolder = itemView.findViewById(R.id.imgRecSelectEditAdmin);
            imgDelHolder = itemView.findViewById(R.id.imgRecDelEditAdmin);
        }
        private void setData(int pos){
            idQuesHolder.setText(clsPartP2s.get(pos).getId_ques());
            nounHolder.setText(String.valueOf(pos+1));
            resultHolder.setText(clsPartP2s.get(pos).getResult());
        }
        private void sendDatatoDetail(int pos){
            Intent intent = new Intent(itemView.getContext(), EditListQuesP2Activity.class);
            String result = clsPartP2s.get(pos).getResult().toString();
            String id_ques = clsPartP2s.get(pos).getId_ques().toString();
            int numQues = pos+1;
            intent.putExtra("idExam",nameExam);
            intent.putExtra("numQues",numQues);
            intent.putExtra("result",result);
            intent.putExtra("idQues",id_ques);
            itemView.getContext().startActivity(intent);
        }
        private void delQuestion(int pos){
            final ProgressDialog progressDialog = new ProgressDialog(itemView.getContext());
            progressDialog.setTitle("Delete Question...");
            progressDialog.show();
            String child = nameExam +"/"+ clsPartP2s.get(pos).getId_ques();

            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("List_Ques2").child(child);
            ref.getRef().removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    progressDialog.dismiss();
                    Toast.makeText(itemView.getContext(), "Xóa câu hỏi thành công ", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}

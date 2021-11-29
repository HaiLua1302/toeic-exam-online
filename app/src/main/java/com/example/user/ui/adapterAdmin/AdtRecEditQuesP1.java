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
import com.example.user.ui.classExam.ClsPartP1;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdtRecEditQuesP1 extends RecyclerView.Adapter<AdtRecEditQuesP1.EditQuesP1Holder> {
    private List<ClsPartP1> clsPartP1List;
    private String idExam;

    public AdtRecEditQuesP1() {
    }

    public AdtRecEditQuesP1(List<ClsPartP1> clsPartP1List, String idExam) {
        this.clsPartP1List = clsPartP1List;
        this.idExam = idExam;
    }

    @Override
    public EditQuesP1Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_rec_edit_p1,parent,false);
        return new EditQuesP1Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EditQuesP1Holder holder, int position) {
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
        return clsPartP1List.size();
    }

    public class EditQuesP1Holder extends RecyclerView.ViewHolder {
        private TextView nounHolder,resultHolder;
        private ImageView imgQuesHolder,imgEditAQuesHolder,imgDelHolder;
        private StorageReference storageReference;
        private FirebaseStorage storage;

        public EditQuesP1Holder(@NonNull View itemView) {
            super(itemView);
            nounHolder = itemView.findViewById(R.id.txtNounRecEditAdminP1);
            resultHolder = itemView.findViewById(R.id.txtRecResultEditAdminP1);
            imgQuesHolder = itemView.findViewById(R.id.imgRecViewEditAdminP1);
            imgEditAQuesHolder = itemView.findViewById(R.id.imgRecSelectEditAdminP1);
            imgDelHolder = itemView.findViewById(R.id.imgRecDelEditAdminP1);
            storage = FirebaseStorage.getInstance();
            storageReference = storage.getReference();
        }
        private void setData(int pos){
            Picasso.get().load(clsPartP1List.get(pos).getUrl_img()).into(imgQuesHolder);
            nounHolder.setText(String.valueOf(pos+1));
            resultHolder.setText(clsPartP1List.get(pos).getResult());
        }

        private void sendDatatoDetail(int pos){
            Intent intent = new Intent(itemView.getContext(), EditListQuesP1Activity.class);
            String urlImg = clsPartP1List.get(pos).getUrl_img().toString();
            String result = clsPartP1List.get(pos).getResult().toString();
            String id_ques = clsPartP1List.get(pos).getId_ques().toString();
            int numQues = pos+1;
            intent.putExtra("idExam",idExam);
            intent.putExtra("numQues",numQues);
            intent.putExtra("result",result);
            intent.putExtra("urlImg",urlImg);
            intent.putExtra("idQues",id_ques);
            itemView.getContext().startActivity(intent);
        }
        private void delQuestion(int pos){
            final ProgressDialog progressDialog = new ProgressDialog(itemView.getContext());
            progressDialog.setTitle("Delete Question...");
            progressDialog.show();
            storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(clsPartP1List.get(pos).getUrl_img());
            String link = storageReference.getName();
            String child = idExam +"/"+clsPartP1List.get(pos).getId_ques();
            StorageReference removeImg = storage.getReference("images_exam").child(link);
            removeImg.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("List_Ques1").child(child);
                    ref.getRef().removeValue();
                    progressDialog.dismiss();
                    Toast.makeText(itemView.getContext(), "Xóa câu hỏi thành công ", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                }
            });

        }
    }
}

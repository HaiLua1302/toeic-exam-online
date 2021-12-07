package com.example.user.ui.adapterAdmin;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.user.R;
import com.example.user.ui.admin.part1.EditListQuesP1Activity;
import com.example.user.ui.classExam.ClsPartP1;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class RecEditQuesP1Adapter extends FirebaseRecyclerAdapter<ClsPartP1, RecEditQuesP1Adapter.RecEditQuesP1Holder> {
    private Context context;
    private String idExam;

    public RecEditQuesP1Adapter(@NonNull FirebaseRecyclerOptions<ClsPartP1> options, Context context, String idExam) {
        super(options);
        this.context = context;
        this.idExam = idExam;
    }

    public RecEditQuesP1Adapter(@NonNull FirebaseRecyclerOptions<ClsPartP1> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull RecEditQuesP1Holder holder, int position, @NonNull ClsPartP1 model) {
        holder.setData(model,position);
        holder.imgEditAQuesHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.sendDatatoDetail(model,position);
            }
        });
        holder.imgDelHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.clickDel(model);
            }
        });
    }

    @NonNull
    @Override
    public RecEditQuesP1Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_rec_edit_p1,parent,false);
        return new RecEditQuesP1Holder(view);
    }

    public class RecEditQuesP1Holder extends RecyclerView.ViewHolder {
        private TextView nounHolder,resultHolder;
        private ImageView imgQuesHolder,imgEditAQuesHolder,imgDelHolder;
        private StorageReference storageReference;
        private FirebaseStorage storage;
        public RecEditQuesP1Holder(@NonNull View itemView) {
            super(itemView);
            nounHolder = itemView.findViewById(R.id.txtNounRecEditAdminP1);
            resultHolder = itemView.findViewById(R.id.txtRecResultEditAdminP1);
            imgQuesHolder = itemView.findViewById(R.id.imgRecViewEditAdminP1);
            imgEditAQuesHolder = itemView.findViewById(R.id.imgRecSelectEditAdminP1);
            imgDelHolder = itemView.findViewById(R.id.imgRecDelEditAdminP1);
            storage = FirebaseStorage.getInstance();
            storageReference = storage.getReference();
        }
        private void setData(ClsPartP1 model,int pos){
            Picasso.get().load(model.getUrl_img()).into(imgQuesHolder);
            nounHolder.setText(String.valueOf(pos+1));
            resultHolder.setText(model.getResult());
        }

        private void sendDatatoDetail(ClsPartP1 model,int pos){
            Intent intent = new Intent(itemView.getContext(), EditListQuesP1Activity.class);
            String urlImg = model.getUrl_img().toString();
            String result = model.getResult().toString();
            String id_ques = model.getId_ques().toString();
            int numQues = pos+1;
            intent.putExtra("idExam",idExam);
            intent.putExtra("numQues",numQues);
            intent.putExtra("result",result);
            intent.putExtra("urlImg",urlImg);
            intent.putExtra("idQues",id_ques);
            itemView.getContext().startActivity(intent);
        }
        public void clickDel(ClsPartP1 model){
            ViewDialog dialog = new ViewDialog();
            dialog.showDialog((Activity) itemView.getContext(),"Bạn Có Chắc Là Muốn Xóa Nó Chứ ? ",model);
        }
        //show dialog login success message
        public class ViewDialog {
            public void showDialog(Activity activity, String msg,ClsPartP1 model) {
                final Dialog dialog = new Dialog(activity);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.dialog_delete);

                TextView text = (TextView) dialog.findViewById(R.id.txtTitleDel);
                text.setText(msg);

                Button dialogButtonYes = (Button) dialog.findViewById(R.id.btnYesDel);
                Button dialogButtonNo = (Button) dialog.findViewById(R.id.btnNoDel);
                dialogButtonYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        delQuestion(model);
                        dialog.dismiss();
                    }
                });
                dialogButtonNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        }
        private void delQuestion(ClsPartP1 model){
            storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(model.getUrl_img());
            String link = storageReference.getName();
            String child = idExam +"/"+model.getId_ques();
            StorageReference removeImg = storage.getReference("images_exam").child(link);
            removeImg.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("List_Ques1").child(child);
                    ref.getRef().removeValue();
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

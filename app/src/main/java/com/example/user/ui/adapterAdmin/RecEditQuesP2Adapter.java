package com.example.user.ui.adapterAdmin;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
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
import com.example.user.ui.admin.part2.EditListQuesP2Activity;
import com.example.user.ui.classExam.ClsPartP2;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RecEditQuesP2Adapter extends FirebaseRecyclerAdapter<ClsPartP2, RecEditQuesP2Adapter.RecEditQuesP2Holder> {
    private Context context;
    private String idExam;

    public RecEditQuesP2Adapter(@NonNull FirebaseRecyclerOptions<ClsPartP2> options, Context context, String idExam) {
        super(options);
        this.context = context;
        this.idExam = idExam;
    }

    public RecEditQuesP2Adapter(@NonNull FirebaseRecyclerOptions<ClsPartP2> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull RecEditQuesP2Adapter.RecEditQuesP2Holder holder, int position, @NonNull ClsPartP2 model) {
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
    public RecEditQuesP2Adapter.RecEditQuesP2Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_rec_view_question_admin,parent,false);
        return new RecEditQuesP2Holder(view);
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
        private void setData(ClsPartP2 model,int pos){
            nounHolder.setText(String.valueOf(pos+1));
            resultHolder.setText(model.getResult());
            idQuesHolder.setText(model.getId_ques());
        }
        private void sendDatatoDetail(ClsPartP2 model,int pos){
            Intent intent = new Intent(itemView.getContext(), EditListQuesP2Activity.class);
            String result = model.getResult().toString();
            String id_ques = model.getId_ques().toString();
            intent.putExtra("idExam",idExam);
            intent.putExtra("idQues",id_ques);
            intent.putExtra("result",result);
            itemView.getContext().startActivity(intent);
        }
        public void clickDel(ClsPartP2 model){
            ViewDialog dialog = new ViewDialog();
            dialog.showDialog((Activity) itemView.getContext(),"Bạn Có Chắc Là Muốn Xóa Nó Chứ ? ",model);
        }
        //show dialog login success message
        public class ViewDialog {
            public void showDialog(Activity activity, String msg,ClsPartP2 model) {
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
        private void delQuestion(ClsPartP2 model){
            final ProgressDialog progressDialog = new ProgressDialog(itemView.getContext());
            progressDialog.setTitle("Delete Question...");
            progressDialog.show();
            child = idExam +"/"+model.getId_ques();
            ref = FirebaseDatabase.getInstance().getReference("List_Ques2").child(child);
            ref.getRef().setValue(null).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    progressDialog.dismiss();
                    Toast.makeText(itemView.getContext(), "Xóa câu hỏi thành công ", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    
}

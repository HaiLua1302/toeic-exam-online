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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class RecQuesP1Adapter extends FirebaseRecyclerAdapter<ClsPartP1, RecQuesP1Adapter.RecQuesP1Holder> {
    private String nameExam;
    private Context context;

    public RecQuesP1Adapter(@NonNull FirebaseRecyclerOptions<ClsPartP1> options, String nameExam, Context context) {
        super(options);
        this.nameExam = nameExam;
        this.context = context;
    }

    public RecQuesP1Adapter(@NonNull FirebaseRecyclerOptions<ClsPartP1> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull RecQuesP1Holder holder,final int position,final @NonNull ClsPartP1 model) {
        holder.setData(model,position);
        holder.imgDelHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.clickDel(model);
                //notifyDataSetChanged();
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
    public RecQuesP1Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_rec_view_question_p1_admin,parent,false);
        return new  RecQuesP1Holder(view);
    }

    public class RecQuesP1Holder extends RecyclerView.ViewHolder {
        private TextView nounHolder,resultHolder;
        private ImageView imgQuesHolder,imgEditAQuesHolder,imgDelHolder;
        public RecQuesP1Holder(@NonNull View itemView) {
            super(itemView);
            nounHolder = itemView.findViewById(R.id.txtNounRecQuesP1);
            resultHolder = itemView.findViewById(R.id.txtRecResultAdminP1);
            imgQuesHolder = itemView.findViewById(R.id.imgRecViewAdminP1);
            imgEditAQuesHolder = itemView.findViewById(R.id.imgRecSelectEditAdminP1);
            imgDelHolder = itemView.findViewById(R.id.imgRecDelEditAdminP1);
        }
        private void setData(ClsPartP1 model,int pos){
            Picasso.get().load(model.getUrl_img()).into(imgQuesHolder);
            nounHolder.setText(String.valueOf(pos+1));
            resultHolder.setText(model.getResult());
        }
        private void sendDatatoDetail(ClsPartP1 model,int pos){
            Intent intent = new Intent(itemView.getContext(), EditListQuesP1Activity.class);
            String result = model.getResult().toString();
            String id_ques = model.getId_ques().toString();
            String urlImg = model.getUrl_img().toString();
            int numQues = pos+1;
            intent.putExtra("idExam",nameExam);
            intent.putExtra("numQues",numQues);
            intent.putExtra("result",result);
            intent.putExtra("idQues",id_ques);
            intent.putExtra("urlImg",urlImg);
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
            String child = nameExam +"/"+ model.getId_ques();
            FirebaseDatabase.getInstance().getReference()
                    .child("List_Ques1")
                    .child(child)
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

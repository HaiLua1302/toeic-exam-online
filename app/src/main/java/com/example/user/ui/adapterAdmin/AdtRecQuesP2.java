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
import com.example.user.ui.admin.part2.EditListQuesP2Activity;
import com.example.user.ui.classExam.ClsPartP2;
import com.example.user.ui.classExam.ClsPartP2;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

public class AdtRecQuesP2 extends FirebaseRecyclerAdapter<ClsPartP2 , AdtRecQuesP2.testHolder> {
    private String nameExam;
    private Context context;

    public AdtRecQuesP2(@NonNull FirebaseRecyclerOptions<ClsPartP2> options, String nameExam, Context context) {
        super(options);
        this.nameExam = nameExam;
        this.context = context;
    }

    public AdtRecQuesP2(@NonNull FirebaseRecyclerOptions<ClsPartP2> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull testHolder holder,final  int position, @NonNull final ClsPartP2 model) {
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
    public testHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_rec_view_question_admin,parent,false);
        return new testHolder(view);
    }

    public class testHolder extends RecyclerView.ViewHolder {
        private TextView nounHolder,resultHolder,idQuesHolder;
        private ImageView imgEditAQuesHolder,imgDelHolder;
        public testHolder(@NonNull View itemView) {
            super(itemView);
            nounHolder = itemView.findViewById(R.id.txtNounAdmin);
            idQuesHolder = itemView.findViewById(R.id.txtNameExamAdmin);
            resultHolder = itemView.findViewById(R.id.txtTotalQuestionAdmin);
            imgEditAQuesHolder = itemView.findViewById(R.id.imgRecSelectEditAdmin);
            imgDelHolder = itemView.findViewById(R.id.imgRecDelEditAdmin);
        }
        private void setData(ClsPartP2 model,int pos){
            idQuesHolder.setText(model.getId_ques());
            nounHolder.setText(String.valueOf(pos+1));
            resultHolder.setText(model.getResult());
        }
        private void sendDatatoDetail(ClsPartP2 model,int pos){
            Intent intent = new Intent(itemView.getContext(), EditListQuesP2Activity.class);
            String result = model.getResult().toString();
            String id_ques = model.getId_ques().toString();
            intent.putExtra("idExam",nameExam);
            intent.putExtra("result",result);
            intent.putExtra("idQues",id_ques);
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
            String child = nameExam +"/"+ model.getId_ques();
            FirebaseDatabase.getInstance().getReference()
                    .child("List_Ques2")
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

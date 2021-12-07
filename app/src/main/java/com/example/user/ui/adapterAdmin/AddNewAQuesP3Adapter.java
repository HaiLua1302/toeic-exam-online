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
import com.example.user.ui.admin.part3.EditAQuesP3Activity;
import com.example.user.ui.classExam.ClsListQuestionP3;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

public class AddNewAQuesP3Adapter extends FirebaseRecyclerAdapter<ClsListQuestionP3, AddNewAQuesP3Adapter.RecListQuesP3Holder> {
    private String child;
    private Context context;

    public AddNewAQuesP3Adapter(@NonNull FirebaseRecyclerOptions<ClsListQuestionP3> options, String child, Context context) {
        super(options);
        this.child = child;
        this.context = context;
    }

    public AddNewAQuesP3Adapter(@NonNull FirebaseRecyclerOptions<ClsListQuestionP3> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull RecListQuesP3Holder holder, int position, @NonNull ClsListQuestionP3 model) {
        holder.setData(model,position);
        holder.imgDelHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.clickDel(model);
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
    public RecListQuesP3Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_rec_view_question_admin,parent,false);
        return new RecListQuesP3Holder(view);
    }

    public class RecListQuesP3Holder extends RecyclerView.ViewHolder {
        private TextView nounHolder,resultHolder,idQuesHolder;
        private ImageView imgEditAQuesHolder,imgDelHolder;
        public RecListQuesP3Holder(@NonNull View itemView) {
            super(itemView);
            nounHolder = itemView.findViewById(R.id.txtNounAdmin);
            idQuesHolder = itemView.findViewById(R.id.txtNameExamAdmin);
            resultHolder = itemView.findViewById(R.id.txtTotalQuestionAdmin);
            imgEditAQuesHolder = itemView.findViewById(R.id.imgRecSelectEditAdmin);
            imgDelHolder = itemView.findViewById(R.id.imgRecDelEditAdmin);
        }
        private void setData(ClsListQuestionP3 model, int pos){
            idQuesHolder.setText(model.getId_ques());
            nounHolder.setText(String.valueOf(pos+1));
            resultHolder.setText(model.getResult());
        }
        private void sendDatatoDetail(ClsListQuestionP3 model,int pos){
            Intent intent = new Intent(itemView.getContext(), EditAQuesP3Activity.class);
            String result = model.getResult().toString();
            String id_ques = model.getId_ques().toString();
            String quesContent = model.getQues_content().toString();
            String ansA = model.getAns_a().toString();
            String ansB = model.getAns_b().toString();
            String ansC = model.getAns_c().toString();
            String ansD = model.getAns_d().toString();
            intent.putExtra("idExam",child);
            intent.putExtra("result",result);
            intent.putExtra("idQues",id_ques);
            intent.putExtra("quesContent",quesContent);
            intent.putExtra("ansA",ansA);
            intent.putExtra("ansB",ansB);
            intent.putExtra("ansC",ansC);
            intent.putExtra("ansD",ansD);
            itemView.getContext().startActivity(intent);
        }
        public void clickDel(ClsListQuestionP3 model){
            ViewDialog dialog = new ViewDialog();
            dialog.showDialog((Activity) itemView.getContext(),"Bạn Có Chắc Là Muốn Xóa Nó Chứ ? ",model);
        }
        //show dialog login success message
        public class ViewDialog {
            public void showDialog(Activity activity, String msg,ClsListQuestionP3 model) {
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
        private void delQuestion(ClsListQuestionP3 model){
            String child2 = child +"/"+ model.getId_ques();
            FirebaseDatabase.getInstance().getReference("List_Ques3")
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
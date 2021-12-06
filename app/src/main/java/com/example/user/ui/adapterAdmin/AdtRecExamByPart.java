package com.example.user.ui.adapterAdmin;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.user.R;
import com.example.user.ui.admin.part1.EditQuesP1Activity;
import com.example.user.ui.admin.part2.EditQuesP2Activity;
import com.example.user.ui.admin.part3.AddNewQues1P3;
import com.example.user.ui.admin.part4.AddNewQues1P4;
import com.example.user.ui.admin.part5.AddNewQuesP5;
import com.example.user.ui.admin.part6.AddNewQues1P6;
import com.example.user.ui.admin.part7.AddNewQues1P7;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class AdtRecExamByPart extends RecyclerView.Adapter<AdtRecExamByPart.RecPartExamHolder> {
    private List<String> getKey;
    private List<Integer> countTotal;
    private String keyExam;

    public AdtRecExamByPart() {
    }

    public AdtRecExamByPart(List<String> getKey, List<Integer> countTotal, String child) {
        this.getKey = getKey;
        this.countTotal = countTotal;
        this.keyExam = child;
    }

    @NonNull
    @Override
    public RecPartExamHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_rec_view_manage_admin,parent,false);
        return new RecPartExamHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecPartExamHolder holder, int position) {
        holder.setData(position);
        holder.imgEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog = new ProgressDialog(v.getContext());
                progressDialog.setTitle("Wait...");
                progressDialog.show();
                holder.sendDataEdit(position);
                progressDialog.dismiss();
            }
        });
    }

    @Override
    public int getItemCount() {
        return getKey.size();
    }

    public class RecPartExamHolder extends RecyclerView.ViewHolder {
        TextView txtNoun,txtNameExam,txtTotalQuest;
        ImageView imgEdt;
        Intent Intent;
        public RecPartExamHolder(@NonNull View itemView) {
            super(itemView);
            txtNoun = itemView.findViewById(R.id.txtNounManageAdmin);
            txtNameExam = itemView.findViewById(R.id.txtNameExamManageAdmin);
            txtTotalQuest = itemView.findViewById(R.id.txtTotalQuestionManageAdmin);
            imgEdt = itemView.findViewById(R.id.imgRecSelectEditManageAdmin);
        }

        private void setData(int pos){
            txtNoun.setText(String.valueOf(pos+1));
            txtNameExam.setText(getKey.get(pos));

            if (countTotal.get(pos).equals(0)){
                txtTotalQuest.setText("0");
            }else {
                String numberCount = countTotal.get(pos).toString();
                txtTotalQuest.setText(numberCount);
            }


        }

        private void sendDataEdit(int pos){
            String idExam = getKey.get(pos).toString().trim();
            int numberQues = pos+1;
            switch(keyExam) {
                case "Ques_2":
                    Intent = new Intent(itemView.getContext(), EditQuesP2Activity.class);
                    Intent.putExtra("idExam",idExam);
                    Intent.putExtra("numQues",numberQues);
                    itemView.getContext().startActivity(Intent);
                    break;
                case "Ques_3":
                    Intent = new Intent(itemView.getContext(), AddNewQues1P3.class);
                    Intent.putExtra("idExam",idExam);
                    itemView.getContext().startActivity(Intent);
                    break;
                case "Ques_4":
                    Intent = new Intent(itemView.getContext(), AddNewQues1P4.class);
                    Intent.putExtra("idExam",idExam);
                    itemView.getContext().startActivity(Intent);
                    break;
                case "Ques_5":
                    Intent = new Intent(itemView.getContext(), AddNewQuesP5.class);
                    Intent.putExtra("idExam",idExam);
                    itemView.getContext().startActivity(Intent);

                    break;
                case "Ques_6":
                    Intent = new Intent(itemView.getContext(), AddNewQues1P6.class);
                    Intent.putExtra("idExam",idExam);
                    itemView.getContext().startActivity(Intent);
                    break;
                case "Ques_7":
                    Intent = new Intent(itemView.getContext(), AddNewQues1P7.class);
                    Intent.putExtra("idExam",idExam);
                    itemView.getContext().startActivity(Intent);
                    break;
                case "Ques_1":
                default:
                    Intent = new Intent(itemView.getContext(), EditQuesP1Activity.class);
                    Intent.putExtra("idExam",idExam);
                    Intent.putExtra("numQues",numberQues);
                    itemView.getContext().startActivity(Intent);
                    break;
            }
        }
    }
}

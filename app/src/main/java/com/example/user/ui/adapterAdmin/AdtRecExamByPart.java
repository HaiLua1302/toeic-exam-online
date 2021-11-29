package com.example.user.ui.adapterAdmin;

import android.annotation.SuppressLint;
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
import com.example.user.ui.admin.ManagerQuestionActivity;
import com.example.user.ui.admin.part1.AddNewQuesP1Activity;
import com.example.user.ui.admin.part1.EditQuesP1Activity;
import com.example.user.ui.admin.part2.AddNewQuesP2Activity;
import com.example.user.ui.admin.part2.EditQuesP2Activity;

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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_rec_view_question_admin,parent,false);
        return new RecPartExamHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecPartExamHolder holder, int position) {
        holder.setData(position);
        holder.imgEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.sendDataEdit(position);
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
            txtNoun = itemView.findViewById(R.id.txtNounAdmin);
            txtNameExam = itemView.findViewById(R.id.txtNameExamAdmin);
            txtTotalQuest = itemView.findViewById(R.id.txtTotalQuestionAdmin);
            imgEdt = itemView.findViewById(R.id.imgRecSelectEditAdmin);
        }

        @SuppressLint("SetTextI18n")
        private void setData(int pos){
            txtNoun.setText(""+(pos+1));
            txtNameExam.setText(getKey.get(pos));
            txtTotalQuest.setText(countTotal.get(pos).toString());
            imgEdt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        private void sendDataEdit(int pos){
          /*  final ProgressDialog progressDialog = new ProgressDialog(itemView.getContext());
            progressDialog.setTitle("Wait...");
            progressDialog.show();*/
            String idExam = getKey.get(pos).toString().trim();
            int numberQues = pos+1;
           /* if (idExam.equals("Ques_1")){
                Intent = new Intent(itemView.getContext(), EditQuesP1Activity.class);
                Intent.putExtra("idExam",idExam);
                Intent.putExtra("numQues",numberQues);
                itemView.getContext().startActivity(Intent);
            }
            else if (idExam.equals("Ques_2")){
                Intent = new Intent(itemView.getContext(), EditQuesP2Activity.class);
                Intent.putExtra("idExam",idExam);
                Intent.putExtra("numQues",numberQues);
                itemView.getContext().startActivity(Intent);
            }*/
            switch(keyExam) {
                case "Ques_2":
                    //progressDialog.dismiss();
                    Intent = new Intent(itemView.getContext(), EditQuesP2Activity.class);
                    Intent.putExtra("idExam",idExam);
                    Intent.putExtra("numQues",numberQues);
                    itemView.getContext().startActivity(Intent);
                    break;
                case "Ques_3":
                    //getDataFirebase("List_Ques3","Ques_3");
                    break;
                case "Ques_4":
                    // getDataFirebase("List_Ques4","Ques_4");
                    break;
                case "Ques_5":
                    // getDataFirebase2("List_Ques5","Ques_5");
                    break;
                case "Ques_6":
                    // getDataFirebase("List_Ques6","Ques_6");
                    break;
                case "Ques_7":
                // getDataFirebase("List_Ques6","Ques_6");
                    break;
                case "Ques_1":
                default:
                    //progressDialog.dismiss();
                    Intent = new Intent(itemView.getContext(), EditQuesP1Activity.class);
                    Intent.putExtra("idExam",idExam);
                    Intent.putExtra("numQues",numberQues);
                    itemView.getContext().startActivity(Intent);
                    break;
            }
        }
    }
}

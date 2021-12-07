package com.example.user.ui.adapterAdmin;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.user.R;
import com.example.user.ui.admin.fullExam.AddNewAPartActivity;
import com.example.user.ui.classExam.ClsRecExamFull;

import java.util.List;

public class RecExamFullAdapter extends RecyclerView.Adapter<RecExamFullAdapter.RecExamFullHolder> {
    private List<String> getKey;
    private List<ClsRecExamFull> clsRecExamFulls;

    public RecExamFullAdapter() {
    }

    public RecExamFullAdapter(List<String> getKey, List<ClsRecExamFull> clsRecExamFulls) {
        this.getKey = getKey;
        this.clsRecExamFulls = clsRecExamFulls;
    }

    @NonNull
    @Override
    public RecExamFullHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_rec_full_exam,parent,false);
        return new RecExamFullHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecExamFullHolder holder, int position) {
        holder.setData(position);
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
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

    public class RecExamFullHolder extends RecyclerView.ViewHolder {
        private TextView txtNoun,txtNameExam;
        private ImageView imgEdit;
        public RecExamFullHolder(@NonNull View itemView) {
            super(itemView);
            txtNoun = itemView.findViewById(R.id.txtNounFull);
            txtNameExam = itemView.findViewById(R.id.txtNameExamFull);
            imgEdit = itemView.findViewById(R.id.imgEditFull);
        }
        private void setData(int pos){
            txtNoun.setText(String.valueOf(pos+1));
            txtNameExam.setText(getKey.get(pos).toString());
        }

        private void sendDataEdit(int pos){
            String idExam = getKey.get(pos).toString();

            String idExam1 = clsRecExamFulls.get(pos).getId_part1();
            String idQues1 = clsRecExamFulls.get(pos).getId_question1();
            String idExam2 = clsRecExamFulls.get(pos).getId_part2();
            String idQues2 = clsRecExamFulls.get(pos).getId_question2();
            String idExam3 = clsRecExamFulls.get(pos).getId_part3();
            String idQues3 = clsRecExamFulls.get(pos).getId_question3();
            String idExam4 = clsRecExamFulls.get(pos).getId_part4();
            String idQues4 = clsRecExamFulls.get(pos).getId_question4();
            String idExam5 = clsRecExamFulls.get(pos).getId_part5();
            String idQues5 = clsRecExamFulls.get(pos).getId_question5();
            String idExam6 = clsRecExamFulls.get(pos).getId_part6();
            String idQues6 = clsRecExamFulls.get(pos).getId_question6();
            String idExam7 = clsRecExamFulls.get(pos).getId_part7();
            String idQues7 = clsRecExamFulls.get(pos).getId_question7();

            Intent intent = new Intent(itemView.getContext(), AddNewAPartActivity.class);
            intent.putExtra("idExam",idExam);
            intent.putExtra("idExam1",idExam1);
            intent.putExtra("idQues1",idQues1);
            intent.putExtra("idExam2",idExam2);
            intent.putExtra("idQues2",idQues2);
            intent.putExtra("idExam3",idExam3);
            intent.putExtra("idQues3",idQues3);
            intent.putExtra("idExam4",idExam4);
            intent.putExtra("idQues4",idQues4);
            intent.putExtra("idExam5",idExam5);
            intent.putExtra("idQues5",idQues5);
            intent.putExtra("idExam6",idExam6);
            intent.putExtra("idQues6",idQues6);
            intent.putExtra("idExam7",idExam7);
            intent.putExtra("idQues7",idQues7);

            itemView.getContext().startActivity(intent);
        }
    }
}

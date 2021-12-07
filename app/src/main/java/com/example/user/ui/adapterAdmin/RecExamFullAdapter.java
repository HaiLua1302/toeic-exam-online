package com.example.user.ui.adapterAdmin;

import android.app.Activity;
import android.app.Dialog;
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
import com.example.user.ui.admin.fullExam.AddNewAPartActivity;
import com.example.user.ui.classExam.ClsRecExamFull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

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
        holder.imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.clickDel(position);
                getKey.remove(position);
                notifyItemRemoved(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return getKey.size();
    }

    public class RecExamFullHolder extends RecyclerView.ViewHolder {
        private TextView txtNoun,txtNameExam;
        private ImageView imgEdit,imgDel;
        public RecExamFullHolder(@NonNull View itemView) {
            super(itemView);
            txtNoun = itemView.findViewById(R.id.txtNounFull);
            txtNameExam = itemView.findViewById(R.id.txtNameExamFull);
            imgEdit = itemView.findViewById(R.id.imgEditFull);
            imgDel = itemView.findViewById(R.id.imgDelFull);
        }
        private void setData(int pos){
            txtNoun.setText(String.valueOf(pos+1));
            txtNameExam.setText(getKey.get(pos).toString());
        }

        public void clickDel(int pos){
            ViewDialog dialog = new ViewDialog();
            dialog.showDialog((Activity) itemView.getContext(),"Bạn Có Chắc Là Muốn Xóa Nó Chứ ? ",pos);
        }
        //show dialog login success message
        public class ViewDialog {
            public void showDialog(Activity activity, String msg,int pos) {
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
                        delExam(pos);
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
        private void delExam(int pos){
            String child2 = getKey.get(pos).toString();
            FirebaseDatabase.getInstance().getReference("Exam")
                    .child(child2)
                    .setValue(null)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(itemView.getContext(), "Xóa câu hỏi thành công ", Toast.LENGTH_SHORT).show();
                        }
                    });
        }

        private void sendDataEdit(int pos){
            String idExam = getKey.get(pos).toString();

            String idExam1 = clsRecExamFulls.get(pos).getId_part1();
            String idExam2 = clsRecExamFulls.get(pos).getId_part2();
            String idExam3 = clsRecExamFulls.get(pos).getId_part3();
            String idExam4 = clsRecExamFulls.get(pos).getId_part4();
            String idExam5 = clsRecExamFulls.get(pos).getId_part5();
            String idExam6 = clsRecExamFulls.get(pos).getId_part6();
            String idExam7 = clsRecExamFulls.get(pos).getId_part7();

            Intent intent = new Intent(itemView.getContext(), AddNewAPartActivity.class);
            intent.putExtra("idExam",idExam);
            intent.putExtra("idExam1",idExam1);
            intent.putExtra("idExam2",idExam2);
            intent.putExtra("idExam3",idExam3);
            intent.putExtra("idExam4",idExam4);
            intent.putExtra("idExam5",idExam5);
            intent.putExtra("idExam6",idExam6);
            intent.putExtra("idExam7",idExam7);

            itemView.getContext().startActivity(intent);
        }
    }
}

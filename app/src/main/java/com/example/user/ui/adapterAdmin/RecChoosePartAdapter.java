package com.example.user.ui.adapterAdmin;

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
import com.example.user.ui.admin.part3.AddNewQues1P3Activity;
import com.example.user.ui.admin.part4.AddNewQues1P4Activity;
import com.example.user.ui.admin.part5.AddNewQuesP5Activity;
import com.example.user.ui.admin.part6.AddNewQues1P6Activity;
import com.example.user.ui.admin.part7.AddNewQues1P7Activity;

import java.util.List;

public class RecChoosePartAdapter extends RecyclerView.Adapter<RecChoosePartAdapter.RecChoosePartHolder> {
    private String keyExam;
    private List<String> getKey;

    public OnShareClickedListener mCallback;

    public RecChoosePartAdapter() {
    }

    public void setOnShareClickedListener(OnShareClickedListener mCallback) {
        this.mCallback = mCallback;
    }

    public RecChoosePartAdapter(String keyExam, List<String> getKey) {
        this.keyExam = keyExam;
        this.getKey = getKey;
    }

    @NonNull
    @Override
    public RecChoosePartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_rec_choose_part,parent,false);
        return new RecChoosePartHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecChoosePartHolder holder, int position) {
        holder.setData(position);
        holder.imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog = new ProgressDialog(v.getContext());
                progressDialog.setTitle("Wait...");
                progressDialog.show();
                holder.sendDataEdit(position);
                progressDialog.dismiss();
            }
        });
        holder.imgChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.sendDataCallBack(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return getKey.size();
    }

    public class RecChoosePartHolder extends RecyclerView.ViewHolder {
        TextView txtNoun,txtNameExam;
        ImageView imgChoose,imgView;
        android.content.Intent Intent;
        public RecChoosePartHolder(@NonNull View itemView) {
            super(itemView);
            txtNoun = itemView.findViewById(R.id.txtNounPartChoose);
            txtNameExam = itemView.findViewById(R.id.txtNamePartChoose);
            imgChoose = itemView.findViewById(R.id.imgChoosePart);
            imgView = itemView.findViewById(R.id.imgViewPart);
        }

        private void sendDataCallBack(int pos){
            String idPart = getKey.get(pos).toString();
            mCallback.ShareClicked(idPart);
        }

        private void setData(int pos){
            txtNoun.setText(String.valueOf(pos+1));
            txtNameExam.setText(getKey.get(pos));
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
                    Intent = new Intent(itemView.getContext(), AddNewQues1P3Activity.class);
                    Intent.putExtra("idExam",idExam);
                    itemView.getContext().startActivity(Intent);
                    break;
                case "Ques_4":
                    Intent = new Intent(itemView.getContext(), AddNewQues1P4Activity.class);
                    Intent.putExtra("idExam",idExam);
                    itemView.getContext().startActivity(Intent);
                    break;
                case "Ques_5":
                    Intent = new Intent(itemView.getContext(), AddNewQuesP5Activity.class);
                    Intent.putExtra("idExam",idExam);
                    itemView.getContext().startActivity(Intent);

                    break;
                case "Ques_6":
                    Intent = new Intent(itemView.getContext(), AddNewQues1P6Activity.class);
                    Intent.putExtra("idExam",idExam);
                    itemView.getContext().startActivity(Intent);
                    break;
                case "Ques_7":
                    Intent = new Intent(itemView.getContext(), AddNewQues1P7Activity.class);
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

    public interface OnShareClickedListener {
        public void ShareClicked(String url);
    }
}

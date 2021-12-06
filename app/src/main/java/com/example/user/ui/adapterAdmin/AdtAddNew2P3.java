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
import com.example.user.ui.admin.part3.EditAQuesP3;

import java.util.List;

public class AdtAddNew2P3 extends RecyclerView.Adapter<AdtAddNew2P3.RecViewPart3Holder> {
    private List<String> getKey;
    private List<Integer> countTotal;

    public AdtAddNew2P3() {
    }

    public AdtAddNew2P3(List<String> getKey, List<Integer> countTotal) {
        this.getKey = getKey;
        this.countTotal = countTotal;
    }

    @NonNull
    @Override
    public AdtAddNew2P3.RecViewPart3Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_rec_add_new_part3,parent,false);
        return new AdtAddNew2P3.RecViewPart3Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdtAddNew2P3.RecViewPart3Holder holder, int position) {
        holder.setData(position);
        holder.imgEdtHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.sendData(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return getKey.size();
    }

    public class RecViewPart3Holder extends RecyclerView.ViewHolder {
        private TextView txtNounHolder,txtNameExamHolder,txtTotalQuesHolder;
        private ImageView imgEdtHolder;
        public RecViewPart3Holder(@NonNull View itemView) {
            super(itemView);
            txtNounHolder = itemView.findViewById(R.id.txtNoun1P3);
            txtNameExamHolder = itemView.findViewById(R.id.txtNameExam1P3);
            txtTotalQuesHolder = itemView.findViewById(R.id.txtTotalQues1P3);
            imgEdtHolder = itemView.findViewById(R.id.imgSelectEditQues1P3);
        }
        private void setData(int pos){
            txtNounHolder.setText(String.valueOf(pos+1));
            txtNameExamHolder.setText(getKey.get(pos).toString());
            txtTotalQuesHolder.setText(String.valueOf(countTotal.get(pos)));
        }
        private void sendData(int pos){
            Intent intent = new Intent(itemView.getContext(), EditAQuesP3.class);
            String idExam = getKey.get(pos).toString();
            intent.putExtra("idExam",idExam);
            itemView.getContext().startActivity(intent);
        }
    }
}

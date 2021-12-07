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
import com.example.user.ui.admin.part4.EditAQuesP4Activity;

import java.util.List;

public class AddNew2P4Adapter extends RecyclerView.Adapter<AddNew2P4Adapter.RecViewPart3Holder> {
    private List<String> getKey;
    private List<Integer> countTotal;

    public AddNew2P4Adapter() {
    }

    public AddNew2P4Adapter(List<String> getKey, List<Integer> countTotal) {
        this.getKey = getKey;
        this.countTotal = countTotal;
    }

    @NonNull
    @Override
    public AddNew2P4Adapter.RecViewPart3Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_rec_add_new_part3,parent,false);
        return new AddNew2P4Adapter.RecViewPart3Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddNew2P4Adapter.RecViewPart3Holder holder, int position) {
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
            Intent intent = new Intent(itemView.getContext(), EditAQuesP4Activity.class);
            String idExam = getKey.get(pos).toString();
            intent.putExtra("idExam",idExam);
            itemView.getContext().startActivity(intent);
        }
    }
}

package com.example.user.ui.adapterAdmin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.user.R;
import com.example.user.ui.classExam.ClsPartP1;
import com.squareup.picasso.Picasso;

import java.util.List;

public class adtRecViewQuestionP1 extends RecyclerView.Adapter<adtRecViewQuestionP1.RecViewQuestionP1Holder> {

    private List<ClsPartP1> clsPartP1s;

    public adtRecViewQuestionP1() {
    }

    public adtRecViewQuestionP1(List<ClsPartP1> clsPartP1s) {
        this.clsPartP1s = clsPartP1s;
    }

    @NonNull
    @Override
    public adtRecViewQuestionP1.RecViewQuestionP1Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_rec_view_question_p1_admin,parent,false);
        return new  adtRecViewQuestionP1.RecViewQuestionP1Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adtRecViewQuestionP1.RecViewQuestionP1Holder holder, int position) {
        holder.setData(position);
    }

    @Override
    public int getItemCount() {
        return clsPartP1s.size();
    }

    public class RecViewQuestionP1Holder extends RecyclerView.ViewHolder {
        private TextView nounHolder,resultHolder;
        private ImageView imgQuesHolder,imgEditAQuesHolder;

        public RecViewQuestionP1Holder(@NonNull View itemView) {
            super(itemView);
            nounHolder = itemView.findViewById(R.id.txtNounRecQuesP1);
            resultHolder = itemView.findViewById(R.id.txtRecResultAdminP1);
            imgQuesHolder = itemView.findViewById(R.id.imgRecViewAdminP1);
            imgEditAQuesHolder = itemView.findViewById(R.id.imgRecSelectEditQuesP1);
        }

        private void setData(int pos){
            Picasso.get().load(clsPartP1s.get(pos).getUrl_img()).into(imgQuesHolder);
            nounHolder.setText(String.valueOf(pos+1));
            resultHolder.setText(clsPartP1s.get(pos).getResult());
        }
    }

}

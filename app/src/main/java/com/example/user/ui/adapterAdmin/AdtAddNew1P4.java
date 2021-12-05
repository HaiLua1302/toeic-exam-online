package com.example.user.ui.adapterAdmin;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.user.R;
import com.example.user.ui.admin.part4.AddNewQues1P4;
import com.example.user.ui.admin.part4.AddNewQues2P4;
import com.example.user.ui.classExam.ClsRecExamP4;
import com.example.user.ui.classExam.ClsRecExamP4;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class AdtAddNew1P4 extends FirebaseRecyclerAdapter<ClsRecExamP4,AdtAddNew1P4.AddNew1P4Holder> {
    private String nameExam;
    private Context context;

    public AdtAddNew1P4(@NonNull FirebaseRecyclerOptions<ClsRecExamP4> options, String nameExam, Context context) {
        super(options);
        this.nameExam = nameExam;
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull AddNew1P4Holder holder, int position, @NonNull ClsRecExamP4 model) {
        holder.setData(model,position);
        holder.imgEditAQuesHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.sendData(model,position);
            }
        });
    }

    @NonNull
    @Override
    public AddNew1P4Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_rec_add_new_part3,parent,false);
        return new AddNew1P4Holder(view);
    }

    public class AddNew1P4Holder extends RecyclerView.ViewHolder {
        private TextView nounHolder, totalQuesHolder,idQuesHolder;
        private ImageView imgEditAQuesHolder;
        public AddNew1P4Holder(@NonNull View itemView) {
            super(itemView);
            nounHolder = itemView.findViewById(R.id.txtNoun1P3);
            idQuesHolder = itemView.findViewById(R.id.txtNameExam1P3);
            totalQuesHolder = itemView.findViewById(R.id.txtTotalQues1P3);
            imgEditAQuesHolder = itemView.findViewById(R.id.imgSelectEditQues1P3);
        }
        private void setData(ClsRecExamP4 model, int pos){
            idQuesHolder.setText(model.getId_question());
            nounHolder.setText(String.valueOf(pos+1));
            //resultHolder.setText("N/A");
        }
        private void sendData(ClsRecExamP4 model,int pos){
            Intent intent = new Intent(itemView.getContext(), AddNewQues2P4.class);
            String idExam = model.getId_exam().toString();
            String idQues = model.getId_question().toString();
            String urlAudio = model.getUrl_audio().toString();
            intent.putExtra("idExam",idExam);
            intent.putExtra("idQues",idQues);
            intent.putExtra("urlAudio",urlAudio);
            itemView.getContext().startActivity(intent);
        }
    }
}

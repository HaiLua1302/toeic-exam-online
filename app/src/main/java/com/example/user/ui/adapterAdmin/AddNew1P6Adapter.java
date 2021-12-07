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

import com.example.user.ui.admin.part6.AddNewQues2P6Activity;
import com.example.user.ui.classExam.ClsRecExamP6;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class AddNew1P6Adapter extends FirebaseRecyclerAdapter<ClsRecExamP6, AddNew1P6Adapter.AddNew1P6Holder> {
    private String nameExam;
    private Context context;

    public AddNew1P6Adapter(@NonNull FirebaseRecyclerOptions<ClsRecExamP6> options, String nameExam, Context context) {
        super(options);
        this.nameExam = nameExam;
        this.context = context;
    }

    public AddNew1P6Adapter(@NonNull FirebaseRecyclerOptions<ClsRecExamP6> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull AddNew1P6Holder holder, int position, @NonNull ClsRecExamP6 model) {
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
    public AddNew1P6Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_rec_add_new_part3,parent,false);
        return new AddNew1P6Holder(view);
    }

    public class AddNew1P6Holder extends RecyclerView.ViewHolder {
        private TextView nounHolder, totalQuesHolder,idQuesHolder;
        private ImageView imgEditAQuesHolder;
        public AddNew1P6Holder(@NonNull View itemView) {
            super(itemView);
            nounHolder = itemView.findViewById(R.id.txtNoun1P3);
            idQuesHolder = itemView.findViewById(R.id.txtNameExam1P3);
            totalQuesHolder = itemView.findViewById(R.id.txtTotalQues1P3);
            imgEditAQuesHolder = itemView.findViewById(R.id.imgSelectEditQues1P3);
        }
        private void setData(ClsRecExamP6 model, int pos){
            idQuesHolder.setText(model.getId_question());
            nounHolder.setText(String.valueOf(pos+1));
            //resultHolder.setText("N/A");
        }
        private void sendData(ClsRecExamP6 model,int pos){
            Intent intent = new Intent(itemView.getContext(), AddNewQues2P6Activity.class);
            String idExam = model.getId_exam().toString();
            String idQues = model.getId_question().toString();
            String paragraph = model.getParagraph().toString();
            intent.putExtra("idExam",idExam);
            intent.putExtra("idQues",idQues);
            intent.putExtra("paragraph",paragraph);
            intent.putExtra("idExam",idExam);
            intent.putExtra("idQues",idQues);
            intent.putExtra("quesContent","");
            intent.putExtra("ansA","");
            intent.putExtra("ansB","");
            intent.putExtra("ansC","");
            intent.putExtra("ansD","");
            intent.putExtra("result","");
            intent.putExtra("idQuesParent","");

            itemView.getContext().startActivity(intent);
        }
    }
}

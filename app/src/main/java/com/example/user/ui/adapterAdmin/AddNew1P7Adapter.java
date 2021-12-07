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
import com.example.user.ui.admin.part7.AddNewQues2P7Activity;
import com.example.user.ui.classExam.ClsRecExamP7;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class AddNew1P7Adapter extends FirebaseRecyclerAdapter<ClsRecExamP7, AddNew1P7Adapter.AddNew1P7Holder> {
    private String nameExam;
    private Context context;

    public AddNew1P7Adapter(@NonNull FirebaseRecyclerOptions<ClsRecExamP7> options, String nameExam, Context context) {
        super(options);
        this.nameExam = nameExam;
        this.context = context;
    }

    public AddNew1P7Adapter(@NonNull FirebaseRecyclerOptions<ClsRecExamP7> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull AddNew1P7Holder holder, int position, @NonNull ClsRecExamP7 model) {
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
    public AddNew1P7Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_rec_add_new_part3,parent,false);
        return new AddNew1P7Holder(view);
    }

    public class AddNew1P7Holder extends RecyclerView.ViewHolder {
        private TextView nounHolder, totalQuesHolder,idQuesHolder;
        private ImageView imgEditAQuesHolder;
        public AddNew1P7Holder(@NonNull View itemView) {
            super(itemView);
            nounHolder = itemView.findViewById(R.id.txtNoun1P3);
            idQuesHolder = itemView.findViewById(R.id.txtNameExam1P3);
            totalQuesHolder = itemView.findViewById(R.id.txtTotalQues1P3);
            imgEditAQuesHolder = itemView.findViewById(R.id.imgSelectEditQues1P3);
        }
        private void setData(ClsRecExamP7 model, int pos){
            idQuesHolder.setText(model.getId_question());
            nounHolder.setText(String.valueOf(pos+1));
            //resultHolder.setText("N/A");
        }
        private void sendData(ClsRecExamP7 model,int pos){
            Intent intent = new Intent(itemView.getContext(), AddNewQues2P7Activity.class);
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

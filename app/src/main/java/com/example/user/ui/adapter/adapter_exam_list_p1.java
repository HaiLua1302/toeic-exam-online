package com.example.user.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.user.R;
import com.example.user.ui.class_exam.list_exam_1;
import com.example.user.ui.exam.Desc_Fragment_P1;
import com.example.user.ui.exam.Rec_Fragment_P1;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;


public class adapter_exam_list_p1 extends FirebaseRecyclerAdapter<list_exam_1,adapter_exam_list_p1.exam_list_p1_holder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public adapter_exam_list_p1(@NonNull FirebaseRecyclerOptions<list_exam_1> options) {
        super(options);

    }


    @Override
    protected void onBindViewHolder(@NonNull exam_list_p1_holder holder, int position, @NonNull final list_exam_1 model) {
        holder.id_exam.setText("Exam " + (position+1));
        holder.get_dataTodesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity=(AppCompatActivity)v.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.wrapper,new Desc_Fragment_P1(model.getId_exam(),model.getUrl_audio(),0)).addToBackStack(null).commit();
            }
        });
    }

    @NonNull
    @Override
    public exam_list_p1_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_p1,parent,false);
        return new exam_list_p1_holder(view);
    }

    public static class exam_list_p1_holder extends RecyclerView.ViewHolder{

        TextView id_exam ;
        Button get_dataTodesc;
        public exam_list_p1_holder(@NonNull View itemView) {
            super(itemView);
            id_exam = itemView.findViewById(R.id.txt_list_Exam_p1);
            get_dataTodesc = (Button) itemView.findViewById(R.id.btn_start_list_p1);

            get_dataTodesc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AppCompatActivity activity=(AppCompatActivity)v.getContext();
                    activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.wraper1,new Desc_Fragment_P1()).commit();

                }
            });
        }

    }
}

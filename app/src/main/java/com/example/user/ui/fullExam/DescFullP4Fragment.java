package com.example.user.ui.fullExam;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.R;
import com.example.user.ui.adapterUser.AdtDescFullP4;
import com.example.user.ui.classExam.ClsRecExamP4;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DescFullP4Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DescFullP4Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerViewQuestionP4;
    private AdtDescFullP4 adtDescP4;
    private List<ClsRecExamP4> clsRecExamP4s;

    public DescFullP4Fragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static DescFullP4Fragment newInstance(String param1, String param2) {
        DescFullP4Fragment fragment = new DescFullP4Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_decs_p4, container, false);
        recyclerViewQuestionP4 = view.findViewById(R.id.decsListQuestionP4);
        recyclerViewQuestionP4.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        LinearSnapHelper snapHelper = new LinearSnapHelper(){
            @Override
            public int findTargetSnapPosition(RecyclerView.LayoutManager lm, int velocityX, int velocityY){
                View centerView = findSnapView(lm);
                if (centerView == null)
                    return RecyclerView.NO_POSITION;
                int position = lm.getPosition(centerView);
                int targetPosition = -1;
                if (lm.canScrollHorizontally()) {
                    if (velocityX < 0) {
                        targetPosition = position - 1;
                    } else {
                        targetPosition = position + 1;
                    }
                }
                if (lm.canScrollVertically()) {
                    if (velocityY < 0) {
                        targetPosition = position - 1;
                    } else {
                        targetPosition = position + 1;
                    }
                }
                final int firstItem = 0;
                final int lastItem = lm.getItemCount() - 1;
                targetPosition = Math.min(lastItem, Math.max(targetPosition, firstItem));
                return targetPosition;
            }
        };

        snapHelper.attachToRecyclerView(recyclerViewQuestionP4);
//        String child = id_exam + "/" + String.valueOf(id_question);
        clsRecExamP4s = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Ques_4").child("Exam1");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    ClsRecExamP4 partP4 = dataSnapshot.getValue(ClsRecExamP4.class);
                    clsRecExamP4s.add(partP4);
                }
                adtDescP4 = new AdtDescFullP4(clsRecExamP4s);
                recyclerViewQuestionP4.setAdapter(adtDescP4);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        
        return view;
    }
}
package com.example.user.ui.exam2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.R;
import com.example.user.ui.adapter.adapter_exam_list_p1;
import com.example.user.ui.adapter.adtExamList2;
import com.example.user.ui.class_exam.listExam2;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Rec_Fragment_P2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Rec_Fragment_P2 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerViewList2;
    private adtExamList2 adapterExamListP2;


    public Rec_Fragment_P2() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Rec_Fragment_P2 newInstance(String param1, String param2) {
        Rec_Fragment_P2 fragment = new Rec_Fragment_P2();
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
        View view =  inflater.inflate(R.layout.fragment_rec_p2, container, false);
        recyclerViewList2 = view.findViewById(R.id.recView_list_p2);
        recyclerViewList2.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<listExam2>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("Ques_2"),listExam2.class)
                .build();

        adapterExamListP2 = new adtExamList2(options);
        recyclerViewList2.setAdapter(adapterExamListP2);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapterExamListP2.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapterExamListP2.stopListening();
    }
}
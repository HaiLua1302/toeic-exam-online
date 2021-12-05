package com.example.user.ui.exam3;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.R;
import com.example.user.ui.adapterUser.AdtExamListP3;
import com.example.user.ui.classExam.ClsRecExamP3;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class RecP3Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerViewList3;
    private AdtExamListP3 adapterExamList3;
    private List<ClsRecExamP3> ClsRecExamP3s;


    public RecP3Fragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static RecP3Fragment newInstance(String param1, String param2) {
        RecP3Fragment fragment = new RecP3Fragment();
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
        View view =  inflater.inflate(R.layout.fragment_rec_p3, container, false);
        recyclerViewList3 = view.findViewById(R.id.recViewListP3);
        recyclerViewList3.setLayoutManager(new LinearLayoutManager(getContext()));

        ClsRecExamP3s = new ArrayList<>();
        List<String> key = new ArrayList<>();
        DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference().child("Ques_3");

        ref1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot locationSnapshot: snapshot.getChildren()){
                    for (DataSnapshot dataSnapshot : locationSnapshot.getChildren()){
                        ClsRecExamP3 recP3 = dataSnapshot.getValue(ClsRecExamP3.class);
                        ClsRecExamP3s.add(recP3);
                    }
                }
                DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference().child("Ques_3");
                ref2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                            String keyExam = dataSnapshot.getKey();
                            key.add(keyExam);
                            adapterExamList3 = new AdtExamListP3(ClsRecExamP3s,key);
                            recyclerViewList3.setAdapter(adapterExamList3);
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        return  view;
    }


}
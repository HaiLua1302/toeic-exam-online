package com.example.user.ui.exam4;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.R;
import com.example.user.ui.adapter.AdtExamListP4;
import com.example.user.ui.adapter.AdtExamListP4;
import com.example.user.ui.class_exam.ClsRecExamP4;
import com.example.user.ui.class_exam.ClsRecExamP4;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecP4Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecP4Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerViewList4;
    private AdtExamListP4 adapterExamList4;
    private List<ClsRecExamP4> clsRecP4s ;

    public RecP4Fragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static RecP4Fragment newInstance(String param1, String param2) {
        RecP4Fragment fragment = new RecP4Fragment();
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
        View view = inflater.inflate(R.layout.fragment_rec_p4, container, false);
        recyclerViewList4 = view.findViewById(R.id.recViewListP4);
        recyclerViewList4.setLayoutManager(new LinearLayoutManager(getContext()));

        clsRecP4s = new ArrayList<>();

        DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference().child("Ques_4");

        ref1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot locationSnapshot: snapshot.getChildren()){
                    for (DataSnapshot dataSnapshot : locationSnapshot.getChildren()){
                        ClsRecExamP4 recP4 = dataSnapshot.getValue(ClsRecExamP4.class);
                        clsRecP4s.add(recP4);
                    }
                }
                adapterExamList4 = new AdtExamListP4(clsRecP4s);
                recyclerViewList4.setAdapter(adapterExamList4);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        return view;
    }
}
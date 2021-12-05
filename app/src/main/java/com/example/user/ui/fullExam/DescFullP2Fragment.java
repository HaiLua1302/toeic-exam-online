package com.example.user.ui.fullExam;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.R;
import com.example.user.ui.adapterUser.AdtDescFullP2;
import com.example.user.ui.classExam.ClsPartP2;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DescFullP2Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DescFullP2Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private AdtDescFullP2 adtDescFullP2;
    private List<ClsPartP2> clsPartP2s;
    private String getKey;
    private RecyclerView recyclerViewQuestionFull2;

    public DescFullP2Fragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static DescFullP2Fragment newInstance(String param1, String param2) {
        DescFullP2Fragment fragment = new DescFullP2Fragment();
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
        View view = inflater.inflate(R.layout.fragment_desc_p2, container, false);

        recyclerViewQuestionFull2 = view.findViewById(R.id.recQuestionP2);
        recyclerViewQuestionFull2.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

//        getKey = getArguments().getString("getKey");
        clsPartP2s = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("List_Ques2").child("Exam1");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    ClsPartP2 partP2 = dataSnapshot.getValue(ClsPartP2.class);
                    clsPartP2s.add(partP2);
                }
                adtDescFullP2 = new AdtDescFullP2(clsPartP2s);
                recyclerViewQuestionFull2.setAdapter(adtDescFullP2);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        return view;
    }
}
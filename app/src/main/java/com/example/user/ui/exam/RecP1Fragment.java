package com.example.user.ui.exam;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.R;
import com.example.user.ui.adapter.AdtExamListP1;
import com.example.user.ui.class_exam.ClsRecExamP1;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecP1Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecP1Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView_list;
    private AdtExamListP1 adapterExamListP1;

    public RecP1Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Rec_Fragment_P1.
     */
    // TODO: Rename and change types and number of parameters
    public static RecP1Fragment newInstance(String param1, String param2) {
        RecP1Fragment fragment = new RecP1Fragment();
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
        View view = inflater.inflate(R.layout.fragment_rec_p1, container, false);

        recyclerView_list=(RecyclerView)view.findViewById(R.id.recView_list_p1);
        recyclerView_list.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<ClsRecExamP1> options =
                new FirebaseRecyclerOptions.Builder<ClsRecExamP1>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Ques_1"), ClsRecExamP1.class)
                        .build();

        adapterExamListP1 = new AdtExamListP1(options);
        recyclerView_list.setAdapter(adapterExamListP1);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapterExamListP1.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapterExamListP1.stopListening();
    }

}
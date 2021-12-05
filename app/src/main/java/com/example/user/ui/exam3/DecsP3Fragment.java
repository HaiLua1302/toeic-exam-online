package com.example.user.ui.exam3;

import android.app.Activity;
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
import com.example.user.ui.adapterUser.AdtDescP3;
import com.example.user.ui.classExam.ClsRecExamP3;
import com.example.user.ui.exam1.DescP1Fragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DecsP3Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerViewQuestionP3;
    private AdtDescP3 adtDescP3;


    String id_exam;
    String id_question;
    String url_audio;
    String Key;


    public DecsP3Fragment() {
        // Required empty public constructor
    }

    public DecsP3Fragment(String key) {
        Key = key;
    }

    private DecsP3Fragment.OnFragmentInteractionListener mListener;

    public DecsP3Fragment(DecsP3Fragment.OnFragmentInteractionListener mListener) {
        this.mListener = mListener;
    }

    // TODO: Rename and change types and number of parameters
    public static DecsP3Fragment newInstance(String param1, String param2) {
        DecsP3Fragment fragment = new DecsP3Fragment();
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
        View view = inflater.inflate(R.layout.fragment_desc_p3, container, false);

        if (mListener != null) {
            mListener.onFragmentInteraction("Part III : Short Conversations");
        }

        recyclerViewQuestionP3 = view.findViewById(R.id.decsListQuestionP3);
        recyclerViewQuestionP3.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
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

        snapHelper.attachToRecyclerView(recyclerViewQuestionP3);
//        String child = id_exam + "/" + String.valueOf(id_question);
        /*FirebaseRecyclerOptions<ClsRecExamP3> options = new FirebaseRecyclerOptions.Builder<ClsRecExamP3>()
                .setQuery(FirebaseDatabase.getInstance().getReference("Ques_3").child(id_exam), ClsRecExamP3.class)
                .build();*/
        List<ClsRecExamP3> clsRecExamP3List = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Ques_3").child(Key);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    ClsRecExamP3 clsRecExamP3 = dataSnapshot.getValue(ClsRecExamP3.class);
                    clsRecExamP3List.add(clsRecExamP3);
                }
                adtDescP3 = new AdtDescP3(clsRecExamP3List);
                recyclerViewQuestionP3.setAdapter(adtDescP3);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }
    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (DecsP3Fragment.OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(String title);
    }
}
package com.example.user.ui.user.exam5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.user.R;
import com.example.user.ui.adapterUser.AdtDescP5;
import com.example.user.ui.classExam.ClsPartP5;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DescP5Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DescP5Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView recyclerViewQuestionP5;
    private AdtDescP5 adtDescP5;
    private List<ClsPartP5> clsRecP5s;
    private Button btnSubmit;

    String keyExam;
    private DescP5Fragment.OnFragmentInteractionListener mListener;

    public DescP5Fragment(DescP5Fragment.OnFragmentInteractionListener mListener) {
        this.mListener = mListener;
    }
    public DescP5Fragment(String keyExam) {
        this.keyExam = keyExam;
    }

    public DescP5Fragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static DescP5Fragment newInstance(String param1, String param2) {
        DescP5Fragment fragment = new DescP5Fragment();
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
        View view = inflater.inflate(R.layout.fragment_desc_p5, container, false);
        if (mListener != null) {
            mListener.onFragmentInteraction("Part V : Incomplete Sentences");
        }
        clsRecP5s = new ArrayList<>();
        recyclerViewQuestionP5 = view.findViewById(R.id.decsListQuestionP5);
        recyclerViewQuestionP5.setLayoutManager(new LinearLayoutManager(getContext()));
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

        snapHelper.attachToRecyclerView(recyclerViewQuestionP5);


        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Ques_5").child(keyExam);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    ClsPartP5 partP5 = dataSnapshot.getValue(ClsPartP5.class);
                    clsRecP5s.add(partP5);
                }
                adtDescP5 = new AdtDescP5(clsRecP5s);
                recyclerViewQuestionP5.setAdapter(adtDescP5);
                adtDescP5.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnSubmit = view.findViewById(R.id.btnSubmitP5_1);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(),ResultP5Activity.class);
                view.getContext().startActivity(intent);
            }
        });

     /*   adtDescP5 = new AdtDescP5(options);
        recyclerViewQuestionP5.setAdapter(adtDescP5);*/

        return view ;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (DescP5Fragment.OnFragmentInteractionListener) activity;
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
package com.example.user.ui.user.exam4;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.R;
import com.example.user.ui.adapterUser.AdtDescP4;
import com.example.user.ui.classExam.ClsRecExamP4;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@li    nk Fragment} subclass.
 * Use the {@link DecsP4Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DecsP4Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerViewQuestionP4;
    private AdtDescP4 adtDescP4;


    String id_exam;
    String id_question;
    String url_audio;
    private DecsP4Fragment.OnFragmentInteractionListener mListener;

    public DecsP4Fragment(DecsP4Fragment.OnFragmentInteractionListener mListener) {
        this.mListener = mListener;
    }

    public DecsP4Fragment() {
        // Required empty public constructor
    }

    public DecsP4Fragment(String id_exam, String id_question, String url_audio) {
        this.id_exam = id_exam;
        this.id_question = id_question;
        this.url_audio = url_audio;
    }

    // TODO: Rename and change types and number of parameters
    public static DecsP4Fragment newInstance(String param1, String param2) {
        DecsP4Fragment fragment = new DecsP4Fragment();
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
        if (mListener != null) {
            mListener.onFragmentInteraction("Part IV : Short Talks");
        }
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
        FirebaseRecyclerOptions<ClsRecExamP4> options = new FirebaseRecyclerOptions.Builder<ClsRecExamP4>()
                .setQuery(FirebaseDatabase.getInstance().getReference("Ques_4").child(id_exam), ClsRecExamP4.class)
                .build();

        adtDescP4 = new AdtDescP4(options);
        recyclerViewQuestionP4.setAdapter(adtDescP4);
        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        adtDescP4.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adtDescP4.stopListening();
    }
    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (DecsP4Fragment.OnFragmentInteractionListener) activity;
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
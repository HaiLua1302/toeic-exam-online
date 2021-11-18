package com.example.user.ui.exam;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.R;
import com.example.user.ui.class_exam.cls_part_1;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link exam_slide_p1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class exam_slide_p1 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    ArrayList<cls_part_1> arr_Ques_p1;
    public static final String ARG_PAGE = "Page";
    private int mPageNumber; //trang hien taij
    TextView txt_num ;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public exam_slide_p1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment exam_slide_p1.
     */
    // TODO: Rename and change types and number of parameters
    public static exam_slide_p1 newInstance(int pageNumber) {
        exam_slide_p1 fragment = new exam_slide_p1();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE,pageNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arr_Ques_p1 = new ArrayList<>();
        exam_part_1 activity_Exam_p1 = (exam_part_1) getActivity();
        arr_Ques_p1 = activity_Exam_p1.getData_p1();
        mPageNumber = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.exam_p1, container, false);
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.exam_p1,container,false);
        txt_num = rootView.findViewById(R.id.txt_NumPage);
        return  rootView;
    }

    public static slide_exam_p1 create(int pageNumber){
        slide_exam_p1 fragment = new slide_exam_p1();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE,pageNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle saveInstanceState){
        super.onActivityCreated(saveInstanceState);
        txt_num.setText("Cau " + (arr_Ques_p1.get(mPageNumber).getId_ques_1()));
    }
}
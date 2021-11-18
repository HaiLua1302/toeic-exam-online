package com.example.user.ui.exam;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.user.R;
import com.example.user.ui.class_exam.cls_part_1;

import java.util.ArrayList;

public class slide_exam_p1 extends Fragment {

    ArrayList<cls_part_1> arr_Ques_p1;
    public static final String ARG_PAGE = "Page";
    private int mPageNumber; //trang hien taij
    TextView txt_num ;

    public slide_exam_p1(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        //Inflate the ayout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.exam_p1,container,false);

        txt_num = rootView.findViewById(R.id.txt_NumPage);
        return  rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle saveInstanceState){
        super.onCreate(saveInstanceState);

        arr_Ques_p1 = new ArrayList<>();
        exam_part_1 activity_Exam_p1 = (exam_part_1) getActivity();
        arr_Ques_p1 = activity_Exam_p1.getData_p1();
        mPageNumber = getArguments().getInt(ARG_PAGE);
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
package com.example.user.ui.adapterAdmin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.user.R;
import com.example.user.ui.classAdmin.clsPartExam;

import java.util.ArrayList;

public class AdtSpinerPart extends ArrayAdapter<clsPartExam> {
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    public AdtSpinerPart(@NonNull Context context, ArrayList<clsPartExam>clsPartExams) {
        super(context, 0,clsPartExams);

    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position,View partView,ViewGroup viewGroup){
        if (partView == null)
        {
            partView = LayoutInflater.from(getContext()).inflate(R.layout.custom_spinner_part,viewGroup,false);
        }
        TextView textView = partView.findViewById(R.id.txtPartQuestion);

        clsPartExam clsPartExam = getItem(position);

        if (clsPartExam != null){
            textView.setText(clsPartExam.getPart());
        }


        return partView;
    }
}

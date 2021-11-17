package com.example.user.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.user.R;
import com.example.user.models.CauHoi;
import com.example.user.models.CauHoiModel;

import java.util.ArrayList;
import java.util.List;

public class AdapterChonCauHoi extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<CauHoi> data;

    public AdapterChonCauHoi(Context context, int resource, ArrayList<CauHoi> data) {
        super(context, resource, data);
        this.context = context;
        this.resource = resource;
        this.data = data;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, null);
            viewHolder = new ViewHolder();

            viewHolder.txtNoiDungCauHoi = convertView.findViewById(R.id.txtNoiDungCauHoi);
            viewHolder.txtidCauHoi = convertView.findViewById(R.id.txtidCauHoi);
            viewHolder.chkCheckBox = convertView.findViewById(R.id.chkCheckBox);


            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        CauHoi cauHoi = data.get(position);
        viewHolder.txtNoiDungCauHoi.setText(cauHoi.getNoiDungCauHoi());
        viewHolder.txtidCauHoi.setText(cauHoi.getId());

        viewHolder.chkCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return convertView;
    }

    @Override
    public int getCount() {
        return data.size();
    }


    public static class ViewHolder {
        CheckBox chkCheckBox;
        TextView txtNoiDungCauHoi;
        TextView txtidCauHoi;
    }
}

package com.example.user.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.user.R;
import com.example.user.models.CauHoi;

import java.util.ArrayList;

public class AdapterQuanLyCauHoi extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<CauHoi> data;
    ClickCauHoi clickCauHoi;

    public AdapterQuanLyCauHoi(Context context, int resource, ArrayList<CauHoi> data, ClickCauHoi clickCauHoi) {
        super(context, resource, data);
        this.context = context;
        this.resource = resource;
        this.data = data;
        this.clickCauHoi = clickCauHoi;
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
            viewHolder.idCauHoi = convertView.findViewById(R.id.idCauHoi);


            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        CauHoi cauHoi = data.get(position);
        viewHolder.txtNoiDungCauHoi.setText(cauHoi.getNoiDungCauHoi());
        viewHolder.txtidCauHoi.setText(cauHoi.getId());

        viewHolder.txtidCauHoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickCauHoi.clickItemCauHoi(cauHoi);
            }
        }); viewHolder.txtNoiDungCauHoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickCauHoi.clickItemCauHoi(cauHoi);
            }
        });



        return convertView;
    }

    @Override
    public int getCount() {
        return data.size();
    }


    public static class ViewHolder {
        TextView idCauHoi;
        TextView txtNoiDungCauHoi;
        TextView txtidCauHoi;
    }
}

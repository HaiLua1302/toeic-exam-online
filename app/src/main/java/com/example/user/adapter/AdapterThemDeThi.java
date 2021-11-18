package com.example.user.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.user.R;
import com.example.user.models.CauHoiModel;

import java.util.ArrayList;

public class AdapterThemDeThi extends ArrayAdapter {


    Context context;
    int resource;
    ArrayList<CauHoiModel> data;
    ClickAddQuenstion clickAddQuenstion;

    public AdapterThemDeThi(Context context, int resource, ArrayList<CauHoiModel> data, ClickAddQuenstion clickAddQuenstion) {
        super(context, resource, data);
        this.context = context;
        this.resource=resource;
        this.data=data;
        this.clickAddQuenstion = clickAddQuenstion;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder =null;
        if (convertView==null){
            convertView = LayoutInflater.from(context).inflate(resource, null);
            viewHolder = new ViewHolder();

            viewHolder.btnAddCauHoi = convertView.findViewById(R.id.btnAddCauHoi);
            viewHolder.txtPhan = convertView.findViewById(R.id.txtPhan);
            viewHolder.txtCauHoi = convertView.findViewById(R.id.txtCauHoi);


            convertView.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        CauHoiModel cauHoi = data.get(position);
        viewHolder.txtPhan.setText(cauHoi.getPhan());
        viewHolder.txtCauHoi.setText(cauHoi.getCacCauHoi());

        viewHolder.btnAddCauHoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickAddQuenstion.clickAddQuenstion(cauHoi);
            }
        });
        return convertView;
    }

    @Override
    public int getCount() {
        return data.size();
    }


    public  static  class  ViewHolder{
        TextView txtPhan ;
        TextView txtCauHoi;
        ImageView btnAddCauHoi;
    }
}

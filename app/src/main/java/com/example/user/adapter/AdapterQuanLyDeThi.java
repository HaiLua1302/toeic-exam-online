package com.example.user.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.user.R;
import com.example.user.models.DeThi;

import java.util.ArrayList;

public class AdapterQuanLyDeThi extends ArrayAdapter {

    Context context;
    int resource;
    ArrayList<DeThi> data;
    ClickItemBoDe clickItemBoDe;

    public AdapterQuanLyDeThi(Context context, int resource, ArrayList<DeThi> data, ClickItemBoDe clickItemBoDe) {
        super(context, resource, data);
        this.context = context;
        this.resource = resource;
        this.data = data;
        this.clickItemBoDe = clickItemBoDe;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, null);

            viewHolder = new ViewHolder();
//            viewHolder.txtBoDe = convertView.findViewById(R.id.txtBoDe);
//            viewHolder.txtIDBoDe = convertView.findViewById(R.id.txtIDBoDe);
//            viewHolder.txtNgayThemBoDe = convertView.findViewById(R.id.txtNgayThemBoDe);
//            viewHolder.lnearitemQuanLyDeThi = convertView.findViewById(R.id.lnearitemQuanLyDeThi);
//

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        DeThi deThi = data.get(position);
        viewHolder.txtIDBoDe.setText(deThi.getId());
        viewHolder.txtBoDe.setText(deThi.getBoDe());
        viewHolder.txtNgayThemBoDe.setText(deThi.getNgayThem());


        if (position / 2 == 1) {
            viewHolder.lnearitemQuanLyDeThi.setBackgroundColor(Color.GREEN);
        } else {
            viewHolder.lnearitemQuanLyDeThi.setBackgroundColor(Color.WHITE);
        }


        viewHolder.txtBoDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickItemBoDe.clickItemBoDe(deThi);
            }
        });
        viewHolder.txtIDBoDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickItemBoDe.clickItemBoDe(deThi);
            }
        });
        viewHolder.txtNgayThemBoDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickItemBoDe.clickItemBoDe(deThi);
            }
        });


        return convertView;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    public static class ViewHolder {
        TextView txtIDBoDe;
        TextView txtBoDe;
        TextView txtNgayThemBoDe;
        LinearLayout lnearitemQuanLyDeThi;
    }
}

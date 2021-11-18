package com.example.user.ui.admin;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.user.R;
import com.example.user.adapter.AdapterChonCauHoi;
import com.example.user.models.CauHoi;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ChonCauHoi extends AppCompatActivity {
    EditText txtSearchCauHoi;
    Spinner spLocCauHoi;
    ListView lvCauHoi;

    ArrayList<CauHoi> list;
    AdapterChonCauHoi adapterChonCauHoi;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_cau_hoi);
        setControl();
        setAction();
    }
    private void setAction() {
        list = new ArrayList<>();
        list.add(new CauHoi("12","dfgdfgdfgdfgdfg","A"));
        list.add(new CauHoi("12","dfgdfgdfgdfgdfg","A"));
        list.add(new CauHoi("12","dfgdfgdfgdfgdfg","A"));
        list.add(new CauHoi("12","dfgdfgdfgdfgdfg","A"));
        list.add(new CauHoi("12","dfgdfgdfgdfgdfg","A"));
        list.add(new CauHoi("12","dfgdfgdfgdfgdfg","A"));
        list.add(new CauHoi("12","dfgdfgdfgdfgdfg","A"));
        list.add(new CauHoi("12","dfgdfgdfgdfgdfg","A"));
        list.add(new CauHoi("12","dfgdfgdfgdfgdfg","A"));
        list.add(new CauHoi("12","dfgdfgdfgdfgdfg","A"));
        list.add(new CauHoi("12","dfgdfgdfgdfgdfg","A"));
        list.add(new CauHoi("12","dfgdfgdfgdfgdfg","A"));
        list.add(new CauHoi("12","dfgdfgdfgdfgdfg","A"));
        list.add(new CauHoi("12","dfgdfgdfgdfgdfg","A"));


        adapterChonCauHoi = new AdapterChonCauHoi(getApplicationContext(), R.layout.item_chon_cau_hoi, list);
        lvCauHoi.setAdapter(adapterChonCauHoi);
    }

    private void setControl() {
        txtSearchCauHoi = findViewById(R.id.txtSearchCauHoi);
        spLocCauHoi = findViewById(R.id.spLocCauHoi);
        lvCauHoi = findViewById(R.id.lvCauHoi);
    }
}

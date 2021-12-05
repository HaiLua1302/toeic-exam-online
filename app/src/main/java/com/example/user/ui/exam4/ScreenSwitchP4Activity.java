package com.example.user.ui.exam4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

import com.example.user.R;
import com.example.user.ui.exam3.DecsP3Fragment;

public class ScreenSwitchP4Activity extends AppCompatActivity implements DecsP4Fragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_switch_p4);
        getSupportActionBar().setTitle("Danh sách đề thi");
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportFragmentManager().beginTransaction().replace(R.id.wraper4,new RecP4Fragment()).commit();
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    };

    @Override
    public void onFragmentInteraction(String title) {
        getSupportActionBar().setTitle(title);
    }
}
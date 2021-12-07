package com.example.user.ui.admin.part3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.user.R;
import com.example.user.ui.adapterAdmin.AddNew2P3Adapter;
import com.example.user.ui.admin.AdminHomeActivity;
import com.example.user.ui.admin.ManagerExamActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RecViewExamP3Activity extends AppCompatActivity {

    private Button btnAddNew;
    private RecyclerView recyclerView;
    private AddNew2P3Adapter addNew2P3Adapter;
    private DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rec_view_exam_p3);
        getSupportActionBar().setTitle("Part 3 Manager");
        // calling the action bar
        ActionBar actionBar = getSupportActionBar();
        // Customize the back button
        actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24);
        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        btnAddNew = findViewById(R.id.btnIntentToAddQuesRecP3);
        recyclerView = findViewById(R.id.recViewListQuestRecP3);

        getDataFirebase();
        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(RecViewExamP3Activity.this, AddNewQues1P3Activity.class);
               startActivity(intent);
            }
        });
    }
    private void getDataFirebase(){
        List<String> getKey = new ArrayList<>();
        List<Integer> countTotal = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ref = FirebaseDatabase.getInstance().getReference("Ques_3");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        String key = dataSnapshot.getKey();
                        long count = snapshot.getChildrenCount();
                        countTotal.add((int) count);
                        getKey.add(key);
                    }
                    addNew2P3Adapter = new AddNew2P3Adapter(getKey,countTotal);
                    recyclerView.setAdapter(addNew2P3Adapter);
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    // this event will enable the back
    // function to the button on press
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(RecViewExamP3Activity.this, ManagerExamActivity.class);
                startActivity(intent);
            case R.id.home_bar_admin:
                Intent intent2 = new Intent(RecViewExamP3Activity.this, AdminHomeActivity.class);
                startActivity(intent2);
                return true;
        }
        return super.onOptionsItemSelected(item);
    };

}
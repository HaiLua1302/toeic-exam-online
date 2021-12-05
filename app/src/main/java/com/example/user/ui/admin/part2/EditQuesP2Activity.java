package com.example.user.ui.admin.part2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.R;
import com.example.user.ui.adapterAdmin.AdtRecEditQuesP2;
import com.example.user.ui.admin.ManagerExamActivity;
import com.example.user.ui.classExam.ClsPartP2;
import com.example.user.ui.classExam.ClsRecExamP2;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class EditQuesP2Activity extends AppCompatActivity {

    private TextView txtNameExam, txtNumQues, txtUrlAudio, txtTimeCurrent, txtTimeTotal;
    private ImageView imgRefesh,imgPlayPause;
    private SeekBar seekBar;
    private MediaPlayer mediaPlayer;
    private Handler handler = new Handler();
    private DatabaseReference ref;

    private Uri filePath_AUD;
    private final int PICK_AUDIO_REQUEST = 1;

    private RecyclerView recyclerView;
    private Button btnChooseAudio,btnSaveFileAudio,btnDelExam;

    private List<ClsPartP2> clsPartP2List;
    private AdtRecEditQuesP2 adtRecViewQuetionP2;

    private StorageReference storageReference;
    private FirebaseStorage storage;

    private String idExam,urlAudio;
    private int iNumQues = 0;
    
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desc_exam_edit_p2);
        getSupportActionBar().setTitle("Edit Question Part 2");
        // calling the action bar
        ActionBar actionBar = getSupportActionBar();
        // Customize the back button
        actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24);
        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        idExam = bundle.getString("idExam");
        iNumQues = bundle.getInt("numQues");

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        txtNameExam = findViewById(R.id.txtNameExamEditP2);
        //numQues = findViewById(R.id.txtNumEditQuestP2);
        txtUrlAudio = findViewById(R.id.txtFilePathEditAudioP2);
        txtTimeCurrent = findViewById(R.id.txtCurrentTimeEditP2);
        txtTimeTotal = findViewById(R.id.txtTimeTotalEditP2);

        imgRefesh = findViewById(R.id.imgRefreshEditP2);
        imgPlayPause = findViewById(R.id.imgPlayAudEditP2);

        seekBar = findViewById(R.id.playerSeekBarEditP2);
        seekBar.setMax(100);

        mediaPlayer = new MediaPlayer();

        recyclerView = findViewById(R.id.recViewListQuestEditP2);

        btnChooseAudio = findViewById(R.id.btnEditAudioP2);
        btnSaveFileAudio = findViewById(R.id.btnSaveEditAudioP2);
        btnDelExam = findViewById(R.id.btnDelExamP2);

        txtNameExam.setText(idExam);
        //numQues.setText(String.valueOf(iNumQues));
        setDataToRecViewDefault();
        imgRefesh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDataToRecViewDefault();
            }
        });

        imgPlayPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getNameAudioFromUrl();
                if(mediaPlayer.isPlaying()){
                    handler.removeCallbacks(runnable);
                    mediaPlayer.pause();
                    imgPlayPause.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                }else {
                    mediaPlayer.start();
                    imgPlayPause.setImageResource(R.drawable.ic_baseline_pause_24);
                    updateSeekbar();
                }
            }
        });
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                imgPlayPause.performClick();
            }
        });
        prepareMediaPlayer();
        seekBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                SeekBar seekBar = (SeekBar) v;
                int playPostion = (mediaPlayer.getDuration()/100)*seekBar.getProgress();
                mediaPlayer.seekTo(playPostion);
                txtTimeCurrent.setText(milliSecondsToTimer(mediaPlayer.getCurrentPosition()));
                return false;
            }
        });
        mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                seekBar.setSecondaryProgress(percent);
            }
        });

        btnChooseAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseAudio();
            }
        });

        btnSaveFileAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveEditQues();
            }
        });
        btnDelExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delExam();
            }
        });
    }

    private void setDataToRecViewDefault(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<ClsPartP2> options =
                new FirebaseRecyclerOptions.Builder<ClsPartP2>()
                        .setQuery(FirebaseDatabase.getInstance().getReference("List_Ques2").child(idExam), ClsPartP2.class)
                        .build();

        adtRecViewQuetionP2 = new AdtRecEditQuesP2(options,this,idExam);
        recyclerView.setAdapter(adtRecViewQuetionP2);
        adtRecViewQuetionP2.startListening();
    }

    private void getNameAudioFromUrl(){
        ref = FirebaseDatabase.getInstance().getReference("Ques_2").child(idExam);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                urlAudio = snapshot.child("url_audio").getValue().toString();
                storage = FirebaseStorage.getInstance();
                storageReference = storage.getReference();
                storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(urlAudio);
                String link = storageReference.getName();
                txtUrlAudio.setText(link);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void prepareMediaPlayer(){
        ref = FirebaseDatabase.getInstance().getReference("Ques_2").child(idExam);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                urlAudio = snapshot.child("url_audio").getValue().toString();
                try {
                    mediaPlayer.setDataSource(urlAudio);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                txtTimeTotal.setText(milliSecondsToTimer(mediaPlayer.getDuration()));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
    }
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            updateSeekbar();
            long currentDuration = mediaPlayer.getCurrentPosition();
            txtTimeCurrent.setText(milliSecondsToTimer(currentDuration));
        }
    };
    private void updateSeekbar(){
        if(mediaPlayer.isPlaying()){
            seekBar.setProgress((int)(((float)mediaPlayer.getCurrentPosition() / mediaPlayer.getDuration()) * 100));
            handler.postDelayed(runnable,1000);
        }
    }
    private String milliSecondsToTimer(long milliSecons){
        String timeString = "";
        String secondString = "";

        int hours = (int) (milliSecons / (1000*60*60));
        int mins = (int) (milliSecons % (1000*60*60)) / (1000*60);
        int secs = (int) (milliSecons % (1000*60*60) % (1000*60) / 1000);

        if (hours > 0){
            timeString = hours + ":";
        }
        if (secs < 10){
            secondString = "0" + secs;
        } else {  secondString = "" + secs;     }
        timeString = timeString + mins + ":" + secondString;
        return timeString;
    }

    /*
     * This method is choose img to the
     * */
    private void chooseAudio(){
        Intent intent = new Intent();
        intent.setType("audio/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        //noinspection deprecation
        startActivityForResult(Intent.createChooser(intent, "Select Audio"), PICK_AUDIO_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_AUDIO_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {

            filePath_AUD = data.getData();
            txtUrlAudio.setText(filePath_AUD.toString());
        }
    };
    private void editAQuesAudio(String url_audio){
        //getting the reference of question part 1 node
        ref = FirebaseDatabase.getInstance().getReference().child("Ques_2");
        ClsRecExamP2 clsPartP2 = new ClsRecExamP2(idExam,url_audio);
        ref.child(idExam).setValue(clsPartP2).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                }
            }
        });
    }
    private void saveEditQues()
    {

        //create id question
        String ID_Quest = idExam + "_" + getTime();
        delAudioCurrent();
        if(filePath_AUD != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);

            progressDialog.setTitle("Edit Audio...");
            progressDialog.show();

            StorageReference ref_IMG = storageReference.child("audio_exam/"+  ID_Quest);
            ref_IMG.putFile(filePath_AUD)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            ref_IMG.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String url_IMG = uri.toString();
                                    editAQuesAudio(url_IMG);
                                    Toast.makeText(EditQuesP2Activity.this, "Đã lưu thay đổi thành công ", Toast.LENGTH_SHORT).show();
                                    storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(url_IMG);
                                    String link = storageReference.getName();
                                    txtUrlAudio.setText(link);
                                    progressDialog.dismiss();
                                }
                            });

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(EditQuesP2Activity.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
        }
        else {
            Toast.makeText(EditQuesP2Activity.this, "Chưa chọn file Audio ", Toast.LENGTH_SHORT).show();
        }
    }
    private void delAudioCurrent(){
        ref = FirebaseDatabase.getInstance().getReference("Ques_2").child(idExam);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                urlAudio = snapshot.child("url_audio").getValue().toString();
                storage = FirebaseStorage.getInstance();
                storageReference = storage.getReference();
                storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(urlAudio);
                String link = storageReference.getName();
                StorageReference removeImg = storage.getReference("audio_exam").child(link);
                removeImg.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void delExam(){
        String idExam = txtNameExam.getText().toString();
        FirebaseDatabase.getInstance().getReference()
                .child("List_Ques2")
                .child(idExam)
                .setValue(null)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        delAudioCurrent();
                        FirebaseDatabase.getInstance().getReference()
                                .child("Ques_2")
                                .child(idExam)
                                .setValue(null)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(EditQuesP2Activity.this, "Xóa câu hỏi thành công ", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(EditQuesP2Activity.this, ManagerExamActivity.class);
                                        startActivity(intent);
                                    }
                                });
                    }
                });
    }
    /*
     gettime current
     * */
    private String getTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());
        return  currentDateandTime;
    }
    // this event will enable the back
    // function to the button on press
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    };
}
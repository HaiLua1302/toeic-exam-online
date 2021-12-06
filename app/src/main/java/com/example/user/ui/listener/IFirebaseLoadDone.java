package com.example.user.ui.listener;

import com.example.user.ui.classExam.ClsPartP1;

import java.util.List;

public interface IFirebaseLoadDone {
    void onFirebaseLoadSuccess(List<ClsPartP1> clsPartP_1List);
    void onFirebaseLoadFailed(String message);
}

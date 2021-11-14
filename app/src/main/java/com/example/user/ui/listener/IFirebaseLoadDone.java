package com.example.user.ui.listener;

import com.example.user.ui.class_exam.ClsPartP1;

import java.util.List;

public interface IFirebaseLoadDone {
    void onFirebaseLoadSuccess(List<ClsPartP1> clsPartP_1List);
    void onFirebaseLoadFailed(String message);
}

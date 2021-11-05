package com.example.user.ui.listener;

import com.example.user.ui.class_exam.cls_part_1;

import java.util.List;

public interface IFirebaseLoadDone {
    void onFirebaseLoadSuccess(List<cls_part_1> cls_part_1List);
    void onFirebaseLoadFailed(String message);
}

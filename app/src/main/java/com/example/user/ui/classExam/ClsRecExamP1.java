package com.example.user.ui.classExam;

public class ClsRecExamP1 {
    String id_exam,url_audio;

    public ClsRecExamP1() {
    }

    public ClsRecExamP1(String id_exam, String url_audio) {
        this.id_exam = id_exam;
        this.url_audio = url_audio;
    }

    public String getId_exam() {
        return id_exam;
    }

    public void setId_exam(String id_exam) {
        this.id_exam = id_exam;
    }

    public String getUrl_audio() {
        return url_audio;
    }

    public void setUrl_audio(String url_audio) {
        this.url_audio = url_audio;
    }
}

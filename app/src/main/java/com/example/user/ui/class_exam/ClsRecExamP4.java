package com.example.user.ui.class_exam;

public class ClsRecExamP4 {
    String id_exam,url_audio;
    int id_question;

    public ClsRecExamP4() {
    }

    public ClsRecExamP4(String id_exam, String url_audio, int id_question) {
        this.id_exam = id_exam;
        this.url_audio = url_audio;
        this.id_question = id_question;
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

    public int getId_question() {
        return id_question;
    }

    public void setId_question(int id_question) {
        this.id_question = id_question;
    }
}

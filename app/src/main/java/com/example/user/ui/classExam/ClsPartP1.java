package com.example.user.ui.classExam;

public class ClsPartP1 {
    String result,url_img,id_ques;

    public ClsPartP1() {
    }

    public ClsPartP1(String result, String url_img, String id_ques) {
        this.result = result;
        this.url_img = url_img;
        this.id_ques = id_ques;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getUrl_img() {
        return url_img;
    }

    public void setUrl_img(String url_img) {
        this.url_img = url_img;
    }

    public String getId_ques() {
        return id_ques;
    }

    public void setId_ques(String id_ques) {
        this.id_ques = id_ques;
    }
}

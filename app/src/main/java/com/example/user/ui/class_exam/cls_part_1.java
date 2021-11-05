package com.example.user.ui.class_exam;
import com.google.firebase.database.IgnoreExtraProperties;

public class cls_part_1 {
    String result,url_img;

    public cls_part_1() {
    }

    public cls_part_1(String result, String url_img) {
        this.result = result;
        this.url_img = url_img;
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
}

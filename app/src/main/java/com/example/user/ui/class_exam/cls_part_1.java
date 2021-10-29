package com.example.user.ui.class_exam;
import com.google.firebase.database.IgnoreExtraProperties;

public class cls_part_1 {
    String id_ques_1;
    String result;
    String url_IMG;
    String url_Audio;
    String note;

    public cls_part_1(String id_ques_1, String result, String url_IMG, String url_Audio, String note) {
        this.id_ques_1 = id_ques_1;
        this.result = result;
        this.url_IMG = url_IMG;
        this.url_Audio = url_Audio;
        this.note = note;
    }

    public String getId_ques_1() {
        return id_ques_1;
    }

    public void setId_ques_1(String id_ques_1) {
        this.id_ques_1 = id_ques_1;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getUrl_IMG() {
        return url_IMG;
    }

    public void setUrl_IMG(String url_IMG) {
        this.url_IMG = url_IMG;
    }

    public String getUrl_Audio() {
        return url_Audio;
    }

    public void setUrl_Audio(String url_Audio) {
        this.url_Audio = url_Audio;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "cls_part_1{" +
                "id_ques_1='" + id_ques_1 + '\'' +
                ", result='" + result + '\'' +
                ", url_IMG='" + url_IMG + '\'' +
                ", url_Audio='" + url_Audio + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}

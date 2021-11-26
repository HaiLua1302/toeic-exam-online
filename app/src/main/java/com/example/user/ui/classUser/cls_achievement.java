package com.example.user.ui.classUser;

public class cls_achievement {
    float score;
    String time;

    public cls_achievement() {
    }

    public cls_achievement(float score, String time) {
        this.score = score;
        this.time = time;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

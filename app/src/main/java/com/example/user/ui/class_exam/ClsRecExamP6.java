package com.example.user.ui.class_exam;

public class ClsRecExamP6 {
    String id_exam,paragraph;
    int id_question;

    public ClsRecExamP6() {
    }

    public ClsRecExamP6(String id_exam, String paragraph, int id_question) {
        this.id_exam = id_exam;
        this.paragraph = paragraph;
        this.id_question = id_question;
    }

    public String getId_exam() {
        return id_exam;
    }

    public void setId_exam(String id_exam) {
        this.id_exam = id_exam;
    }

    public String getParagraph() {
        return paragraph;
    }

    public void setParagraph(String paragraph) {
        this.paragraph = paragraph;
    }

    public int getId_question() {
        return id_question;
    }

    public void setId_question(int id_question) {
        this.id_question = id_question;
    }
}

package com.example.user.ui.classExam;

public class ClsRecExamP6 {
    String id_exam,id_question,paragraph;

    public ClsRecExamP6() {
    }

    public ClsRecExamP6(String id_exam, String id_question, String paragraph) {
        this.id_exam = id_exam;
        this.id_question = id_question;
        this.paragraph = paragraph;
    }

    public String getId_exam() {
        return id_exam;
    }

    public void setId_exam(String id_exam) {
        this.id_exam = id_exam;
    }

    public String getId_question() {
        return id_question;
    }

    public void setId_question(String id_question) {
        this.id_question = id_question;
    }

    public String getParagraph() {
        return paragraph;
    }

    public void setParagraph(String paragraph) {
        this.paragraph = paragraph;
    }
}

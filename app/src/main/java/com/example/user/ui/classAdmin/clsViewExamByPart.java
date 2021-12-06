package com.example.user.ui.classAdmin;

public class clsViewExamByPart {
    String nameExam;
    int totalQues;

    public clsViewExamByPart() {
    }

    public clsViewExamByPart(String nameExam, int totalQues) {
        this.nameExam = nameExam;
        this.totalQues = totalQues;
    }

    public String getNameExam() {
        return nameExam;
    }

    public void setNameExam(String nameExam) {
        this.nameExam = nameExam;
    }

    public int getTotalQues() {
        return totalQues;
    }

    public void setTotalQues(int totalQues) {
        this.totalQues = totalQues;
    }
}

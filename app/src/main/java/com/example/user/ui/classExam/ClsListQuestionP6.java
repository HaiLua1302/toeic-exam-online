package com.example.user.ui.classExam;

public class ClsListQuestionP6 {
    String id_ques,ques_content,ans_a,ans_b,ans_c,ans_d,result;

    public ClsListQuestionP6() {
    }

    public ClsListQuestionP6(String id_ques, String ques_content, String ans_a, String ans_b, String ans_c, String ans_d, String result) {
        this.id_ques = id_ques;
        this.ques_content = ques_content;
        this.ans_a = ans_a;
        this.ans_b = ans_b;
        this.ans_c = ans_c;
        this.ans_d = ans_d;
        this.result = result;
    }

    public String getId_ques() {
        return id_ques;
    }

    public void setId_ques(String id_ques) {
        this.id_ques = id_ques;
    }

    public String getQues_content() {
        return ques_content;
    }

    public void setQues_content(String ques_content) {
        this.ques_content = ques_content;
    }

    public String getAns_a() {
        return ans_a;
    }

    public void setAns_a(String ans_a) {
        this.ans_a = ans_a;
    }

    public String getAns_b() {
        return ans_b;
    }

    public void setAns_b(String ans_b) {
        this.ans_b = ans_b;
    }

    public String getAns_c() {
        return ans_c;
    }

    public void setAns_c(String ans_c) {
        this.ans_c = ans_c;
    }

    public String getAns_d() {
        return ans_d;
    }

    public void setAns_d(String ans_d) {
        this.ans_d = ans_d;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}

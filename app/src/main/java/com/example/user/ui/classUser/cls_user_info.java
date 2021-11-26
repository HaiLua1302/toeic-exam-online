package com.example.user.ui.classUser;

public class cls_user_info {

    private String id_user;
    private String name_user;
    private int dob_user;
    private int numberP_user;
    private String pass_user;
    private String mail_user;
    private String avata_user;


    public cls_user_info() {
        super();
    }

    public cls_user_info(String id_user, String name_user, int dob_user, int numberP_user, String pass_user, String mail_user, String avata_user) {
        this.id_user = id_user;
        this.name_user = name_user;
        this.dob_user = dob_user;
        this.numberP_user = numberP_user;
        this.pass_user = pass_user;
        this.mail_user = mail_user;
        this.avata_user = avata_user;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getName_user() {
        return name_user;
    }

    public void setName_user(String name_user) {
        this.name_user = name_user;
    }

    public int getDob_user() {
        return dob_user;
    }

    public void setDob_user(int dob_user) {
        this.dob_user = dob_user;
    }

    public int getNumberP_user() {
        return numberP_user;
    }

    public void setNumberP_user(int numberP_user) {
        this.numberP_user = numberP_user;
    }

    public String getPass_user() {
        return pass_user;
    }

    public void setPass_user(String pass_user) {
        this.pass_user = pass_user;
    }

    public String getMail_user() {
        return mail_user;
    }

    public void setMail_user(String mail_user) {
        this.mail_user = mail_user;
    }

    public String getAvata_user() {
        return avata_user;
    }

    public void setAvata_user(String avata_user) {
        this.avata_user = avata_user;
    }

    @Override
    public String toString() {
        return "cls_user_info{" +
                "id_user=" + id_user +
                ", name_user='" + name_user + '\'' +
                ", dob_user=" + dob_user +
                ", numberP_user=" + numberP_user +
                ", pass_user='" + pass_user + '\'' +
                ", mail_user='" + mail_user + '\'' +
                ", avata_user='" + avata_user + '\'' +
                '}';
    }
}

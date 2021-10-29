package com.example.user.ui.class_user;

public class cls_user_info {

    public String id_user;
    public String name_user;
    public String dob_user;
    public String numberP_user;
    public String pass_user;
    public String mail_user;
    public String avata_user;


    public cls_user_info() {
        super();
    }

    public cls_user_info(String id_user, String name_user, String dob_user, String numberP_user, String pass_user, String mail_user, String avata_user) {
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

    public String getDob_user() {
        return dob_user;
    }

    public void setDob_user(String dob_user) {
        this.dob_user = dob_user;
    }

    public String getNumberP_user() {
        return numberP_user;
    }

    public void setNumberP_user(String numberP_user) {
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

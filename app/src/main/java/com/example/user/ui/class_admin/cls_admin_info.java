package com.example.user.ui.class_admin;

public class cls_admin_info {
    public String id_admin;
    public String name_admin;
    public String mail_admin;
    public String pass_admin;


    public cls_admin_info(String id_admin, String name_admin, String mail_admin, String pass_admin) {
        this.id_admin = id_admin;
        this.name_admin = name_admin;
        this.mail_admin = mail_admin;
        this.pass_admin = pass_admin;
    }

    public cls_admin_info() {
    }

    public String getId_admin() {
        return id_admin;
    }

    public void setId_admin(String id_admin) {
        this.id_admin = id_admin;
    }

    public String getName_admin() {
        return name_admin;
    }

    public void setName_admin(String name_admin) {
        this.name_admin = name_admin;
    }

    public String getPass_admin() {
        return pass_admin;
    }

    public void setPass_admin(String pass_admin) {
        this.pass_admin = pass_admin;
    }

    public String getMail_admin() {
        return mail_admin;
    }

    public void setMail_admin(String mail_admin) {
        this.mail_admin = mail_admin;
    }

    @Override
    public String toString() {
        return "cls_admin_info{" +
                "id_admin='" + id_admin + '\'' +
                ", name_admin='" + name_admin + '\'' +
                ", mail_admin='" + mail_admin + '\'' +
                ", pass_admin='" + pass_admin + '\'' +
                '}';
    }
}

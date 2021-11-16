package com.example.user.models;

public class DeThi {
    private  String id;
    private  String ngayThem;
    private  String boDe;

    public DeThi() {
    }

    public DeThi(String id, String ngayThem, String boDe) {
        this.id = id;
        this.ngayThem = ngayThem;
        this.boDe = boDe;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNgayThem() {
        return ngayThem;
    }

    public void setNgayThem(String ngayThem) {
        this.ngayThem = ngayThem;
    }

    public String getBoDe() {
        return boDe;
    }

    public void setBoDe(String boDe) {
        this.boDe = boDe;
    }
}

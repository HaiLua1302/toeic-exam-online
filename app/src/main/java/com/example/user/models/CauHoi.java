package com.example.user.models;

public class CauHoi {
    private String id;
    private String noiDungCauHoi;
    private String dapAn;

    public CauHoi() {
    }

    public CauHoi(String id, String noiDungCauHoi, String dapAn) {
        this.id = id;
        this.noiDungCauHoi = noiDungCauHoi;
        this.dapAn = dapAn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNoiDungCauHoi() {
        return noiDungCauHoi;
    }

    public void setNoiDungCauHoi(String noiDungCauHoi) {
        this.noiDungCauHoi = noiDungCauHoi;
    }

    public String getDapAn() {
        return dapAn;
    }

    public void setDapAn(String dapAn) {
        this.dapAn = dapAn;
    }
}

package com.example.user.models;

public class CauHoiModel {
    private String phan;
    private String cacCauHoi;
    private String id;

    public CauHoiModel() {
    }

    public CauHoiModel(String phan, String cacCauHoi, String id) {
        this.phan = phan;
        this.cacCauHoi = cacCauHoi;
        this.id = id;
    }

    public String getPhan() {
        return phan;
    }

    public void setPhan(String phan) {
        this.phan = phan;
    }

    public String getCacCauHoi() {
        return cacCauHoi;
    }

    public void setCacCauHoi(String cacCauHoi) {
        this.cacCauHoi = cacCauHoi;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

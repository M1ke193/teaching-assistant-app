package com.example.teaching_assistant.Model_DB;

import com.google.type.DateTime;

public class TinNhanModel {
    public String tenNguoiChat;
    public String noiDung;
    public String ngayGui;
    public boolean role;
    public String maNguoiChat;

    public TinNhanModel(String tenNguoiChat, String noiDung, String ngayGui, boolean role, String maNguoiChat) {
        this.tenNguoiChat = tenNguoiChat;
        this.noiDung = noiDung;
        this.ngayGui = ngayGui;
        this.role = role;
        this.maNguoiChat = maNguoiChat;
    }


    @Override
    public String toString() {
        return "TinNhanModel{" +
                "tenNguoiChat='" + tenNguoiChat + '\'' +
                ", noiDung='" + noiDung + '\'' +
                ", ngayGui='" + ngayGui + '\'' +
                ", role=" + role +
                ", maNguoiChat='" + maNguoiChat + '\'' +
                '}';
    }

    public String getTenNguoiChat() {
        return tenNguoiChat;
    }

    public void setTenNguoiChat(String tenNguoiChat) {
        this.tenNguoiChat = tenNguoiChat;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getNgayGui() {
        return ngayGui;
    }

    public void setNgayGui(String ngayGui) {
        this.ngayGui = ngayGui;
    }

    public boolean isRole() {
        return role;
    }

    public void setRole(boolean role) {
        this.role = role;
    }

    public String getMaNguoiChat() {
        return maNguoiChat;
    }

    public void setMaNguoiChat(String maNguoiChat) {
        this.maNguoiChat = maNguoiChat;
    }

    public TinNhanModel() {};


}

package com.example.teaching_assistant.Model_DB;

public class SinhVienModel {
    public String ten;
    public String email;

    public SinhVienModel() {};

    public SinhVienModel(String name, String email) {
        this.ten = name;
        this.email = email;
    }


    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}

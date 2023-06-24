package com.example.teaching_assistant.GiaoVien_Activity.GiaoVienQuanLySinhVien;

import java.util.ArrayList;

public class Data_for_people_gv {
    String name;
    String email;
    ArrayList<Boolean> diemdanh;

    public Data_for_people_gv(String name, String email, ArrayList<Boolean> diemdanh) {
        this.name = name;
        this.email = email;
        this.diemdanh = diemdanh;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Boolean> getDiemdanh() {
        return diemdanh;
    }

    public void setDiemdanh(ArrayList<Boolean> diemdanh) {
        this.diemdanh = diemdanh;
    }
}

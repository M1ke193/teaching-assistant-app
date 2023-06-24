package com.example.teaching_assistant.Model_DB;

import com.example.teaching_assistant.Model_DB.TinNhanModel;

import java.util.ArrayList;
import java.util.List;

public class LopModel {
    public String maLop;
    public String ten;
    public String giaoVien;
    public int tongSo;
    public int tienDo;
    public ArrayList<Boolean> diemDanh;
    public ArrayList<String> sinhVien;
    public TinNhanModel tinNhanModel;
    public String emailGiaoVien;

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public int getTienDo() {
        return tienDo;
    }

    public void setTienDo(int tienDo) {
        this.tienDo = tienDo;
    }

    public TinNhanModel getTinNhanModel() {
        return tinNhanModel;
    }

    public void setTinNhanModel(TinNhanModel tinNhanModel) {
        this.tinNhanModel = tinNhanModel;
    }

    public String getEmailGiaoVien() {
        return emailGiaoVien;
    }

    public void setEmailGiaoVien(String emailGiaoVien) {
        this.emailGiaoVien = emailGiaoVien;
    }

    public ArrayList<String> getSinhVien() {
        return sinhVien;
    }

    public void setSinhVien(ArrayList<String> sinhVien) {
        this.sinhVien = sinhVien;
    }

    public LopModel(String ten, String giaoVien, int tongSo) {
        this.ten = ten;
        this.giaoVien = giaoVien;
        this.tongSo = tongSo;
    }

    @Override
    public String toString() {
        return "Lop{" +
                "ten='" + ten + '\'' +
                ", giaoVien='" + giaoVien + '\'' +
                ", tongSo=" + tongSo +
                ", diemDanh=" + diemDanh +
                '}';
    }

    public LopModel() {
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getGiaoVien() {
        return giaoVien;
    }

    public void setGiaoVien(String giaoVien) {
        this.giaoVien = giaoVien;
    }

    public int getTongSo() {
        return tongSo;
    }

    public void setTongSo(int tongSo) {
        this.tongSo = tongSo;
    }

    public List<Boolean> getDiemDanh() {
        return diemDanh;
    }

    public void setDiemDanh(ArrayList<Boolean> diemDanh) {
        this.diemDanh = diemDanh;
    }

    public LopModel(String ten, String giaoVien, int tongSo, ArrayList<Boolean> diemDanh, ArrayList<String> sinhVien) {
        this.ten = ten;
        this.giaoVien = giaoVien;
        this.tongSo = tongSo;
        this.diemDanh = diemDanh;
        this.sinhVien = sinhVien;
    }
}

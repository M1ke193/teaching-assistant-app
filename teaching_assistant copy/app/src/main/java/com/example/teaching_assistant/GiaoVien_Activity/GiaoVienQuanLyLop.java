package com.example.teaching_assistant.GiaoVien_Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.teaching_assistant.GiaoVien_Activity.DiemDanh.Atendance_gv_fragment;
import com.example.teaching_assistant.GiaoVien_Activity.DiemDanh.GiaoVienDiemDanh;
import com.example.teaching_assistant.GiaoVien_Activity.GiaoVienQuanLySinhVien.fragment_people_gv;
import com.example.teaching_assistant.GiaoVien_Activity.NhanTin.GiaoVienNhanTin;
import com.example.teaching_assistant.R;
import com.example.teaching_assistant.SinhVien_Activity.DiemDanh.SinhVienDiemDanh;
import com.example.teaching_assistant.SinhVien_Activity.NhanTin.SinhVienNhanTin;
import com.example.teaching_assistant.SinhVien_Activity.XemBan.SinhVienXemBan;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class GiaoVienQuanLyLop extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_in_class_st);
        BottomNavigationView bottom = findViewById(R.id.bottom_inclass);
        bottom.setOnNavigationItemSelectedListener(nav);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer, new Atendance_gv_fragment()).commit();
    }
    private BottomNavigationView.OnNavigationItemSelectedListener nav =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selected = null;
                    {
                        if (item.getItemId() == R.id.atendance) {
                            selected = new Atendance_gv_fragment();
                        } else if (item.getItemId() == R.id.mutiluser) {
                            selected = new fragment_people_gv();
                        }
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainergv,
                                selected).commit();
                        return true;
                    }
                }
            };
}
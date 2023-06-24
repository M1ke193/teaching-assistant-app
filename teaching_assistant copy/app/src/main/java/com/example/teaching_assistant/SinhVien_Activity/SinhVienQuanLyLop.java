package com.example.teaching_assistant.SinhVien_Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.teaching_assistant.R;
import com.example.teaching_assistant.SinhVien_Activity.DiemDanh.SinhVienDiemDanh;
import com.example.teaching_assistant.SinhVien_Activity.NhanTin.SinhVienNhanTin;
import com.example.teaching_assistant.SinhVien_Activity.XemBan.SinhVienXemBan;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

public class SinhVienQuanLyLop extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_in_class_st);
        BottomNavigationView bottom = findViewById(R.id.bottom_inclass);
        bottom.setOnNavigationItemSelectedListener(nav);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer, new SinhVienDiemDanh()).commit();
    }
    private BottomNavigationView.OnNavigationItemSelectedListener nav =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selected = null;
                    if (item.getItemId() ==R.id.chat) {
                        selected = new SinhVienNhanTin();
                    } else if (item.getItemId() == R.id.mutiluser) {
                        selected = new SinhVienXemBan();
                    } else if (item.getItemId() == R.id.privatechat) {
                        selected = new SinhVienDiemDanh();
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer,
                            selected).commit();
                    return true;
                }
            };
}

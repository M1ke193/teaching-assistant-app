package com.example.teaching_assistant.GiaoVien_Activity.DiemDanh;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.teaching_assistant.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
public class Fragment_cho_cai_activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_in_class_gv);
        BottomNavigationView bottom = findViewById(R.id.bottom_inclassgv);
        bottom.setOnNavigationItemSelectedListener(nav);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainergv, new Atendance_gv_fragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener nav =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selected = null;
                    {
                        if (item.getItemId() == R.id.atendance) {
                            selected = new Atendance_gv_fragment();
                        }
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainergv,
                                selected).commit();
                        return true;
                    }
                }

                ;
            };
}

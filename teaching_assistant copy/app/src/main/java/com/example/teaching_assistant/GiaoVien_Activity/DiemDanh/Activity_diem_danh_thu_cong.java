package com.example.teaching_assistant.GiaoVien_Activity.DiemDanh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.teaching_assistant.R;

import java.util.ArrayList;

public class Activity_diem_danh_thu_cong extends AppCompatActivity {
    ArrayList<Data_for_diem_danh_tc> data;
    ArrayList<Boolean> checkboolean;
    RecyclerView list;
    Adapter_diem_danh_tc adapter;
    Button submit;
    TextView buoi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_diem_danh_thu_cong);
        data = new ArrayList<Data_for_diem_danh_tc>();
        checkboolean = new ArrayList<Boolean>();
        RecyclerView list;
        Adapter_diem_danh_tc adapter;
        list = findViewById(R.id.listdiemdanhtc);
        submit = findViewById(R.id.bt_submit);
        buoi = findViewById(R.id.textbuoi);
        data.add(new Data_for_diem_danh_tc("Dinh Nhut Minh","mndx1t@gmail.com"));
        data.add(new Data_for_diem_danh_tc("test1","mndx1t@gmail.com"));
        data.add(new Data_for_diem_danh_tc("test2","mndx1t@gmail.com"));
        data.add(new Data_for_diem_danh_tc("test3","mndx1t@gmail.com"));
        data.add(new Data_for_diem_danh_tc("test4","mndx1t@gmail.com"));
        data.add(new Data_for_diem_danh_tc("test5","mndx1t@gmail.com"));
        data.add(new Data_for_diem_danh_tc("test6","mndx1t@gmail.com"));
        data.add(new Data_for_diem_danh_tc("test6","mndx1t@gmail.com"));
        data.add(new Data_for_diem_danh_tc("test6","mndx1t@gmail.com"));
        data.add(new Data_for_diem_danh_tc("test6","mndx1t@gmail.com"));
        data.add(new Data_for_diem_danh_tc("test6","mndx1t@gmail.com"));
        data.add(new Data_for_diem_danh_tc("test6","mndx1t@gmail.com"));
        for(int i = 0;i<data.size();i++){
            checkboolean.add(null);
        }
        list.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        list.setLayoutManager(linearLayoutManager);
        adapter = new Adapter_diem_danh_tc(data,checkboolean);
        list.setAdapter(adapter);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Boolean> checkbooblean;
                checkbooblean = adapter.getBoolean();
                Boolean flag =true;
               for (Boolean i:checkbooblean)
               {
                    if(i == null){
                        Toast.makeText(Activity_diem_danh_thu_cong.this, "Please Select All....", Toast.LENGTH_SHORT).show();
                        flag = false;
                         break;
                    }
               }
               if(flag)
               {
                   // add du lieu here bro, du lieu nam` trong checkbooblean
                   finish();
               }
            }
        });
    }
}
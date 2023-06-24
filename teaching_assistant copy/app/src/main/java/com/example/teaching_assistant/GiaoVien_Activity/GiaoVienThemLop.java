package com.example.teaching_assistant.GiaoVien_Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.teaching_assistant.Model_DB.LopModel;
import com.example.teaching_assistant.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GiaoVienThemLop extends AppCompatActivity {
    EditText tl_maLop, tl_tenLop, tl_soBuoi;
    Button tl;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_lop);

        tl_maLop = findViewById(R.id.tl_maLop);
        tl_tenLop = findViewById(R.id.tl_tenLop);
        tl_soBuoi = findViewById(R.id.tl_sobuoi);
        tl = findViewById(R.id.tl);

        tl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DocumentReference giaoVien = db.collection("GiaoVien").document(user.getUid());
                giaoVien.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        giaoVien.update("maLop", FieldValue.arrayUnion(tl_maLop.getText().toString()));
                        DocumentReference lopMoiRef = db.collection("Lop").document(tl_maLop.getText().toString());
                        Map<String, Object> data = new HashMap<>();
                        data.put("emailGiaoVien", task.getResult().getString("email"));
                        data.put("giaoVien", task.getResult().getString("ten"));
                        data.put("sinhVien", new ArrayList<String>());
                        data.put("ten", tl_tenLop.getText().toString());
                        data.put("tongSo", Integer.parseInt(tl_soBuoi.getText().toString()));
                        data.put("tienDo", 0);
                        data.put("maDiemDanh", "Stop");
                        lopMoiRef.set(data);
                        finish();
                    }
                });
            }
        });
    }
}
package com.example.teaching_assistant.SinhVien_Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

public class SinhVienThamGiaLop extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    EditText maLop;
    Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tham_gia_lop);
        maLop = findViewById(R.id.malop);
        bt = findViewById(R.id.thamgia);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DocumentReference lop = db.collection("Lop").document(maLop.getText().toString());
                lop.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            LopModel lopModel = task.getResult().toObject(LopModel.class);
                            lopModel.diemDanh = new ArrayList<Boolean>();
                            db.collection("Sinhvien")
                                    .document(user.getUid())
                                    .collection("Lop").document(maLop.getText().toString()).set(lopModel);
                            ;
                            lop.update("sinhVien", FieldValue.arrayUnion(user.getUid()));
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Mã lớp không hợp lệ", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}
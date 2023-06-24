package com.example.teaching_assistant.GiaoVien_Activity.DiemDanh;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.teaching_assistant.Model_DB.TinNhanModel;
import com.example.teaching_assistant.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.zip.Inflater;

public class GiaoVienDiemDanh extends Fragment {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    EditText maDiemDanh;
    Button diemDanh, xuatFile;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_giao_vien_diem_danh, container, false);
        maDiemDanh = view.findViewById(R.id.maDiemDanh);
        diemDanh = view.findViewById(R.id.diemDanh);
        xuatFile = view.findViewById(R.id.xuatFile);

        String maLop = getActivity().getIntent().getStringExtra("maLop");
        DocumentReference lop = db.collection("Lop").document(maLop);

        diemDanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lop.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.getResult().getString("maDiemDanh").equals("Stop")) {
                            lop.update("maDiemDanh", maDiemDanh.getText().toString());
                            diemDanh.setText("Dừng điểm danh");
                        } else {
                            lop.update("maDiemDanh", "Stop");
                            lop.update("tienDo", FieldValue.increment(1));
                            lop.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    ArrayList<String> danhSachSV = (ArrayList<String>) task.getResult().get("sinhVien");
                                    int tienDo = Integer.parseInt(task.getResult().get("tienDo").toString());
                                    if (tienDo <= Integer.parseInt(task.getResult().getString("tongSo"))) {
                                        for (String sinhvien : danhSachSV) {
                                            DocumentReference lop_SV = db.collection("Sinhvien").document(sinhvien).collection("Lop").document(maLop);
                                            lop_SV.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                @Override
                                                public void onComplete(@NonNull Task<DocumentSnapshot> task1) {
                                                    ArrayList<Boolean> diemDanh = (ArrayList<Boolean>) task1.getResult().get("diemDanh");
                                                    if (diemDanh.size() < tienDo) {
                                                        diemDanh.add(false);
                                                        lop_SV.update("diemDanh", diemDanh);
                                                    }
                                                }
                                            });
                                        }
                                    }
                                }
                            });
                            diemDanh.setText("Điểm danh");
                        }
                    }
                });
            }
        });
        return view;
    }
}
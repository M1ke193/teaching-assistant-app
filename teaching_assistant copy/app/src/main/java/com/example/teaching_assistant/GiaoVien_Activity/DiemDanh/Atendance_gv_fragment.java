package com.example.teaching_assistant.GiaoVien_Activity.DiemDanh;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teaching_assistant.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class Atendance_gv_fragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        View view = inflater.inflate(R.layout.fragment_atendance_gv, container, false);
        Button Bt_Diem_Danh_thu_cong;
        Button diemDanh;
        TextView maDiemDanh;
        maDiemDanh = view.findViewById(R.id.mhapma);
        Bt_Diem_Danh_thu_cong = view.findViewById(R.id.bt_diem_danh_thu_cong);
        diemDanh = view.findViewById(R.id.btdiemdanh);
        Bt_Diem_Danh_thu_cong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), Activity_diem_danh_thu_cong.class);
                startActivity(intent);
            }
        });

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
                                    if (tienDo <= Integer.parseInt(task.getResult().get("tongSo").toString())) {
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



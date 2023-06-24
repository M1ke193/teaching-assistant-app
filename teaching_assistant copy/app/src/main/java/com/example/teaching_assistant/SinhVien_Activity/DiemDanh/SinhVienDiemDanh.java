package com.example.teaching_assistant.SinhVien_Activity.DiemDanh;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teaching_assistant.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.util.BackgroundQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class SinhVienDiemDanh extends Fragment {
    RecyclerView list;
    TextView maDD;
    Button nutDD;
    SinhVienDiemDanhAdapter adapter;
    ArrayList<Boolean> data = new ArrayList<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_atendance,container,false);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String maLop = getActivity().getIntent().getStringExtra("maLop");
        CollapsingToolbarLayout collapsingToolbarLayout = view.findViewById(R.id.text_for_collap);
        db.collection("Lop").document(maLop).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                collapsingToolbarLayout.setTitle(task.getResult().getString("ten"));
            }
        });
        maDD = view.findViewById(R.id.mhapma);
        nutDD = view.findViewById(R.id.nutXacNhan);

        nutDD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ma = maDD.getText().toString();
                DocumentReference lop = db.collection("Lop").document(maLop);
                DocumentReference lop_sv = db.collection("Sinhvien").document(user.getUid()).collection("Lop").document(maLop);
                lop.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (ma.equals(task.getResult().getString("maDiemDanh")) && !ma.equals("Stop")) {
                            int tienDo = Integer.parseInt(task.getResult().get("tienDo").toString());
                            lop_sv.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task1) {
                                    ArrayList<Boolean> diemDanh = (ArrayList<Boolean>) task1.getResult().get("diemDanh");
                                    if (diemDanh.size() > tienDo) {
                                        Toast.makeText(getContext(), "Đã điểm danh", Toast.LENGTH_LONG).show();
                                    } else {
                                        diemDanh.add(true);
                                    }
                                    lop_sv.update("diemDanh", diemDanh);
                                }
                            });
                        }
                    }
                });
            }
        });

        list = view.findViewById(R.id.listatendance);
        list.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        list.setLayoutManager(linearLayoutManager);
        db.collection("Sinhvien").document(user.getUid()).collection("Lop").document(maLop).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                data = (ArrayList<Boolean>) task.getResult().get("diemDanh");
                adapter = new SinhVienDiemDanhAdapter(data);
                list.setAdapter(adapter);
            }
        });
        db.collection("Sinhvien").document(user.getUid()).collection("Lop").document(maLop).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                data = (ArrayList<Boolean>) value.get("diemDanh");
                adapter = new SinhVienDiemDanhAdapter(data);
                list.setAdapter(adapter);
            }
        });
        return view;
    }
}

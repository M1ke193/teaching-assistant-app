package com.example.teaching_assistant.SinhVien_Activity.XemBan;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teaching_assistant.Model_DB.SinhVienModel;
import com.example.teaching_assistant.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class SinhVienXemBan extends Fragment {
    RecyclerView list;
    TextView teacherName, teacherEmail;
    SinhVienXemBanAdapter adapter;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ban_hoc_view, container, false);
        ArrayList<SinhVienModel> data = new ArrayList<>();


        teacherEmail = view.findViewById(R.id.teacheremail);
        teacherName = view.findViewById(R.id.teacherName);

        list = view.findViewById(R.id.listpeople);

        String maLop = getActivity().getIntent().getStringExtra("maLop");
        DocumentReference lop = db.collection("Lop").document(maLop);

        lop.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                teacherName.setText(task.getResult().getString("giaoVien"));
                teacherEmail.setText(task.getResult().getString("emailGiaoVien"));
                ArrayList<String> dssv = (ArrayList<String>) task.getResult().get("sinhVien");
                ArrayList<SinhVienModel> data = new ArrayList<>();
                db.collection("Sinhvien").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (QueryDocumentSnapshot sinhVien_db : task.getResult()) {
                            if (dssv.contains(sinhVien_db.getId())) {
                                SinhVienModel sinhVien = new SinhVienModel();
                                sinhVien.ten = sinhVien_db.getString("ten");
                                sinhVien.email = sinhVien_db.getString("email");
                                data.add(sinhVien);
                            }
                        }
                        adapter = new SinhVienXemBanAdapter(data);
                        list.setAdapter(adapter);
                    }
                });
            }
        });
        list.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        list.setLayoutManager(linearLayoutManager);

        return view;
    }
}

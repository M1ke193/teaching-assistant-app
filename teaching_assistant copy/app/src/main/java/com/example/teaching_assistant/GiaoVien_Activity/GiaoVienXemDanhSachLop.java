package com.example.teaching_assistant.GiaoVien_Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.teaching_assistant.Model_DB.LopModel;
import com.example.teaching_assistant.R;
import com.example.teaching_assistant.SinhVien_Activity.SinhVienXemDanhSachLopAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class GiaoVienXemDanhSachLop extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    TextView loichao;
    FloatingActionButton themLop;
    RecyclerView danhSachLop;
    GiaoVienXemDanhSachLopAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loichao = findViewById(R.id.loichao);
        db.collection("GiaoVien").document(user.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                String chaoSinhVien = "Chào " + task.getResult().get("ten").toString();
                loichao.setText(chaoSinhVien);
            }
        });
        themLop = findViewById(R.id.addclass);
        themLop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(v.getContext(), GiaoVienThemLop.class).putExtra("Ten giao vien", "ten"), 5000);
            }
        });

        danhSachLop = findViewById(R.id.listclass);
        danhSachLop.hasFixedSize();
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        danhSachLop.setLayoutManager(staggeredGridLayoutManager);

        display();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 5000) {
            display();
        }
    }

    private void display() {
        DocumentReference giaoVien = db.collection("GiaoVien").document(user.getUid());
        CollectionReference dulieuLop = db.collection("Lop");
        giaoVien.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                ArrayList<String> danhSachMaLop = (ArrayList<String>) task.getResult().get("maLop");
                dulieuLop.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        ArrayList<LopModel> data = new ArrayList<>();
                        for (QueryDocumentSnapshot lop : task.getResult()) {
                            if (danhSachMaLop.contains(lop.getId())) {
                                LopModel item_lop = lop.toObject(LopModel.class);
                                item_lop.maLop = lop.getId();
                                data.add(item_lop);
                            }
                        }
                        adapter = new GiaoVienXemDanhSachLopAdapter(data);
                        danhSachLop.setAdapter(adapter);
                    }
                });
            }
        });

    }
}
package com.example.teaching_assistant.SinhVien_Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.teaching_assistant.Model_DB.LopModel;
import com.example.teaching_assistant.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import org.w3c.dom.Document;

import java.util.ArrayList;

public class SinhVienXemDanhSachLop extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    TextView loichao;
    FloatingActionButton thamGiaLop;
    RecyclerView danhSachLop;
    SinhVienXemDanhSachLopAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loichao = findViewById(R.id.loichao);
        db.collection("Sinhvien").document(user.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                String chaoSinhVien = "Chào " + task.getResult().get("ten").toString();
                loichao.setText(chaoSinhVien);
            }
        });
        thamGiaLop = findViewById(R.id.addclass);
        danhSachLop = findViewById(R.id.listclass);
        danhSachLop.hasFixedSize();
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        danhSachLop.setLayoutManager(staggeredGridLayoutManager);

        thamGiaLop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(v.getContext(), SinhVienThamGiaLop.class), 3000);
            }

        });

        display();

        db.collection("Sinhvien").document(user.getUid()).collection("Lop").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                for (DocumentSnapshot doc : value.getDocuments()) {
                    ArrayList<Boolean> diemDanh = (ArrayList<Boolean>) doc.get("diemDanh");
                    int vang = 0;
                    for (Boolean caHoc : diemDanh) {
                        if (!caHoc) {
                            vang++;
                        }
                    }
                    int soBuoi = Integer.parseInt(doc.get("tongSo").toString());
                    if (vang != 0 && soBuoi/vang < 5) {
                        AlertDialog.Builder camThi = new AlertDialog.Builder(SinhVienXemDanhSachLop.this);
                        camThi.setTitle("Thông báo");
                        camThi.setMessage("Bạn đã bị cấm thi vì nghỉ quá số buổi môn " + doc.get("ten").toString());
                        AlertDialog alertDialog = camThi.create();
                        alertDialog.setCanceledOnTouchOutside(true);
                        alertDialog.show();
                    }
                    if (vang != 0 && soBuoi/vang == 5) {
                        AlertDialog.Builder sapcamThi = new AlertDialog.Builder(SinhVienXemDanhSachLop.this);
                        sapcamThi.setTitle("Thông báo");
                        sapcamThi.setMessage("Bạn sắp cấm thi vì nghỉ quá số buổi môn " + doc.get("ten").toString());
                        AlertDialog alertDialog = sapcamThi.create();
                        alertDialog.setCanceledOnTouchOutside(true);
                        alertDialog.show();
                    }
                    if (!diemDanh.isEmpty() && !diemDanh.get(diemDanh.size() - 1)) {
                        AlertDialog.Builder thongBaoVang = new AlertDialog.Builder(SinhVienXemDanhSachLop.this);
                        thongBaoVang.setTitle("Thông báo");
                        thongBaoVang.setMessage("Bạn đã vắng học tuần vừa rồi môn " + doc.get("ten").toString());
                        AlertDialog alertDialog = thongBaoVang.create();
                        alertDialog.setCanceledOnTouchOutside(true);
                        alertDialog.show();
                    }
                }
            }
        });






    }


    private void display() {
        db.collection("Sinhvien")
                .document(user.getUid())
                .collection("Lop").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                ArrayList<LopModel> danhsachlop = new ArrayList<>();
                for (QueryDocumentSnapshot lop : task.getResult()) {
                    LopModel l = lop.toObject(LopModel.class);
                    l.maLop = lop.getId();
                    danhsachlop.add(l);
                }
                adapter = new SinhVienXemDanhSachLopAdapter(danhsachlop);
                danhSachLop.setAdapter(adapter);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 3000) {
            display();
        }
    }
}
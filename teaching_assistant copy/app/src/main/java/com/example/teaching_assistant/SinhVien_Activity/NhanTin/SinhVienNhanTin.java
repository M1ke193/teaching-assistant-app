package com.example.teaching_assistant.SinhVien_Activity.NhanTin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teaching_assistant.R;
import com.example.teaching_assistant.Model_DB.TinNhanModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class SinhVienNhanTin extends Fragment {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    TextView textView;
    ImageView imageView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        String maLop = getActivity().getIntent().getStringExtra("maLop");
        View view = inflater.inflate(R.layout.fragment_chat_sinh_vien, container, false);
        ArrayList<TinNhanModel> data = new ArrayList<TinNhanModel>();
        textView = view.findViewById(R.id.textchat);
        imageView = view.findViewById(R.id.imageView10);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db.collection("Sinhvien").document(user.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        String tenNguoiChat = task.getResult().get("ten").toString();
                        String noiDung = textView.getText().toString();
                        boolean role = false;
                        String ngayGui = new SimpleDateFormat("yyyy.MM.dd 'at' HH:mm:ss", Locale.getDefault()).format(Calendar.getInstance().getTime());
                        String maNguoiChat = FirebaseAuth.getInstance().getCurrentUser().getUid();
                        TinNhanModel tinNhanMoi = new TinNhanModel(tenNguoiChat, noiDung, ngayGui, role, maNguoiChat);
                        textView.setText("");
                        db.collection("Lop").document(maLop).collection("TinNhan").add(tinNhanMoi);
                    }
                });
            }
        });
        RecyclerView list;
        list = view.findViewById(R.id.listchat);
        CollectionReference tb_TinNhan = db.collection("Lop").document(maLop).collection("TinNhan");
        tb_TinNhan.orderBy("ngayGui").limitToLast(20).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (QueryDocumentSnapshot tin : task.getResult()) {
                    TinNhanModel tinNhan = tin.toObject(TinNhanModel.class);
                    data.add(tinNhan);
                }
                SinhVienNhanTinAdapter adapter = new SinhVienNhanTinAdapter(data, getActivity().getApplicationContext(), maLop);
                list.setAdapter(adapter);
                list.smoothScrollToPosition(20);
            }
        });
        tb_TinNhan.orderBy("ngayGui").limitToLast(20).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                data.clear();
                for (DocumentSnapshot tin : value.getDocuments()) {
                    TinNhanModel tinNhan = tin.toObject(TinNhanModel.class);
                    data.add(tinNhan);
                }
                SinhVienNhanTinAdapter adapter = new SinhVienNhanTinAdapter(data, getActivity().getApplicationContext(), maLop);
                list.setAdapter(adapter);
                list.smoothScrollToPosition(20);
            }
        });
        list.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        list.setLayoutManager(linearLayoutManager);

        return view;
    }
}

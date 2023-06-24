package com.example.teaching_assistant.GiaoVien_Activity;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teaching_assistant.Model_DB.LopModel;
import com.example.teaching_assistant.R;

import java.util.ArrayList;

public class GiaoVienXemDanhSachLopAdapter extends RecyclerView.Adapter<GiaoVienXemDanhSachLopAdapter.viewholder>{
    private ArrayList<LopModel> data;
    public GiaoVienXemDanhSachLopAdapter(ArrayList<LopModel> data)
    {
        this.data = data;
    }
    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.giao_vien_xem_lop_item,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        LopModel dt = data.get(position);
        if(dt == null)
            return;
        holder.subject.setText(dt.getTen());
        holder.hocsinh.setText("Tổng số học sinh: "+ String.valueOf(dt.sinhVien.size()));
        holder.sobuoi.setText("Số buổi "+dt.tongSo);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), GiaoVienQuanLyLop.class);
                intent.putExtra("maLop", dt.maLop);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(data != null){
            return data.size();
        }
        return 0;
    }

    public class viewholder extends RecyclerView.ViewHolder{
        private TextView subject;
        private TextView hocsinh;
        private TextView sobuoi;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            subject = itemView.findViewById(R.id.subject);
            hocsinh = itemView.findViewById(R.id.hocsinh);
            sobuoi = itemView.findViewById(R.id.sobuoi);
        }
    }
}

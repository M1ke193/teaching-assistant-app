package com.example.teaching_assistant.SinhVien_Activity;

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

public class SinhVienXemDanhSachLopAdapter extends RecyclerView.Adapter<SinhVienXemDanhSachLopAdapter.viewholder>{
    private ArrayList<LopModel> data;

    public SinhVienXemDanhSachLopAdapter(ArrayList<LopModel> data)
    {
        this.data = data;
    }
    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_danh_sach_lop_sinhvien,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        LopModel dt = data.get(position);
        if(dt == null)
            return;
        holder.name.setText(dt.giaoVien);
        holder.subject.setText(dt.getTen());
        holder.tongso.setText("Tiến Độ: "+String.valueOf(counthoc(dt.diemDanh))+"/"+String.valueOf(dt.tongSo));
        holder.vang.setText("Số Buổi Vắng: "+String.valueOf(coutvang(dt.diemDanh)));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SinhVienQuanLyLop.class);
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
        private TextView name;
        private TextView tongso;
        private TextView vang;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            subject = itemView.findViewById(R.id.subject);
            name =  itemView.findViewById(R.id.name);
            tongso = itemView.findViewById(R.id.total);
            vang = itemView.findViewById(R.id.vang);

        }
    }
    public int counthoc( ArrayList<Boolean> data){
        int count = 0;
        for (Boolean i : data){
            if (i == true)
                count = count + 1;
        }
        return count;
    }
    public int coutvang( ArrayList<Boolean> data){
        int count = 0;
        for (Boolean i : data){
            if (i == false)
                count = count + 1;
        }
        return count;
    }
}
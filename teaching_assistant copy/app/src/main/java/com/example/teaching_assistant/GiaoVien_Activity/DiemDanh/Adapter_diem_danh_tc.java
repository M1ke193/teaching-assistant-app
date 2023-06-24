package com.example.teaching_assistant.GiaoVien_Activity.DiemDanh;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teaching_assistant.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Adapter_diem_danh_tc extends RecyclerView.Adapter<Adapter_diem_danh_tc.viewholder>{
    private ArrayList<Data_for_diem_danh_tc> data;
    private ArrayList<Boolean> checkbolean;
    public Adapter_diem_danh_tc(ArrayList<Data_for_diem_danh_tc> data,ArrayList<Boolean> check)
    {
        this.data = data;
        this.checkbolean = check;
    }
    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.apdapter_diem_danh_thu_cong,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        Data_for_diem_danh_tc dt = data.get(position);
        if(dt == null)
            return;
        holder.ten.setText(dt.getTen());
        holder.gmail.setText(dt.getGmail());
        holder.buttonP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkbolean.set(position,true);
            }
        });
        holder.buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkbolean.set(position,false);
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
        TextView ten;
        TextView gmail;
        RadioButton buttonP;
        RadioButton buttonA;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            ten = itemView.findViewById(R.id.tenhocsinh);
            gmail= itemView.findViewById(R.id.emailhocsinh);
            buttonP = itemView.findViewById(R.id.radiobt_P);
            buttonA = itemView.findViewById(R.id.radiobt_A);
        }
    }
    public ArrayList<Boolean> getBoolean(){
        return checkbolean;
    }
}

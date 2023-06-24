package com.example.teaching_assistant.GiaoVien_Activity.GiaoVienQuanLySinhVien;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teaching_assistant.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Adapter_for_people_gv extends RecyclerView.Adapter<Adapter_for_people_gv.viewholder>{
    private ArrayList<Data_for_people_gv> data;
    public Adapter_for_people_gv(ArrayList<Data_for_people_gv> data)
    {
        this.data = data;
    }
    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_people_gv,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        Data_for_people_gv dt = data.get(position);
        if(dt == null)
            return;
        holder.email.setText(dt.email);
        holder.name.setText(dt.name);
        holder.sobuoivang.setText(String.valueOf(coutvang(dt.diemdanh)));
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//               Intent intent = new Intent(v.getContext(),Activity_diemdanh_cardview_gv.class);
//               intent.putExtra("data",dt.diemdanh);
//                v.getContext().startActivity(intent);
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
        private TextView name;
        private TextView email;
        private TextView sobuoivang;
        private CardView cardview;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            name =  itemView.findViewById(R.id.studenttextgv);
            email = itemView.findViewById(R.id.emailstudent_gv);
            sobuoivang = itemView.findViewById(R.id.sobuoivang_gv);
            cardview = itemView.findViewById(R.id.cardview_people_gv);
        }
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

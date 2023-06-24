package com.example.teaching_assistant.SinhVien_Activity.DiemDanh;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teaching_assistant.R;

import java.util.ArrayList;

public class SinhVienDiemDanhAdapter extends RecyclerView.Adapter<SinhVienDiemDanhAdapter.viewholder>{
    private ArrayList<Boolean> data;
    public SinhVienDiemDanhAdapter(ArrayList<Boolean> data)
    {
        this.data = data;
    }
    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.addapter_atendace_student,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        Boolean dt = data.get(position);
        if(dt == null)
            return;
        holder.textday.setText(String.valueOf(position+1));
        if(dt)
            holder.text.setText("P");

        else {
            holder.color.setCardBackgroundColor(Color.RED);
            holder.text.setText("A");
        }
    }

    @Override
    public int getItemCount() {
        if(data != null){
            return data.size();
        }
        return 0;
    }

    public class viewholder extends RecyclerView.ViewHolder{
        CardView color;
        TextView text;
        TextView textday;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            color =  itemView.findViewById(R.id.cardviewcolor);
            text = itemView.findViewById(R.id.texttrangthai);
            textday =itemView.findViewById(R.id.textday);
        }
    }
}

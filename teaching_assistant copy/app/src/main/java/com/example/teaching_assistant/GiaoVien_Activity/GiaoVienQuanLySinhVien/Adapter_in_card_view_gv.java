package com.example.teaching_assistant.GiaoVien_Activity.GiaoVienQuanLySinhVien;

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

public class Adapter_in_card_view_gv extends RecyclerView.Adapter<Adapter_in_card_view_gv.viewholder>{
    private ArrayList<Boolean> data;
    public Adapter_in_card_view_gv(ArrayList<Boolean> data)
    {
        this.data = data;
    }
    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_people_cardview_gv,parent,false);
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
            color =  itemView.findViewById(R.id.cardviewcolor_cardview_gv);
            text = itemView.findViewById(R.id.texttrangthai_cardview_gv);
            textday =itemView.findViewById(R.id.textday_cardview_gv);

        }
    }
}

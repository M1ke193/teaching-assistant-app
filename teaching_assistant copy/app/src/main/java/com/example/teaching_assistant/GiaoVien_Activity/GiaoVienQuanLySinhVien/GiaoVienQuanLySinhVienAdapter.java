package com.example.teaching_assistant.GiaoVien_Activity.GiaoVienQuanLySinhVien;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teaching_assistant.Model_DB.SinhVienModel;
import com.example.teaching_assistant.R;

import java.util.ArrayList;

public class GiaoVienQuanLySinhVienAdapter extends RecyclerView.Adapter<GiaoVienQuanLySinhVienAdapter.viewholder>{
    private ArrayList<SinhVienModel> data;
    public GiaoVienQuanLySinhVienAdapter(ArrayList<SinhVienModel> data)
    {
        this.data = data;
    }
    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ban_hoc,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        SinhVienModel dt = data.get(position);
        if(dt == null)
            return;
        Log.d("test", "onBindViewHolder: " + dt.toString());
        holder.email.setText(dt.email);
        holder.name.setText(dt.ten);
    }

    @Override
    public int getItemCount() {
        if(data != null){
            return data.size();
        }
        return 0;
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView name;
        TextView email;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.studenttext);
            email = itemView.findViewById(R.id.emailstudent);
        }

        public TextView getName() {
            return name;
        }

        public TextView getEmail() {
            return email;
        }
    }
}

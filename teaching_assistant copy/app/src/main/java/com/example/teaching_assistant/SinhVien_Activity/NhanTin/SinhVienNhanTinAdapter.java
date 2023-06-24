package com.example.teaching_assistant.SinhVien_Activity.NhanTin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teaching_assistant.R;
import com.example.teaching_assistant.Model_DB.TinNhanModel;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class SinhVienNhanTinAdapter extends RecyclerView.Adapter<SinhVienNhanTinAdapter.viewholder>{
    private ArrayList<TinNhanModel> data;
    Context context;
    String maLop;
    public static final int THE_RECEIVER = 0;
    public static final int THE_SENTER = 1;
    public SinhVienNhanTinAdapter(ArrayList<TinNhanModel> data, Context context, String maLop)
    {
        this.data = data;
        this.context = context;
        this.maLop = maLop;
    }
    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == 1){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapterforchatmain,parent,false);
            return new viewholder(view);
        }
        else{
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapterforchat,parent,false);
            return new viewholder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        TinNhanModel dt = data.get(position);
        if(holder.getItemViewType() == 1)
        {
            holder.ngay.setText(dt.ngayGui);
            holder.hienthichat.setText(dt.noiDung);
            if(dt.role) {
                holder.hienthichat.setBackground(ContextCompat.getDrawable(context, R.drawable.drawforteacher));
            }
        }
        else
        {
            holder.ngay.setText(dt.ngayGui);
            holder.hienthichat.setText(dt.noiDung);
            holder.tennguoinhan.setText(dt.tenNguoiChat);
            if(dt.role) {
                holder.hienthichat.setBackground(ContextCompat.getDrawable(context, R.drawable.drawforteacher));
                holder.avatar.setImageResource(R.drawable.teacher);
                holder.key.setVisibility(View.VISIBLE);
            }
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
        private TextView hienthichat;
        private TextView tennguoinhan;
        private TextView ngay;
        private ImageView avatar;
        private ImageView key;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            hienthichat = itemView.findViewById(R.id.showchattext);
            tennguoinhan = itemView.findViewById(R.id.tennguoichat);
            ngay = itemView.findViewById(R.id.ngay);
            avatar = itemView.findViewById(R.id.avatar);
            key  = itemView.findViewById(R.id.key);
        }
    }

    @Override
    public int getItemViewType(int position) {
        TinNhanModel dt = data.get(position);
        if (dt.maNguoiChat.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
            return THE_SENTER;
        }
        return THE_RECEIVER;
    }
}

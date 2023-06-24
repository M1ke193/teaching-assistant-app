package com.example.teaching_assistant.GiaoVien_Activity.GiaoVienQuanLySinhVien;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teaching_assistant.R;

import java.util.ArrayList;

public class fragment_people_gv extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_people_gv, container, false);
        ArrayList<Data_for_people_gv> data = new ArrayList<>();
        ArrayList<Boolean> data2 = new ArrayList<>();
        data2.add(true);
        data2.add(true);
        data2.add(true);
        RecyclerView list;
        Adapter_for_people_gv adapter;
        list = view.findViewById(R.id.listpeople_gv);
        data = new ArrayList<>();
        data.add(new Data_for_people_gv("asdasdasd", "asdasdasd", data2));
        data.add(new Data_for_people_gv("asdas423dasd", "asda213sdasd", data2));
        data.add(new Data_for_people_gv("asdasdasdasd", "asdasdasd", data2));
        data.add(new Data_for_people_gv("asdasdasdasd", "asdasd234asd", data2));
        data.add(new Data_for_people_gv("asdafdsdasd", "asdas41dasd", data2));
        data.add(new Data_for_people_gv("asdasdasd", "asdasd23asd", data2));
        data.add(new Data_for_people_gv("asd43asdasd", "asdas123dasd", data2));
        data.add(new Data_for_people_gv("asd43asdasd", "asdas123dasd", data2));
        list.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        list.setLayoutManager(linearLayoutManager);
        adapter = new Adapter_for_people_gv(data);
        list.setAdapter(adapter);
        return view;
    }
}


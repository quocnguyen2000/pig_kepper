package com.example.pig_keeper.TabPagerFragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pig_keeper.Adapter.LoaiThuAdapter;
import com.example.pig_keeper.DAO.LoaiThuDAO;
import com.example.pig_keeper.Model.LoaiThu;
import com.example.pig_keeper.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class LoaiThuFragment extends Fragment {
    RecyclerView recyclerView_Loai_Thu;
    FloatingActionButton btnFab;
    Button btnThem;
    Button btnHuy;
    EditText edtTenLoaiThu;
    LoaiThu loaiThu;
    LoaiThuDAO loaiThuDAO;
    LoaiThuAdapter adapter;
    List<LoaiThu> listdata;
    RecyclerView.LayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_loai_thu, container, false);
        listdata = new ArrayList<>();
        //set du lieu len rycycler view
        recyclerView_Loai_Thu = view.findViewById(R.id.recyclerview_loai_thu);
        recyclerView_Loai_Thu.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView_Loai_Thu.setLayoutManager(layoutManager);
        btnFab = view.findViewById(R.id.fab); //nút float action button
        loaiThuDAO = new LoaiThuDAO(getContext());
        LoadDuLieuLoaiThu();
        btnFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowDialog();
            }
        });
        return view;
    }

    private void ShowDialog() {
        final Dialog add_loaithu_layout = new Dialog(getContext());
        add_loaithu_layout.setTitle("Thêm Loại Thu");
        add_loaithu_layout.setCancelable(false);
        add_loaithu_layout.setContentView(R.layout.item_loai_thu_dialog);
        add_loaithu_layout.getWindow().setBackgroundDrawableResource(R.color.white);
        btnThem = add_loaithu_layout.findViewById(R.id.btnThem);
        btnHuy = add_loaithu_layout.findViewById(R.id.btnHuy);
        edtTenLoaiThu = add_loaithu_layout.findViewById(R.id.edtTenLoaiThu);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ThemLoaiThu();
                add_loaithu_layout.cancel();
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_loaithu_layout.cancel();
            }
        });
        add_loaithu_layout.show();
    }

    private void ThemLoaiThu() {
        boolean error = true;
        if (edtTenLoaiThu.getText().toString().equals("") ||edtTenLoaiThu.getText().toString()==null){
            Toast.makeText(getContext(),"Không được để trống!!", Toast.LENGTH_SHORT).show();
            error = false;
        }else {
            for (  int i = 0; i<listdata.size();i++){
                if (listdata.get(i).getTenLoaiThu().equals(edtTenLoaiThu.getText().toString())){
                    Toast.makeText(getContext(),"Không được nhập trùng tên loại!!", Toast.LENGTH_SHORT).show();
                    error = false;
                }
            }
        }
        if (error==true){
            long check = loaiThuDAO.AddItem(new LoaiThu(
                    edtTenLoaiThu.getText().toString()
            ));
            if (check>0){
                Toast.makeText(getContext(),"Thêm thành công!!", Toast.LENGTH_SHORT).show();
                LoadDuLieuLoaiThu();
            }else {
                Toast.makeText(getContext(),"Không thành công!!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void LoadDuLieuLoaiThu() {
        listdata = loaiThuDAO.getLoaiThu();
        adapter = new LoaiThuAdapter(listdata, getContext());
        recyclerView_Loai_Thu.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}

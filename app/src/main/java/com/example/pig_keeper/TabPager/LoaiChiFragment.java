package com.example.pig_keeper.TabPager;

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

import com.example.pig_keeper.Adapter.LoaiChiAdapter;
import com.example.pig_keeper.DAO.LoaiChiDAO;
import com.example.pig_keeper.Model.LoaiChi;
import com.example.pig_keeper.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class LoaiChiFragment extends Fragment {

    RecyclerView recyclerView_Loai_Chi;
    FloatingActionButton btnFab_Loai_Chi;
    Button btnThem;
    Button btnHuy;
    EditText edtTenLoaiChi;
    LoaiChi loaiChi;
    LoaiChiDAO loaiChiDAO;
    LoaiChiAdapter adapter;
    List<LoaiChi> listdata;
    RecyclerView.LayoutManager layoutManager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_loai_chi, container, false);
        listdata = new ArrayList<>();
        //set du lieu len rycycler view
        recyclerView_Loai_Chi = view.findViewById(R.id.recyclerview_loai_chi);
        recyclerView_Loai_Chi.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView_Loai_Chi.setLayoutManager(layoutManager);
        btnFab_Loai_Chi = view.findViewById(R.id.fab_Loai_Chi); //nút float action button
        loaiChiDAO = new LoaiChiDAO(getContext());
        LoadDuLieuLoaiChi();
        btnFab_Loai_Chi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowDialog();
            }
        });
        return view;
    }

    private void ShowDialog() {
        final Dialog add_loaichi_layout = new Dialog(getContext());
        add_loaichi_layout.setTitle("Thêm Loại Chi");
        add_loaichi_layout.setCancelable(false);
        add_loaichi_layout.setContentView(R.layout.item_loai_chi_dialog);
        add_loaichi_layout.getWindow().setBackgroundDrawableResource(R.color.white);
        btnThem =  add_loaichi_layout.findViewById(R.id.btnThem);
        btnHuy =  add_loaichi_layout.findViewById(R.id.btnHuy);
        edtTenLoaiChi = add_loaichi_layout.findViewById(R.id.edtTenLoaiChi);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ThemLoaiChi();
                add_loaichi_layout.cancel();
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_loaichi_layout.cancel();
            }
        });
        add_loaichi_layout.show();
    }

    private void ThemLoaiChi() {
        loaiChi = new LoaiChi(edtTenLoaiChi.getText().toString());

        //Validate



        long check = loaiChiDAO.AddItem(loaiChi);
        if (check > 0) {
            LoadDuLieuLoaiChi();
            Toast.makeText(getContext(), "Thêm Thành Công!!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Thêm Thất Bại!!", Toast.LENGTH_SHORT).show();
        }
    }

    private void LoadDuLieuLoaiChi() {
        listdata = loaiChiDAO.getLoaiChi();
        adapter = new LoaiChiAdapter(listdata, getContext());
        recyclerView_Loai_Chi.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

}

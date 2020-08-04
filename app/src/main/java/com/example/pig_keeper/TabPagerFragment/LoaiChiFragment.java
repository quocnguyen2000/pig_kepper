package com.example.pig_keeper.TabPagerFragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ps10389_lequangminh_assigment.Adapter.LoaiChiAdapter;
import com.example.ps10389_lequangminh_assigment.DAO.LoaiChiDAO;
import com.example.ps10389_lequangminh_assigment.Model.LoaiChi;
import com.example.ps10389_lequangminh_assigment.R;

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
                ShowDialogThemLoai();
            }
        });
        return view;
    }

    private void ShowDialogThemLoai() {
        final Dialog add_loaichi_layout = new Dialog(getContext());
        add_loaichi_layout.setTitle("Thêm Loại Chi");
        add_loaichi_layout.setCancelable(false);
        add_loaichi_layout.setContentView(R.layout.item_loai_chi_dialog);
        add_loaichi_layout.getWindow().setBackgroundDrawableResource(R.color.white);
        btnThem = add_loaichi_layout.findViewById(R.id.btnThem);
        btnHuy = add_loaichi_layout.findViewById(R.id.btnHuy);
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
        //Validate
        boolean error = true;
        if (edtTenLoaiChi.getText().toString().equals("") ||edtTenLoaiChi.getText().toString()==null){
           Toast.makeText(getContext(),"Không được để trống!!", Toast.LENGTH_SHORT).show();
            error = false;
        }else {
            for (  int i = 0; i<listdata.size();i++){
                if (listdata.get(i).getTenLoaiChi().equals(edtTenLoaiChi.getText().toString())){
                    Toast.makeText(getContext(),"Không được nhập trùng tên loại!!", Toast.LENGTH_SHORT).show();
                    error = false;
                }
            }
        }
        if (error==true){
            long check = loaiChiDAO.AddItem(new LoaiChi(
                    edtTenLoaiChi.getText().toString()
            ));
            if (check>0){
                Toast.makeText(getContext(),"Thêm thành công!!", Toast.LENGTH_SHORT).show();
                LoadDuLieuLoaiChi();
            }else {
                Toast.makeText(getContext(),"Không thành công!!", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void LoadDuLieuLoaiChi() {
        listdata = loaiChiDAO.getLoaiChi();
        adapter = new LoaiChiAdapter(listdata, getContext());
        recyclerView_Loai_Chi.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

}

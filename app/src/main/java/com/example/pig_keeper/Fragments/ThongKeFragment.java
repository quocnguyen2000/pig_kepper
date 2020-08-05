package com.example.pig_keeper.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.pig_keeper.DAO.KhoanChiDAO;
import com.example.pig_keeper.DAO.KhoanThuDAO;
import com.example.pig_keeper.R;

import java.text.NumberFormat;
import java.util.Locale;

public class ThongKeFragment extends Fragment {

    KhoanThuDAO khoanThuDAO;
    KhoanChiDAO khoanChiDAO;
    TextView tvTongTienThu, tvTongTienChi,tvTongTienTK;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thongke, container, false);
        //anh xa
        tvTongTienThu = view.findViewById(R.id.tvTongTienThu);
        tvTongTienChi = view.findViewById(R.id.tvTongTienChi);
        tvTongTienTK = view.findViewById(R.id.tvTongTienTietKiem);
        //
        khoanThuDAO = new KhoanThuDAO(getContext());
        khoanChiDAO = new KhoanChiDAO(getContext());
        //tong tien thu
        int tienThu = khoanThuDAO.SumTienKhoanThu();
        tvTongTienThu.setText(NumberFormat.getNumberInstance(Locale.US).format(tienThu) + " VNĐ");
        //tong tien chi
        int tienChi = khoanChiDAO.SumTienKhoanChi();
        tvTongTienChi.setText(NumberFormat.getNumberInstance(Locale.US).format(tienChi) + " VNĐ");
        //thu-chi
        int tienTK = tienThu - tienChi;
        tvTongTienTK.setText(NumberFormat.getNumberInstance(Locale.US).format(tienTK) + " VNĐ");

        return view;
    }
}

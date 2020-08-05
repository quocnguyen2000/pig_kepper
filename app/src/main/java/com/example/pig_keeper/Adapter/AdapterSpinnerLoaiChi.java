package com.example.pig_keeper.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.pig_keeper.Model.LoaiChi;
import com.example.pig_keeper.Model.LoaiChi;
import com.example.pig_keeper.R;

import java.util.List;


public class AdapterSpinnerLoaiChi extends ArrayAdapter<LoaiChi> {

    public List<LoaiChi> listData;
    public Context context;
    public int resource;


    public AdapterSpinnerLoaiChi(@NonNull Context context, int resource, @NonNull List<LoaiChi> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.listData=objects;
    }
    class viewHolder{
        TextView tvLoaiChi;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        viewHolder view;
        if(convertView==null)
        {
            convertView= LayoutInflater.from(context).inflate(R.layout.item_spinner_loaichi,parent,false);
            view=new viewHolder();
            view.tvLoaiChi=convertView.findViewById(R.id.tvLoaiChi);
            convertView.setTag(view);
        }
        else
        {
            view= (viewHolder) convertView.getTag();
        }
                view.tvLoaiChi.setText(listData.get(position).getTenLoaiChi());
        return convertView;
    }
}

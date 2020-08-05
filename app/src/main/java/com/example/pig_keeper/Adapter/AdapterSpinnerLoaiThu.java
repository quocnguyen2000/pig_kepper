package com.example.pig_keeper.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.pig_keeper.Model.LoaiThu;
import com.example.pig_keeper.Model.LoaiThu;
import com.example.pig_keeper.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;


public class AdapterSpinnerLoaiThu extends ArrayAdapter<LoaiThu> {

    public List<LoaiThu> listData;
    public Context context;
    public int resource;


    public AdapterSpinnerLoaiThu(@NonNull Context context, int resource, @NonNull List<LoaiThu> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.listData=objects;
    }
    class viewHolder{
        TextView tvLoaiThu;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        viewHolder view;
        if(convertView==null)
        {
            convertView= LayoutInflater.from(context).inflate(R.layout.item_spinner_loaithu,parent,false);
            view=new viewHolder();
            view.tvLoaiThu=convertView.findViewById(R.id.tvLoaiThu);
            convertView.setTag(view);
        }
        else
        {
            view= (viewHolder) convertView.getTag();
        }
                view.tvLoaiThu.setText(listData.get(position).getTenLoaiThu());
        return convertView;
    }
}

package com.example.pig_keeper.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.example.pig_keeper.TabPagerFragment.KhoanThuFragment;
import com.example.pig_keeper.TabPagerFragment.LoaiThuFragment;

public class PagerAdapterThu extends FragmentStatePagerAdapter {
    public PagerAdapterThu(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment frag=null;
        switch (position){
            case 0:
                frag= new KhoanThuFragment();
                break;
            case 1:
                frag=new LoaiThuFragment();

                break;

        }
        return frag;
    }


    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        String title = "";
        switch (position){
            case 0:
                title = "Khoản Thu";
                break;
            case 1:
                title = "Loại Thu";
                break;
        }
        return title;
    }
}


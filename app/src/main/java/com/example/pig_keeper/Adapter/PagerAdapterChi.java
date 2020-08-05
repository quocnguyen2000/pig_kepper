package com.example.pig_keeper.Adapter;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.pig_keeper.TabPagerFragment.KhoanChiFragment;
import com.example.pig_keeper.TabPagerFragment.LoaiChiFragment;

public class PagerAdapterChi extends FragmentStatePagerAdapter {
    public PagerAdapterChi(FragmentManager fragmentManager) {
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
                frag= new KhoanChiFragment();
                break;
            case 1:
                frag=new LoaiChiFragment();

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
                title = "Khoản Chi";
                break;
            case 1:
                title = "Loại Chi";
                break;
        }
        return title;
    }
}


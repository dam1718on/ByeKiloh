package com.example.byeKiloh.utils;

import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.byeKiloh.fragments.*;

public class PagerController extends FragmentPagerAdapter {

    int numofTabs;

    public PagerController(@NonNull FragmentManager fm, int behavior) {

        super(fm, behavior);
        this.numofTabs = behavior;

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch(position) {

            case 1:
                return new B_Main_Registros();
            case 2:
                return new C_Main_Logros_Disable();
            case 3:
                return new D_Main_CopiasdeSeguridad_Disable();
            default:
                return new A_Main_Promedio();

        }

    }

    @Override
    public int getCount() {

        return numofTabs;

    }

}
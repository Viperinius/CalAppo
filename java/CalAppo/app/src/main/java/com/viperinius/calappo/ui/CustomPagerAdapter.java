package com.viperinius.calappo.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.viperinius.calappo.ui.month.MonthFragment;

import java.util.ArrayList;

public class CustomPagerAdapter extends FragmentStatePagerAdapter {

    public static int PAGE_COUNT = 251;

    public CustomPagerAdapter(FragmentManager fragmentManager, int behavior) {
        super(fragmentManager, behavior);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return CalFragment.init(position);
            case 1:
                return CalFragment.init(position);
            default:
                return CalFragment.init(position);
        }
    }
}

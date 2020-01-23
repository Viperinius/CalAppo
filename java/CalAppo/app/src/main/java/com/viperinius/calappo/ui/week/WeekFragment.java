package com.viperinius.calappo.ui.week;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.viperinius.calappo.R;
import com.viperinius.calappo.ui.CustomPagerAdapter;

import static com.viperinius.calappo.ui.CustomPagerAdapter.PAGE_COUNT;

public class WeekFragment extends Fragment {

    private WeekViewModel weekViewModel;
    private CustomPagerAdapter customPagerAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        weekViewModel =
                ViewModelProviders.of(this).get(WeekViewModel.class);
        View root = inflater.inflate(R.layout.fragment_week, container, false);
        //final TextView textView = root.findViewById(R.id.text_slideshow);
        /*weekViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        final ViewPager viewPager = root.findViewById(R.id.week_viewpager);
        customPagerAdapter = new CustomPagerAdapter(getFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(customPagerAdapter);
        viewPager.setCurrentItem(PAGE_COUNT / 2);

        return root;
    }
}
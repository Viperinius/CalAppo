package com.viperinius.calappo.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.viperinius.calappo.R;

public class CalFragment extends Fragment {
    private int fragmentVal;

    public static CalFragment init(int val) {
        CalFragment newFrag = new CalFragment();

        Bundle args = new Bundle();
        args.putInt("val", val);
        newFrag.setArguments(args);

        return newFrag;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentVal = getArguments() != null ? getArguments().getInt("val") : 1;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layoutView = inflater.inflate(R.layout.fragment_cal, container, false);
        View text = layoutView.findViewById(R.id.cal_text);
        ((TextView) text).setText("FRAGMENT " + fragmentVal);
        return layoutView;
    }
}

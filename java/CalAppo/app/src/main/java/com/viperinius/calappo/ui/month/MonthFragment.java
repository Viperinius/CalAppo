package com.viperinius.calappo.ui.month;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.viperinius.calappo.MainActivity;
import com.viperinius.calappo.R;
import com.viperinius.calappo.ui.CustomCalView;
import com.viperinius.calappo.ui.MainViewModel;
import com.viperinius.calappo.ui.overview.OverviewViewModel;

import org.joda.time.LocalDate;
import org.joda.time.YearMonth;

import java.util.HashSet;
import java.util.Locale;

public class MonthFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_month, container, false);

        HashSet<LocalDate> events = new HashSet<>();
        events.add(new LocalDate());

        CustomCalView cv = root.findViewById(R.id.cal_view);
        cv.updateCal(events);
        YearMonth yearMonth = new YearMonth(cv.today.getYear(), cv.today.getMonthOfYear());
        ((MainActivity) getActivity()).setActionBarTitle(yearMonth.monthOfYear().getAsText());
        cv.setEventHandler(new CustomCalView.EventHandler() {
            @Override
            public void onDayLongPress(LocalDate date) {
                String text = date.toString("dd-MMM-yyyy");
                Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
            }
        });
        return root;
    }
}
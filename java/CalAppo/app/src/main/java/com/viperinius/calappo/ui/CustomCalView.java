package com.viperinius.calappo.ui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.provider.CalendarContract;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.viperinius.calappo.R;

import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.chrono.CopticChronology;
import org.joda.time.chrono.GJChronology;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;

public class CustomCalView extends LinearLayout {
    // 42 days displayed at once (six weeks)
    private static final int DISPLAYED_DAYS = 42;

    private Calendar currentDate = Calendar.getInstance(new Locale("de", "DE"));
    public LocalDate today = new LocalDate();

    private EventHandler eventHandler = null;

    // components
    private LinearLayout header;
    private GridView grid;

    public CustomCalView(Context context) {
        super(context);
    }

    public CustomCalView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLayout(context, attrs);
    }

    public CustomCalView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayout(context, attrs);
    }

    private void initLayout(Context context, AttributeSet attrs) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.cal_view, this);

        // assign UI components
        header = (LinearLayout) findViewById(R.id.cal_header);
        grid = (GridView) findViewById(R.id.cal_grid);

        // set click handler
        grid.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (eventHandler == null)
                    return false;

                eventHandler.onDayLongPress((LocalDate) parent.getItemAtPosition(position));
                return true;
            }
        });

        updateCal();
    }

    public void updateCal() {
        updateCal(null);
    }

    public void updateCal(HashSet<LocalDate> events) {
        ArrayList<LocalDate> cells = new ArrayList<>();

        // get cell for first of month
        LocalDate first = today.withDayOfMonth(1);
        LocalDate cellStart = first;


        // if first of month is not monday, go back
        if (first.getDayOfWeek() > 1) {
            cellStart = first.minusDays(first.getDayOfWeek() - 1);
        }

        LocalDate currentCell = cellStart;
        while (cells.size() < DISPLAYED_DAYS) {
            cells.add(currentCell);
            currentCell = currentCell.plusDays(1);
        }

        // update grid
        grid.setAdapter(new CalendarAdapter(getContext(), cells, events));
    }

    private class CalendarAdapter extends ArrayAdapter<LocalDate> {
        // days with appointments
        private HashSet<LocalDate> eventDays;
        private LayoutInflater inflater;

        public CalendarAdapter(Context context, ArrayList<LocalDate> days, HashSet<LocalDate> eventDays) {
            super(context, R.layout.cal_view, days);
            this.eventDays = eventDays;
            inflater = LayoutInflater.from(context);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            // get day
            LocalDate date = getItem(position);
            int day = date.getDayOfMonth();
            int month = date.getMonthOfYear();
            int year = date.getYear();

            // inflate if needed
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.cal_view_day, parent, false);
            }

            convertView.setBackgroundResource(0);
            convertView.setMinimumHeight(parent.getHeight() / 6);
            if (eventDays != null) {
                for (LocalDate eventDate : eventDays) {
                    if (eventDate.getDayOfMonth() == day && eventDate.getMonthOfYear() == month && eventDate.getYear() == year) {
                        //convertView.setBackgroundResource(R.drawable.ic_notifications_none_black_24dp);
                        /*Drawable[] layers = {
                                ContextCompat.getDrawable(getContext(), R.drawable.ic_notifications_none_black_24dp),
                                ContextCompat.getDrawable(getContext(), R.drawable.border_all_sides)};
                        LayerDrawable layerDrawable = new LayerDrawable(layers);

                        convertView.setBackground(layerDrawable);
                        */
                        // TODO: draw all events here that do not last multiple days -> if possible???

                        convertView.setBackgroundResource(R.drawable.border_all_sides);
                        break;
                    }
                    else {
                        convertView.setBackgroundResource(R.drawable.border_all_sides);
                    }
                }
            }

            // clear style
            ((TextView) convertView).setTypeface(null, Typeface.NORMAL);
            ((TextView) convertView).setTextColor(Color.BLACK);
            ((TextView) convertView).setGravity(Gravity.CENTER_HORIZONTAL);

            if (month != today.getMonthOfYear() || year != today.getYear()) {
                // grey out
                ((TextView) convertView).setTextColor(getResources().getColor(R.color.colorGreyedOut));
            }
            else if (day == today.getDayOfMonth()) {
                // bold and color it
                ((TextView) convertView).setTypeface(null, Typeface.BOLD);
                ((TextView) convertView).setTextColor(getResources().getColor(R.color.colorPrimary));
            }

            // set text
            ((TextView) convertView).setText(String.valueOf(date.getDayOfMonth()));
            return convertView;
        }
    }

    public void setEventHandler(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    public interface EventHandler {
        void onDayLongPress(LocalDate date);
    }
}

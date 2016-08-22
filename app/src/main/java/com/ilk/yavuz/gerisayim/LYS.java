package com.ilk.yavuz.gerisayim;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class LYS extends Fragment {
    private TextView txtTitle, txtDays, txtDate;

    public static LYS newInstance(String title, long remainingDays) {
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putLong("remainingDays", remainingDays);

        LYS fragment = new LYS();
        fragment.setArguments(args);
        return fragment;
    }

    public LYS() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(com.ilk.yavuz.gerisayim.R.layout.fragment_lys, container, false);
        txtTitle = (TextView) view.findViewById(com.ilk.yavuz.gerisayim.R.id.txt_title);
        txtDays = (TextView) view.findViewById(com.ilk.yavuz.gerisayim.R.id.txt_days);
        txtDate = (TextView) view.findViewById(com.ilk.yavuz.gerisayim.R.id.txt_date);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        long remainingDays = getArguments().getLong("remainingDays");
        if (getArguments() != null) {
            txtTitle.setText(String.format("%s ne kadar zaman kaldı", String.valueOf(getArguments().getString("title"))));
            txtDays.setText(String.valueOf(remainingDays)+" Gün Kaldı");


        }

        setDate(remainingDays);
    }

    private void setDate(long days) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, (int) days);

        DateFormat df = new SimpleDateFormat("dd.MM.yyyy", new Locale("tr"));
        txtDate.setText("Sınav Tarihi :"+df.format(calendar.getTime()));
    }

    private class LocalDate {
    }

    private class Period {
    }

    private class ChronoUnit {
    }
}

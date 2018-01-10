package com.example.arnaudschaal.ournation.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.arnaudschaal.ournation.R;


public class CalendarFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    public CalendarFragment() {
        // Required empty public constructor
    }

    public static CalendarFragment newInstance(int sectionNumber) {
        CalendarFragment fragment = new CalendarFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_calendar, container, false);

        setRetainInstance(true);
        return rootView;
    }
}

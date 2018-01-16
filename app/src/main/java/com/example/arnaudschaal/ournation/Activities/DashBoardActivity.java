package com.example.arnaudschaal.ournation.Activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.example.arnaudschaal.ournation.R;
import com.example.arnaudschaal.ournation.Tools.ToolsBox;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

public class DashBoardActivity extends AppCompatActivity {
    private ImageView iv;
    private MaterialCalendarView cv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        iv = (ImageView) findViewById(R.id.dashboard_activity_calendar_background);
        cv = (MaterialCalendarView) findViewById(R.id.dashboard_activity_calendar_view);

        initCalendar();
    }

    private void initCalendar(){
        if(cv == null)
            return;

        cv.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
                switch (ToolsBox.getSeason(date.getMonth())) {
                    case 0:
                        iv.setImageResource(R.drawable.winter);
                        break;
                    case 1:
                        iv.setImageResource(R.drawable.spring);
                        break;
                    case 2:
                        iv.setImageResource(R.drawable.summer);
                        break;
                    case 3:
                        iv.setImageResource(R.drawable.automn);
                        break;
                }
            }
        });

        cv.setArrowColor(R.color.transparent);
    }
}

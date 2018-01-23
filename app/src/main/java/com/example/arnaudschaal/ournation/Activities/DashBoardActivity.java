package com.example.arnaudschaal.ournation.Activities;

import android.graphics.PorterDuff;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.Toast;

import com.example.arnaudschaal.ournation.Adapter.EventsDashboardAdatper;
import com.example.arnaudschaal.ournation.Listener.AppBarStateChangeListener;
import com.example.arnaudschaal.ournation.R;
import com.example.arnaudschaal.ournation.RestClient.Clients.EventsClient;
import com.example.arnaudschaal.ournation.RestClient.Listeners.IClientListener;
import com.example.arnaudschaal.ournation.RestClient.Models.Interface.IJSONMessage;
import com.example.arnaudschaal.ournation.RestClient.Models.Objects.Event;
import com.example.arnaudschaal.ournation.RestClient.Models.ResponseModels.GetEventsResponse;
import com.example.arnaudschaal.ournation.Session.Context;
import com.example.arnaudschaal.ournation.Tools.ToolsBox;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;
import com.transitionseverywhere.Slide;
import com.transitionseverywhere.TransitionManager;
import com.transitionseverywhere.TransitionSet;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static android.widget.Toast.LENGTH_SHORT;

public class DashBoardActivity extends AppCompatActivity {
    private EventsDashboardAdatper rvAdapter;
    private CollapsingToolbarLayout toolbar;
    private AppBarLayout appBarLayout;
    private FloatingActionButton fab;
    private MaterialCalendarView cv;
    private Toolbar toolbarTemp;
    private ViewGroup container;
    private RecyclerView rv;

    private int color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        container = (ViewGroup) findViewById(R.id.dashboard_activity_main_container);

        initRecyclerView();
        iniToolBar();
        initCalendar();
        initFab();
    }

    private void initRecyclerView() {
        rv = (RecyclerView) findViewById(R.id.dashboard_activity_recycler_view);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(mLayoutManager);
        rv.setNestedScrollingEnabled(false);
        rv.setHasFixedSize(true);
    }

    private void initCalendar(){
        cv = (MaterialCalendarView) findViewById(R.id.dashboard_activity_calendar_view);

        cv.setBackgroundColor(getColor(R.color.transparent));
        cv.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
                onCalendarMonthChanged(date);
            }
        });

        cv.setArrowColor(R.color.transparent);
        cv.setAllowClickDaysOutsideCurrentMonth(false);
        cv.setTopbarVisible(false);
        cv.setSelectedDate(new Date());

        CalendarDay day = CalendarDay.from(new Date());
        onCalendarMonthChanged(day);
    }

    private void iniToolBar(){
        toolbarTemp = (Toolbar) findViewById(R.id.dashboard_activity_toolbar);
        appBarLayout = (AppBarLayout) findViewById(R.id.dashboard_activity_appBarLayout);
        toolbar = (CollapsingToolbarLayout) findViewById(R.id.dashboard_activity_collapsing_toolbar);

        setSupportActionBar(toolbarTemp);
        appBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                switch (state){
                    case EXPANDED:
                        hideFab();
                        break;
                    case COLLAPSED:
                        showFab();
                        break;
                }
            }
        });
    }

    private void initFab(){
        fab = (FloatingActionButton) findViewById(R.id.dashboard_activity_fab);
        fab.setVisibility(View.GONE);
    }

    private void populate(ArrayList<Event> events){
        rvAdapter = new EventsDashboardAdatper(getApplicationContext(), events, color);
        rv.setAdapter(rvAdapter);

        for (Event e : events) {
        }
    }

    private void changeColorToolbar(int color){
        this.color = color;
        toolbar.setBackgroundColor(color);
        toolbar.setContentScrimColor(ToolsBox.darkenColor(color));
        appBarLayout.setBackgroundColor(color);
        appBarLayout.setBackgroundTintMode(PorterDuff.Mode.DARKEN);
        ToolsBox.darkenStatusBar(DashBoardActivity.this, color);
    }

    private void getDatas(Date start, Date end){
        clearRecyclerView();
        EventsClient client = new EventsClient();

        client.setClientListener(new IClientListener() {
            @Override
            public void onResponseEnd(IJSONMessage response, String method) {
                if(!(response instanceof GetEventsResponse))
                    return;

                final GetEventsResponse responseEvents = (GetEventsResponse)response;
                if(responseEvents.getStatus().equals("200")){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            populate(responseEvents.Events);
                        }
                    });
                }
                else{
                    displayToast(responseEvents.getMessage());
                }
            }

            @Override
            public void onResponseErrorEnd(Object response, String method) {

            }
        });


        client.getEvents(
                Context.getInstance().getId(),
                start,
                end,
                -1
        );
    }

    private void clearRecyclerView(){
        if(rv == null)
            return;

        rv.setAdapter(new EventsDashboardAdatper(
                getApplicationContext(),
                new ArrayList<Event>(),
                color
        ));
    }

    private void displayToast(final String text){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getBaseContext(), text, LENGTH_SHORT)
                        .show();
            }
        });
    }

    private void onCalendarMonthChanged(CalendarDay date){
        toolbar.setTitle(ToolsBox.getMonth(date.getMonth()));
        switch (ToolsBox.getSeason(date.getMonth())) {
            case 0:
                changeColorToolbar(getColor(R.color.calendarBackgroundWinter));
                break;
            case 1:
                changeColorToolbar(getColor(R.color.calendarBackgroundSpring));
                break;
            case 2:
                changeColorToolbar(getColor(R.color.calendarBackgroundSummer));
                break;
            case 3:
                changeColorToolbar(getColor(R.color.calendarBackgroundAutomn));
                break;
        }

        Calendar start = Calendar.getInstance();
        start.set(Calendar.DAY_OF_MONTH, 1);
        start.set(Calendar.MONTH, date.getMonth());
        start.set(Calendar.YEAR, date.getYear());
        start.set(Calendar.HOUR, 0);
        start.set(Calendar.MINUTE, 0);
        start.set(Calendar.SECOND, 0);
        Calendar end = Calendar.getInstance();
        int maxDay = end.getActualMaximum(Calendar.DAY_OF_MONTH);
        end.set(Calendar.MONTH, date.getMonth());
        end.set(Calendar.DAY_OF_MONTH, maxDay);
        end.set(Calendar.YEAR, date.getYear());
        end.set(Calendar.HOUR, 0);
        end.set(Calendar.MINUTE, 0);
        end.set(Calendar.SECOND, 0);

        getDatas(start.getTime(), end.getTime());
    }

    private void hideFab(){
        if(fab == null)
            return;

        if(container == null)
            return;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Animation animation = AnimationUtils.loadAnimation(getBaseContext(), R.anim.slide_in_top);
                animation.setInterpolator(new AccelerateInterpolator(0.5f));
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        fab.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                fab.startAnimation(animation);
            }
        });
    }

    private void showFab(){
        if(fab == null)
            return;

        if(container == null)
            return;

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                fab.setVisibility(View.VISIBLE);
                Animation animation = AnimationUtils.loadAnimation(getBaseContext(), R.anim.slide_in_bottom);
                animation.setInterpolator(new DecelerateInterpolator(0.5f));
                fab.startAnimation(animation);
            }
        });
    }
}

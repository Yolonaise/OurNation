package com.example.arnaudschaal.ournation.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.arnaudschaal.ournation.Adapter.EventsDashboardAdatper;
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static android.widget.Toast.LENGTH_SHORT;

public class DashBoardActivity extends AppCompatActivity {
    private ImageView iv;
    private MaterialCalendarView cv;
    private RecyclerView rv;
    private EventsDashboardAdatper rvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        //iv = (ImageView) findViewById(R.id.dashboard_activity_calendar_background);
        cv = (MaterialCalendarView) findViewById(R.id.dashboard_activity_calendar_view);

        initCalendar();
        initRecyclerView();

        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(mLayoutManager);

        getDatas();
    }

    private void initRecyclerView() {
        rv = (RecyclerView) findViewById(R.id.dashboard_activity_recycler_view);
        //rv.setHasFixedSize(true);
        rv.setNestedScrollingEnabled(false);
    }

    private void initCalendar(){
        if(cv == null)
            return;

        cv.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
                switch (ToolsBox.getSeason(date.getMonth())) {
                    case 0:

                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                }
            }
        });

        cv.setArrowColor(R.color.transparent);
    }

    private void populate(ArrayList<Event> events){
        rvAdapter = new EventsDashboardAdatper(events);
        rv.setAdapter(rvAdapter);
    }

    private void getDatas(){
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

        Calendar start = Calendar.getInstance();   // this takes current date
        start.set(Calendar.DAY_OF_MONTH, 1);
        Calendar end = start;
        end.add(Calendar.MONTH, 1);

        client.getEvents(
                Context.getInstance().getId(),
                start.getTime(),
                null,
                -1
        );
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
}

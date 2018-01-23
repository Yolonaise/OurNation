package com.example.arnaudschaal.ournation.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.arnaudschaal.ournation.R;
import com.example.arnaudschaal.ournation.RestClient.Models.Objects.Event;
import com.example.arnaudschaal.ournation.Tools.ToolsBox;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by arnaud.schaal on 17-01-18.
 */

public class EventsDashboardAdatper extends RecyclerView.Adapter<EventsDashboardAdatper.EventDashBoardViewHolder> {
    private ArrayList<Event> datas;
    private Context cxt;
    private int lastPosition = -1;
    private int color;

    public static class EventDashBoardViewHolder extends RecyclerView.ViewHolder {
        public View  container;
        private CircleImageView userThumb;
        private TextView titre;
        private TextView description;
        private TextView startDate;
        private TextView endDate;

        public EventDashBoardViewHolder(View v) {
            super(v);

            this.container = v;
            //this.userThumb = (CircleImageView) v.findViewById(R.id.simple_event_circle_image);
            this.titre = (TextView) v.findViewById(R.id.simple_event_titre);
            this.description = (TextView) v.findViewById(R.id.simple_event_text_description);
            this.startDate = (TextView) v.findViewById(R.id.simple_event_start_date);
            this.endDate = (TextView) v.findViewById(R.id.simple_event_end_date);
        }

        public void setTitle(String text){
            titre.setText(text);
        }

        public void setDescprition (String text){
            String result = text;
            if(text.length() > 75){
                result = text.substring(0, 75 - 3);
                result += "...";
            }
            description.setText(result);
        }

        public void setStartDate(String text){
            startDate.setText(text);
        }

        public void setStartDateColor(int color){
            startDate.setBackgroundColor(color);
        }

        public void setEndDate(String text){
            endDate.setText(text);
        }

        public void setUserThumb(String imgSource){
            //TODO : Add image loading and implementation.
        }
    }

    public EventsDashboardAdatper(Context cxt, ArrayList<Event> datas, int color) {
        this.cxt = cxt;
        this.datas = datas;
        this.color = color;
    }

    @Override
    public EventDashBoardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dashboard_event_layout, parent, false);

        EventDashBoardViewHolder vh = new EventDashBoardViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(EventDashBoardViewHolder holder, int position) {
        holder.setTitle(String.valueOf(datas.get(position).getTitre()));
        holder.setDescprition(String.valueOf(datas.get(position).getDescription()));
        holder.setStartDate(String.valueOf(CalendarDay.from(datas.get(position).getStartDate()).getDay()));
        holder.setStartDateColor(ToolsBox.darkenColor(color, 1f - (1f + (float)position) / (float)getItemCount()));

        long diff =  datas.get(position).getEnDate().getTime() - datas.get(position).getStartDate().getTime();
        long ratio = ToolsBox.getTimeDivisor(diff);
        String timeText = ToolsBox.getTimeString(diff);
        holder.setEndDate(String.valueOf(ratio) + " " + timeText);
        //holder.setUserThumb(String.valueOf(datas.get(position).getEnDdate()));

        setAnimation(holder.container, position);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    private void setAnimation(View viewToAnimate, int position){
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(cxt, R.anim.slide_in_bottom);
            animation.setStartOffset(position * 100);
            animation.setInterpolator(cxt, R.anim.basic_interpolator);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }
}



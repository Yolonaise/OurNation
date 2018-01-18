package com.example.arnaudschaal.ournation.Adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.arnaudschaal.ournation.R;
import com.example.arnaudschaal.ournation.RestClient.Models.Objects.Event;

import java.util.ArrayList;

/**
 * Created by arnaud.schaal on 17-01-18.
 */

public class EventsDashboardAdatper extends RecyclerView.Adapter<EventsDashboardAdatper.EventDashBoardViewHolder> {
    private ArrayList<Event> datas;

    public static class EventDashBoardViewHolder extends RecyclerView.ViewHolder {
        public CardView  container;
        private TextView titre;

        public EventDashBoardViewHolder(CardView v) {
            super(v);

            this.container = v;
            this.titre = (TextView) v.findViewById(R.id.simple_event_text_view);
        }

        public void setTextView(String text){
            titre.setText(text);
        }
    }

    public EventsDashboardAdatper(ArrayList<Event> datas) {
        this.datas = datas;
    }

    @Override
    public EventDashBoardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView v = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.simple_event_card_view, parent, false);

        EventDashBoardViewHolder vh = new EventDashBoardViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(EventDashBoardViewHolder holder, int position) {
        holder.setTextView(String.valueOf(datas.get(position).getTitre()));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
}



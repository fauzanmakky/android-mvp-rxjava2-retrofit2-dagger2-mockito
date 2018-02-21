package co.gosalo.androidreview.main.mvp.view.adapter;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

import co.gosalo.androidreview.R;
import co.gosalo.androidreview.app.api.model.Event;
import co.gosalo.androidreview.main.MainActivity;

/**
 * Created by jorge on 05/02/2018.
 */

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    private List<Event> events = new ArrayList<>();
    private final MainActivity activity;

    public EventAdapter(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FrameLayout layout = (FrameLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_element, parent, false);
        return new ViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Event event = events.get(position);
        holder.title.setText(event.getTitle());
        holder.venue.setText(event.getVenue());

        if (position == getItemCount() - 1) {
            activity.incrementPage();
        }
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private AppCompatTextView title;
        private AppCompatTextView venue;

        public ViewHolder(View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.title);
            this.venue = itemView.findViewById(R.id.venue);
        }
    }

    public void addEvents(List<Event> events) {
        this.events.addAll(events);
        notifyDataSetChanged();
    }
}

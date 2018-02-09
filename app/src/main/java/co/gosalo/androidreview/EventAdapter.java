package co.gosalo.androidreview;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import co.gosalo.androidreview.api.model.Event;

/**
 * Created by jorge on 05/02/2018.
 */

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    private List<Event> events;

    public EventAdapter(List<Event> events) {
        this.events = events;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        AppCompatTextView textView = (AppCompatTextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.code_result, parent, false);
        return new ViewHolder(textView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(events.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public AppCompatTextView textView;

        public ViewHolder(AppCompatTextView textView) {
            super(textView);
            this.textView = textView;
        }
    }
}

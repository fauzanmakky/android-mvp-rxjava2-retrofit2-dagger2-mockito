package co.gosalo.androidreview.main.mvp.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.gosalo.androidreview.R;
import co.gosalo.androidreview.app.api.model.Event;
import co.gosalo.androidreview.main.mvp.view.adapter.EventAdapter;

/**
 * Created by jorge on 12/02/2018.
 */

public class MainView extends FrameLayout {

    @BindView(R.id.events_list)
    RecyclerView recyclerView;

    private final ProgressDialog progressDialog;

    public MainView(@NonNull Context context) {
        super(context);

        inflate(context, R.layout.activity_main, this);
        ButterKnife.bind(this);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Looking for events");
    }

    public void setEvents(List<Event> events) {
        RecyclerView.Adapter adapter = new EventAdapter(events);
        recyclerView.setAdapter(adapter);
    }

    public void showLoading(boolean loading) {
        if (loading) {
            progressDialog.show();
        } else {
            progressDialog.dismiss();
        }
    }
}

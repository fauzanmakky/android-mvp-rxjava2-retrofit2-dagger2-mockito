package co.gosalo.androidreview.main.mvp.view;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.gosalo.androidreview.R;
import co.gosalo.androidreview.app.api.model.Event;
import co.gosalo.androidreview.main.MainActivity;
import co.gosalo.androidreview.main.mvp.view.adapter.EventAdapter;

/**
 * Created by jorge on 12/02/2018.
 */

@SuppressLint("ViewConstructor")
public class MainView extends FrameLayout {

    @BindView(R.id.events_list)
    RecyclerView recyclerView;

    private final ProgressDialog progressDialog;
    private EventAdapter adapter;

    public MainView(@NonNull MainActivity activity) {
        super(activity);

        inflate(activity, R.layout.activity_main, this);
        ButterKnife.bind(this);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new EventAdapter(activity);
        recyclerView.setAdapter(adapter);

        progressDialog = new ProgressDialog(activity);
        progressDialog.setMessage("Looking for events");
    }

    public void addEvents(List<Event> events) {
        adapter.addEvents(events);
    }

    public void showNoEvents() {
    }

    public void showError() {

    }

    public void showLoading(boolean loading) {
        if (loading) {
            progressDialog.show();
        } else {
            progressDialog.dismiss();
        }
    }
}

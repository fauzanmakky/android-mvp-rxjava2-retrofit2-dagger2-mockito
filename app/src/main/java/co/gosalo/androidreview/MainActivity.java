package co.gosalo.androidreview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import co.gosalo.androidreview.api.GosaloService;
import co.gosalo.androidreview.api.PagedResponseBody;
import co.gosalo.androidreview.api.model.Event;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "MainActivity";

    private RecyclerView recyclerView;

    @Inject
    GosaloService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        App.getComponent().inject(this);
        recyclerView = findViewById(R.id.events_list);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        getEvents();
    }

    public void getEvents() {
        service.getEvents().enqueue(new Callback<PagedResponseBody<List<Event>>>() {
            @Override
            public void onResponse(@NonNull Call<PagedResponseBody<List<Event>>> call,
                                   @NonNull Response<PagedResponseBody<List<Event>>> response) {
                Log.i(LOG_TAG, String.valueOf(response.code()));
                if (response.isSuccessful()) {
                    List<Event> events = response.body().getContent();
                    RecyclerView.Adapter adapter = new EventAdapter(events);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(@NonNull Call<PagedResponseBody<List<Event>>> call, @NonNull Throwable t) {
                Log.i(LOG_TAG, "Gosalo call failed");
            }
        });
    }
}

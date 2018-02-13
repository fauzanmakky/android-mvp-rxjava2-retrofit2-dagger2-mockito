package co.gosalo.androidreview.main.mvp;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import co.gosalo.androidreview.app.api.PagedResponseBody;
import co.gosalo.androidreview.app.api.model.Event;
import co.gosalo.androidreview.main.mvp.view.MainView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jorge on 12/02/2018.
 */

public class MainPresenter {

    private static final String LOG_TAG = "MainPresenter";

    private final MainModel mainModel;
    private final MainView mainView;
    private Call<PagedResponseBody<List<Event>>> call;

    public MainPresenter(MainModel mainModel, MainView mainView) {
        this.mainView = mainView;
        this.mainModel = mainModel;
    }

    public void onCreate() {
        mainView.showLoading(true);
        call = mainModel.getEvents();
        call.enqueue(new Callback<PagedResponseBody<List<Event>>>() {
            @Override
            public void onResponse(@NonNull Call<PagedResponseBody<List<Event>>> call,
                                   @NonNull Response<PagedResponseBody<List<Event>>> response) {
                Log.i(LOG_TAG, String.valueOf(response.code()));
                if (response.isSuccessful()) {
                    List<Event> events = response.body().getContent();
                    mainView.setEvents(events);
                    mainView.showLoading(false);
                }
            }

            @Override
            public void onFailure(@NonNull Call<PagedResponseBody<List<Event>>> call, @NonNull Throwable t) {
                Log.i(LOG_TAG, "Gosalo call failed");
            }
        });
    }

    public void onDestroy() {
        call.cancel();
    }
}

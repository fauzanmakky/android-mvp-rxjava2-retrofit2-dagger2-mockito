package co.gosalo.androidreview.main.mvp;

import android.util.Log;

import java.util.List;

import co.gosalo.androidreview.app.api.PagedResponseBody;
import co.gosalo.androidreview.app.api.model.Event;
import co.gosalo.androidreview.main.mvp.view.MainView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

/**
 * Created by jorge on 12/02/2018.
 */

public class MainPresenter {

    private static final String LOG_TAG = "MainPresenter";

    private final MainModel mainModel;
    private final MainView mainView;
    private Disposable disposable;

    public MainPresenter(MainModel mainModel, MainView mainView) {
        this.mainView = mainView;
        this.mainModel = mainModel;
    }

    public void onCreate() {
        mainView.showLoading(true);

        disposable = mainModel.getEvents()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<PagedResponseBody<List<Event>>>() {
                    @Override
                    public void onNext(PagedResponseBody<List<Event>> listPagedResponseBody) {
                        List<Event> events = listPagedResponseBody.getContent();
                        mainView.setEvents(events);
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException) {
                            HttpException httpException = (HttpException) e;
                            Log.d(LOG_TAG, String.valueOf(httpException.code()));
                        }
                        mainView.showLoading(false);
                        Log.i(LOG_TAG, "Gosalo call failed: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        mainView.showLoading(false);
                    }
                });
    }

    public void onDestroy() {
        disposable.dispose();
    }
}

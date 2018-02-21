package co.gosalo.androidreview.main.mvp;

import android.util.Log;

import co.gosalo.androidreview.main.mvp.view.MainView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
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
                .subscribe(
                        listPagedResponseBody -> mainView.setEvents(listPagedResponseBody.getContent()),
                        throwable -> {
                            if (throwable instanceof HttpException) {
                                HttpException httpException = (HttpException) throwable;
                                Log.d(LOG_TAG, String.valueOf(httpException.code()));
                            }
                            mainView.showLoading(false);
                            Log.i(LOG_TAG, "Gosalo call failed: " + throwable.getMessage());
                        },
                        () -> mainView.showLoading(false)
                );
    }

    public void onDestroy() {
        disposable.dispose();
    }
}

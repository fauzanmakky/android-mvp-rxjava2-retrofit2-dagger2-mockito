package co.gosalo.androidreview.main.mvp;

import android.util.Log;

import co.gosalo.androidreview.main.mvp.view.MainView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;
import retrofit2.HttpException;

/**
 * Created by jorge on 12/02/2018.
 */

public class MainPresenter {

    private static final String LOG_TAG = "MainPresenter";

    private final MainModel mainModel;
    private final MainView mainView;
    private CompositeDisposable disposables = new CompositeDisposable();
    private Subject<Integer> page = PublishSubject.create();
    private int currentPage = 0;
    private boolean last;

    public MainPresenter(MainModel mainModel, MainView mainView) {
        this.mainView = mainView;
        this.mainModel = mainModel;
    }

    public void onCreate() {
        mainView.showLoading(true);
        disposables.add(page.subscribe(
                pageNumber -> disposables.add(loadEvents())
        ));
        page.onNext(currentPage);
    }

    public void onDestroy() {
        disposables.dispose();
    }

    public void incrementPage() {
        if (!last) {
            currentPage++;
            page.onNext(currentPage);
        }
    }

    private Disposable loadEvents() {
        return mainModel.getEvents(currentPage)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        listPagedResponseBody -> {
                            mainView.addEvents(listPagedResponseBody.getContent());
                            last = listPagedResponseBody.isLast();
                        },
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
}

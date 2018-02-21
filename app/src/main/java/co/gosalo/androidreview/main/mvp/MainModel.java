package co.gosalo.androidreview.main.mvp;

import java.util.List;

import co.gosalo.androidreview.app.api.GosaloService;
import co.gosalo.androidreview.app.api.PagedResponseBody;
import co.gosalo.androidreview.app.api.model.Event;
import io.reactivex.Observable;

/**
 * Created by jorge on 12/02/2018.
 */

public class MainModel {

    private GosaloService service;

    public MainModel(GosaloService service) {
        this.service = service;
    }

    public Observable<PagedResponseBody<List<Event>>> getEvents(int page) {
        return service.getEvents(page);
    }
}

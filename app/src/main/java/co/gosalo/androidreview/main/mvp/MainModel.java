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
    private int currentPage = 0;
    private boolean last;

    public MainModel(GosaloService service) {
        this.service = service;
    }

    public boolean isLast() {
        return last;
    }

    public Observable<List<Event>> getNextEvents() {
        return getPagedEvents(currentPage).map(
                listPagedResponseBody -> {
                    last = listPagedResponseBody.isLast();
                    if (!last) {
                        currentPage++;
                    }
                    return listPagedResponseBody.getContent();
                }
        );
    }

    private Observable<PagedResponseBody<List<Event>>> getPagedEvents(int page) {
        return service.getEvents(page);
    }
}

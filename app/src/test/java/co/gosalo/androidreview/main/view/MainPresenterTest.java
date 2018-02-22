package co.gosalo.androidreview.main.view;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import co.gosalo.androidreview.app.api.PagedResponseBody;
import co.gosalo.androidreview.app.api.model.Event;
import co.gosalo.androidreview.main.mvp.MainModel;
import co.gosalo.androidreview.main.mvp.MainPresenter;
import co.gosalo.androidreview.main.mvp.view.MainView;
import co.gosalo.androidreview.util.RxSchedulersOverrideRule;
import io.reactivex.Observable;

/**
 * Created by jorge on 20/02/2018.
 */

@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {

    @Rule
    public RxSchedulersOverrideRule rule = new RxSchedulersOverrideRule();

    private static final int FIRST_PAGE = 0;

    @Mock
    private MainModel mainModel;

    @Mock
    private MainView mainView;

    private MainPresenter mainPresenter;

    @Before
    public void setUp() throws Exception {
        mainPresenter = new MainPresenter(mainModel, mainView);
    }

    @Test
    public void shouldPassEventsToView() {
        // given
        PagedResponseBody<List<Event>> listPagedResponseBody = new PagedResponseBody<>();

        // when
        Mockito.when(mainModel.getEvents(FIRST_PAGE)).thenReturn(Observable.just(listPagedResponseBody));
        mainPresenter.onCreate();

        // then
        Mockito.verify(mainView, Mockito.times(1)).addEvents(listPagedResponseBody.getContent());
    }
}

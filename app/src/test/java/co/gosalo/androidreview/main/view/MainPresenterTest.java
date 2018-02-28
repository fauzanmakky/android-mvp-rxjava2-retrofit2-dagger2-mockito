package co.gosalo.androidreview.main.view;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    @Mock
    private MainModel model;

    @Mock
    private MainView view;

    private MainPresenter presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new MainPresenter(model, view);
    }

    @Test
    public void shouldPassEventsToView() {
        // given
        List<Event> events = Arrays.asList(new Event(), new Event(), new Event());

        // when
        Mockito.when(model.getNextEvents()).thenReturn(Observable.just(events));
        presenter.onCreate();

        // then
        Mockito.verify(view, Mockito.times(1)).showLoading(true);
        Mockito.verify(view, Mockito.times(1)).addEvents(events);
        Mockito.verify(view, Mockito.times(1)).showLoading(false);
        Mockito.verifyNoMoreInteractions(view);
    }

    @Test
    public void shouldHandleNoEvents() {
        // given
        List<Event> events = new ArrayList<>();

        // when
        Mockito.when(model.getNextEvents()).thenReturn(Observable.just(events));
        presenter.onCreate();

        // then
        Mockito.verify(view, Mockito.times(1)).showLoading(true);
        Mockito.verify(view, Mockito.times(1)).showNoEvents();
        Mockito.verify(view, Mockito.times(1)).showLoading(false);
        Mockito.verifyNoMoreInteractions(view);
    }

    @Test
    public void shouldHandleError() {
        // given
        Exception exception = new Exception();

        // when
        Mockito.when(model.getNextEvents()).thenReturn(Observable.error(exception));
        presenter.onCreate();

        // then
        Mockito.verify(view, Mockito.times(1)).showLoading(true);
        Mockito.verify(view, Mockito.times(1)).showError();
        Mockito.verify(view, Mockito.times(1)).showLoading(false);
        Mockito.verifyNoMoreInteractions(view);
    }
}

package co.gosalo.androidreview.app;

import co.gosalo.androidreview.app.api.ApiModule;
import co.gosalo.androidreview.app.api.GosaloService;
import dagger.Component;

@AppScope
@Component(modules = {ApiModule.class})
public interface AppComponent {

    GosaloService gosaloService();
}

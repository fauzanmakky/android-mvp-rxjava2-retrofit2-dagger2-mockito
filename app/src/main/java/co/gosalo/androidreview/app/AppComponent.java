package co.gosalo.androidreview.app;

import co.gosalo.androidreview.app.api.ApiModule;
import co.gosalo.androidreview.app.api.GosaloService;
import co.gosalo.androidreview.main.MainComponent;
import co.gosalo.androidreview.main.MainModule;
import dagger.Component;

@AppScope
@Component(modules = {ApiModule.class})
public interface AppComponent {

    MainComponent plusMainComponent(MainModule mainModule);

    GosaloService gosaloService();
}

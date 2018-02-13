package co.gosalo.androidreview.main;

import co.gosalo.androidreview.app.AppComponent;
import dagger.Component;

/**
 * Created by jorge on 12/02/2018.
 */

@MainScope
@Component(modules = {MainModule.class}, dependencies = AppComponent.class)
public interface MainComponent {

    void inject(MainActivity mainActivity);
}

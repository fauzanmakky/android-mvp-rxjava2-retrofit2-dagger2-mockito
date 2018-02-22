package co.gosalo.androidreview.main;

import dagger.Subcomponent;

/**
 * Created by jorge on 12/02/2018.
 */

@MainScope
@Subcomponent(modules = {MainModule.class})
public interface MainComponent {

    void inject(MainActivity mainActivity);
}

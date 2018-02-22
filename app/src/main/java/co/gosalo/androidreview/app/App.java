package co.gosalo.androidreview.app;

import android.app.Application;

import co.gosalo.androidreview.app.api.ApiModule;
import co.gosalo.androidreview.main.MainActivity;
import co.gosalo.androidreview.main.MainComponent;
import co.gosalo.androidreview.main.MainModule;

public class App extends Application {

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = co.gosalo.androidreview.app.DaggerAppComponent.builder()
                .apiModule(new ApiModule())
                .build();
    }

    public static MainComponent createMainComponent(MainActivity activity) {
        return appComponent.plusMainComponent(new MainModule(activity));
    }
}

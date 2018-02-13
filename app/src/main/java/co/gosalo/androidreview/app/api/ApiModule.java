package co.gosalo.androidreview.app.api;

import co.gosalo.androidreview.app.AppScope;
import dagger.Module;
import dagger.Provides;

@Module
public class ApiModule {

    @AppScope
    @Provides
    public GosaloService provideGosaloService() {
        return new GosaloClient().getGosaloService();
    }
}

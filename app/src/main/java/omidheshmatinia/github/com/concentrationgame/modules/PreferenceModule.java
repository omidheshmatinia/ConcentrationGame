package omidheshmatinia.github.com.concentrationgame.modules;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Omid Heshmatinia on 8/3/17.
 */
@Module
public class PreferenceModule {

    private Application mApplication;

    public PreferenceModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Application providesApplication() {
        return mApplication;
    }
}

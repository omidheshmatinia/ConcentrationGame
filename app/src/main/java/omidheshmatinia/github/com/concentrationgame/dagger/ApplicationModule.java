package omidheshmatinia.github.com.concentrationgame.dagger;

import android.app.Application;
import android.content.Context;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import omidheshmatinia.github.com.concentrationgame.base.MasterApplication;
import omidheshmatinia.github.com.concentrationgame.utils.PreferenceHelper;

@Module
public class ApplicationModule {

  private MasterApplication application;

  public ApplicationModule(MasterApplication application) {
    this.application = application;
  }

  @Singleton
  @Provides
  public Application provideApplication(){
    return application;
  }

  @Singleton
  @Provides
  public Context provideContext(){
    return application;
  }

  @Singleton
  @Provides
  public PreferenceHelper providePreferenceHelper(){
    return new PreferenceHelper(application);
  }
}

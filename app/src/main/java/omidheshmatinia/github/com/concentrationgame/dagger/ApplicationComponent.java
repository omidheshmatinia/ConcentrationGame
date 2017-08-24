package omidheshmatinia.github.com.concentrationgame.dagger;

import android.content.Context;
import dagger.Component;
import javax.inject.Singleton;
import omidheshmatinia.github.com.concentrationgame.base.MasterApplication;
import omidheshmatinia.github.com.concentrationgame.utils.PreferenceHelper;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
  void inject(MasterApplication application);

  Context getContext();
  PreferenceHelper getPreferenceHelper();
}

package omidheshmatinia.github.com.concentrationgame.dagger;

import android.content.Context;
import android.content.SharedPreferences;
import dagger.Component;
import java.lang.annotation.Retention;
import javax.inject.Scope;
import javax.inject.Singleton;
import omidheshmatinia.github.com.concentrationgame.base.MasterApplication;
import omidheshmatinia.github.com.concentrationgame.utils.PreferenceHelper;

/**
 * Created by Omid Heshmatinia on 19/08/2017.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
  void inject(MasterApplication application);

  Context getContext();
  PreferenceHelper getPreferenceHelper();
}

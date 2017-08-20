package omidheshmatinia.github.com.concentrationgame.activity.highscores;

import dagger.Component;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Scope;
import omidheshmatinia.github.com.concentrationgame.dagger.ApplicationComponent;
import omidheshmatinia.github.com.concentrationgame.dagger.ApplicationModule;

/**
 * Created by Omid Heshmatinia on 19/08/2017.
 */
@Component(modules = HighScoreActivityModule.class)
interface HighScoreComponent {
  void inject(HighScoreActivity activity);
}

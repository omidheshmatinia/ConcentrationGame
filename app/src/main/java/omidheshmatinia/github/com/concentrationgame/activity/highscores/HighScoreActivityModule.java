package omidheshmatinia.github.com.concentrationgame.activity.highscores;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Omid Heshmatinia on 19/08/2017.
 */


@Module
class HighScoreActivityModule {
  HighScoreContract.View view;

  public HighScoreActivityModule(HighScoreContract.View view) {
    this.view = view;
  }

  @Provides
  HighScoreContract.Presenter providePresenter(){
    return new HighScorePresenter(view);
  }
}

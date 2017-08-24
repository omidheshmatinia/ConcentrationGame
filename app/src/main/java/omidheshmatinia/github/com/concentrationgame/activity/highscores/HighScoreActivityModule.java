package omidheshmatinia.github.com.concentrationgame.activity.highscores;

import dagger.Module;
import dagger.Provides;

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

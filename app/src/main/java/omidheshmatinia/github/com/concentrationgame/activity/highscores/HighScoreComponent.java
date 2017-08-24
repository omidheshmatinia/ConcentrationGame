package omidheshmatinia.github.com.concentrationgame.activity.highscores;

import dagger.Component;

@Component(modules = HighScoreActivityModule.class)
interface HighScoreComponent {
  void inject(HighScoreActivity activity);
}

package omidheshmatinia.github.com.concentrationgame.activity.setting;

import dagger.Component;

@Component(modules = SettingModule.class)
interface SettingComponent {
  void inject(SettingActivity activity);
}

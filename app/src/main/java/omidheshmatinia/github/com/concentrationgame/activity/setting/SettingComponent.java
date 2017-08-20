package omidheshmatinia.github.com.concentrationgame.activity.setting;

import dagger.Component;

/**
 * Created by Omid Heshmatinia on 19/08/2017.
 */

@Component(modules = SettingModule.class)
interface SettingComponent {
  void inject(SettingActivity activity);
}

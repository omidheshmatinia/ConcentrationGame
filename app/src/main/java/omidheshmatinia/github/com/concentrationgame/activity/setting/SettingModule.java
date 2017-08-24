package omidheshmatinia.github.com.concentrationgame.activity.setting;

import dagger.Module;
import dagger.Provides;

@Module
class SettingModule {

  SettingContract.View view;

  SettingModule(SettingContract.View view) {
    this.view = view;
  }

  @Provides
  SettingContract.Presenter providePresenter(SettingContract.Model model){
    return new SettingPresenter(view,model);
  }

  @Provides
  SettingContract.Model provideModel(){
    return new SettingModel();
  }
}

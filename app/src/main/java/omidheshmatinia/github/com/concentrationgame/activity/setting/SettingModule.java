package omidheshmatinia.github.com.concentrationgame.activity.setting;

import android.provider.ContactsContract;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Omid Heshmatinia on 19/08/2017.
 */

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

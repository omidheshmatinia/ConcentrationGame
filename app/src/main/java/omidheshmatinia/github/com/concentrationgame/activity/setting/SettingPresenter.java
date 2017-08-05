package omidheshmatinia.github.com.concentrationgame.activity.setting;

import android.widget.Toast;

import omidheshmatinia.github.com.concentrationgame.R;

/**
 * Created by Omid Heshmatinia on 8/5/17.
 */

public class SettingPresenter implements SettingContract.Presenter{

    private SettingContract.View mView;
    private SettingContract.Model mModel;

    public SettingPresenter(SettingContract.View mView) {
        this.mView = mView;
        this.mModel = new SettingModel();
    }

    @Override
    public void viewCreated() {
        mView.setSearchTerm(mModel.getSearchTerm());
        mView.initSpinner(mModel.getAllDifficulties(),mModel.getDifficulty());
    }

    @Override
    public void buttonSaveClicked() {
        if(mView!=null){
            mModel.setDifficultyAtSharedPreference(mView.getDifficulty());
            mModel.setNewSearchTermAtSharedPreference(mView.getSearchTerm());
        }
        mView.toast(R.string.message_setting_saved, Toast.LENGTH_SHORT);
        mView.finishActivity();
    }
}

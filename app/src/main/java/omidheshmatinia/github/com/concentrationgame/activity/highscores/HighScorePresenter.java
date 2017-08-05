package omidheshmatinia.github.com.concentrationgame.activity.highscores;

import omidheshmatinia.github.com.concentrationgame.model.PictureCard;

/**
 * Created by Omid Heshmatinia on 8/5/17.
 */

class HighScorePresenter implements HighScoreContract.Presenter {

    private HighScoreContract.View mView;

    public HighScorePresenter(HighScoreContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void viewCreated() {
        mView.initViewPager();
        mView.initToolbar();
    }

    @Override
    public void onPause() {
//todo
    }

    @Override
    public void onResume() {
//todo
    }

    @Override
    public void viewDetached() {
//todo
    }
}

package omidheshmatinia.github.com.concentrationgame.activity.highscores;

class HighScorePresenter implements HighScoreContract.Presenter {

    private HighScoreContract.View mView;

    HighScorePresenter(HighScoreContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void viewCreated() {
        mView.initViewPager();
        mView.initToolbar();
    }

}

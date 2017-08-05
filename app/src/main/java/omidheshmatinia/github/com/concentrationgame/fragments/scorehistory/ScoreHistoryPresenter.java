package omidheshmatinia.github.com.concentrationgame.fragments.scorehistory;

import android.view.View;

import java.util.List;

import omidheshmatinia.github.com.concentrationgame.model.ScoreHistory;

/**
 * Created by Omid Heshmatinia on 8/5/17.
 */

class ScoreHistoryPresenter implements ScoreHistoryContract.Presenter {

    private ScoreHistoryContract.View mView;
    private ScoreHistoryContract.Model mModel;

    ScoreHistoryPresenter(ScoreHistoryContract.View mView) {
        this.mView = mView;
        mModel = new ScoreHistoryModel();
    }

    @Override
    public void viewCreated() {
        List<ScoreHistory> items= mModel.getHistoryList(mModel.getDifficultyType());
        if(items!=null&& items.size()>0) {
            mView.initList(items);
        } else {
            mView.changeNoRecordVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setDifficultyTypeFromIntent(int difficultyType) {
        mModel.setDifficultyType(difficultyType);
    }
}

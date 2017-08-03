package omidheshmatinia.github.com.concentrationgame.activity.game;

import java.util.List;

import omidheshmatinia.github.com.concentrationgame.PublicEnums;
import omidheshmatinia.github.com.concentrationgame.connection.SearchPictureConnection;
import omidheshmatinia.github.com.concentrationgame.interfaces.webapi.WebApiSearchPictureResponseInterface;
import omidheshmatinia.github.com.concentrationgame.model.PictureCard;
import omidheshmatinia.github.com.concentrationgame.utils.PreferenceHelper;

/**
 * Created by Omid Heshmatinia on 8/3/17.
 */

class GameModel implements GameContract.Model {
    private GameContract.ModelPresenter mModelPresenter;
    private PublicEnums.Difficulty mDifficulty = PublicEnums.Difficulty.Easy;
    private int mScore;
    private int mTime;

    GameModel(GameContract.ModelPresenter modelPresenter) {
        this.mModelPresenter = modelPresenter;
    }

    public PublicEnums.Difficulty getDifficulty() {
        return mDifficulty;
    }

    public void setDifficulty(PublicEnums.Difficulty mDifficulty) {
        this.mDifficulty = mDifficulty;
    }

    public int getScore() {
        return mScore;
    }

    public void setScore(int mScore) {
        this.mScore = mScore;
    }

    public int getTime() {
        return mTime;
    }

    public void add1SecondToTime() {
        this.mTime++;
    }

    @Override
    public void getPicturesFromApi(PublicEnums.Difficulty difficulty,String term) {
        SearchPictureConnection connection=new SearchPictureConnection();
        connection.setWebApiListener(new WebApiSearchPictureResponseInterface() {
            @Override
            public void searchComplete(int maxNumber, List<PictureCard> images) {
                mModelPresenter.searchWebApiResponseIsOk(maxNumber, images);
            }

            @Override
            public void errorHappened(String error) {
                mModelPresenter.searchWebApiErrorHappened(error);
            }
        });
        connection.search(difficulty.getCardNumber(),term);
    }

    @Override
    public String getSearchTermFromSharedPreference() {
        return PreferenceHelper.getInstance().getSearchTerm();
    }
}

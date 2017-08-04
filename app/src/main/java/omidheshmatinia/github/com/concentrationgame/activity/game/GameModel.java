package omidheshmatinia.github.com.concentrationgame.activity.game;

import java.util.ArrayList;
import java.util.List;

import omidheshmatinia.github.com.concentrationgame.PublicEnums;
import omidheshmatinia.github.com.concentrationgame.connection.SearchPictureConnection;
import omidheshmatinia.github.com.concentrationgame.interfaces.webapi.WebApiSearchPictureResponseInterface;
import omidheshmatinia.github.com.concentrationgame.model.PictureCard;
import omidheshmatinia.github.com.concentrationgame.model.ScoreHistory;
import omidheshmatinia.github.com.concentrationgame.utils.PreferenceHelper;

/**
 * Created by Omid Heshmatinia on 8/3/17.
 */

class GameModel implements GameContract.Model {
    private GameContract.ModelPresenter mModelPresenter;
    private PublicEnums.Difficulty mDifficulty = PublicEnums.Difficulty.Easy;
    private int mSuccessfulPairs;
    private int mTime;
    private PictureCard mFirstChosenCard;
    private List<PictureCard> mCardList = new ArrayList<>();
    private Boolean isShowingWrongAnimation = false;

    GameModel(GameContract.ModelPresenter modelPresenter) {
        this.mModelPresenter = modelPresenter;
    }

    public PublicEnums.Difficulty getDifficulty() {
        return mDifficulty;
    }

    public void setDifficulty(PublicEnums.Difficulty mDifficulty) {
        this.mDifficulty = mDifficulty;
    }

    public int getSuccessfulPairs() {
        return mSuccessfulPairs;
    }

    public int getTime() {
        return mTime;
    }

    public void addOneToSuccessfulPairs() {
        this.mSuccessfulPairs++;
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

    @Override
    public void setFirstChosenCard(PictureCard card) {
        mFirstChosenCard = card;
    }

    @Override
    public List<PictureCard> getAllCards() {
        return mCardList;
    }

    @Override
    public void setCardList(List<PictureCard> items) {
        mCardList = items;
    }

    @Override
    public PictureCard getChosenCard() {
        return mFirstChosenCard;
    }

    @Override
    public boolean isWrongAnimationRunning() {
        return isShowingWrongAnimation;
    }

    @Override
    public void setWrongAnimationIsRunning(boolean isRunning) {
        isShowingWrongAnimation = isRunning;
    }

    @Override
    public void addOneSecondToTime() {
        mTime++;
    }

    @Override
    public void saveHistoryItemInDb(ScoreHistory history) {
        //todo
    }

    @Override
    public ScoreHistory getLastHistoryData() {
        //todo
        return null;
    }
}

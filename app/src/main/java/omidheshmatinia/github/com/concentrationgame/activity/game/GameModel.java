package omidheshmatinia.github.com.concentrationgame.activity.game;

import android.os.SystemClock;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import omidheshmatinia.github.com.concentrationgame.PublicEnums;
import omidheshmatinia.github.com.concentrationgame.connection.SearchPictureConnection;
import omidheshmatinia.github.com.concentrationgame.interfaces.webapi.WebApiSearchPictureResponseInterface;
import omidheshmatinia.github.com.concentrationgame.model.PictureCard;
import omidheshmatinia.github.com.concentrationgame.model.ScoreHistory;
import omidheshmatinia.github.com.concentrationgame.repository.ScoreHistoryRepository;
import omidheshmatinia.github.com.concentrationgame.utils.PreferenceHelper;

/**
 * Created by Omid Heshmatinia on 8/3/17.
 */

class GameModel implements GameContract.Model {
    private GameContract.ModelPresenter mModelPresenter;
    private int mSuccessfulPairs;
    private PictureCard mFirstChosenCard;
    private List<PictureCard> mCardList = new ArrayList<>();
    private Boolean isShowingWrongAnimation = false;
    private long mBeginTime;

    GameModel(GameContract.ModelPresenter modelPresenter) {
        this.mModelPresenter = modelPresenter;
    }

    public PublicEnums.Difficulty getDifficulty() {
        return PublicEnums.Difficulty.getDifficultyByType(PreferenceHelper.getInstance().getDifficultyLevel());
    }

    public int getSuccessfulPairs() {
        return mSuccessfulPairs;
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
    public void saveHistoryItemInDb(ScoreHistory history) {
        ScoreHistoryRepository.getInstance().createHistoryItem(history);
    }

    @Override
    public ScoreHistory getLastHistoryData() {
        return  ScoreHistoryRepository.getInstance().getLastItem();
    }

    @Override
    public void updateHistoryData(ScoreHistory historyData) {
        ScoreHistoryRepository.getInstance().updateHistoryItem(historyData);
    }

    @Override
    public void resetBeginTime() {
        mBeginTime = SystemClock.elapsedRealtime();
    }

    @Override
    public long getBeginTime() {
        return mBeginTime;
    }
}

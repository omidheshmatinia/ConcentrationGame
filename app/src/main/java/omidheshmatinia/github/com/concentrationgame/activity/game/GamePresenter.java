package omidheshmatinia.github.com.concentrationgame.activity.game;

import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import omidheshmatinia.github.com.concentrationgame.model.PictureCard;
import omidheshmatinia.github.com.concentrationgame.model.ScoreHistory;

class GamePresenter implements GameContract.Presenter,GameContract.ModelPresenter {

    private GameContract.View mView;
    private GameContract.Model mModel;

    GamePresenter(GameContract.View mView) {
        this.mView = mView;
        this.mModel = new GameModel(this);
    }

    @Override
    public void viewCreated() {
       mModel.getPicturesFromApi(mModel.getDifficulty(),mModel.getSearchTermFromSharedPreference());
    }

    @Override
    public void listCardItemClicked(final PictureCard item) {
        if(mView!=null){
            if(mModel.isWrongAnimationRunning()){
                // we should wait till animation end
                return;
            }
            if(item.isRevealed()){
                //do nothing card is revealed right now
                return;
            }
            if(mModel.getChosenCard()==null) {
                // user didn't choose the first card
                item.setRevealed(true);
                mModel.setFirstChosenCard(item);
                flipACard(item);
            } else {
                // user choose the second card so we should check whether both of them are the same
                final PictureCard chosenCard= mModel.getChosenCard();
                if(item.getId()==chosenCard.getId() && item.getPosition()!=chosenCard.getPosition()){
                    //both card are the same. Bingo. You get the score.
                    item.setRevealed(true);
                    flipACard(item);
                    mModel.addOneToSuccessfulPairs();
                    mModel.setFirstChosenCard(null);
                    if(mModel.getSuccessfulPairs() == mModel.getDifficulty().getCardNumber()){
                        mView.changeChronometerStatus(false);
                        long timeElapsed = SystemClock.elapsedRealtime() - mModel.getBeginTime();
                        ScoreHistory history= new ScoreHistory("UNKNOWN",timeElapsed,mModel.getDifficulty().getType(),System.currentTimeMillis());
                        mModel.saveHistoryItemInDb(history);
                        mView.showSubmitView();
                    }
                } else {
                    //items are not the same, return both card to the default position
                    mModel.setWrongAnimationIsRunning(true);
                    mView.changeWrongViewVisibility(View.VISIBLE);
                    mView.animateWrongView();
                    mModel.setFirstChosenCard(null);
                    flipACard(item);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mView.changeWrongViewVisibility(View.GONE);
                            item.setRevealed(false);
                            chosenCard.setRevealed(false);
                            flipACard(chosenCard);
                            flipACard(item);
                            mModel.setWrongAnimationIsRunning(false);
                        }
                    },750);
                }
            }
        }
    }

    @Override
    public void submitRecordClicked() {
        if(mView!=null){
            String name = mView.getName();
            ScoreHistory historyData= mModel.getLastHistoryData();
            historyData.setUserName(name);
            mModel.updateHistoryData(historyData);
            mView.finishActivity();
        }
    }

    private void flipACard(PictureCard item){
        List<PictureCard> items = mModel.getAllCards();
        int index = items.indexOf(item);
        if(index==-1){
            mView.toast("Error in Getting item Info",Toast.LENGTH_SHORT);
            return;
        }
        mView.flipCard(index);
    }

    @Override
    public void searchWebApiResponseIsOk(int maxItems, List<PictureCard> items) {
        if(mView!=null){
            for(PictureCard item:new ArrayList<>(items)){
                items.add(new PictureCard(item.getImageUrl(),item.getId()));
            }
            Collections.shuffle(items);
            for(int i=0;i<items.size();i++)
                items.get(i).setPosition(i+1);
            mModel.setCardList(items);
            mView.initList(items,mModel.getDifficulty().getColumnSize());

            mModel.resetBeginTime();
            mView.setBeginTimeOFChronometer(SystemClock.elapsedRealtime());
            mView.changeChronometerStatus(true);
            mView.changeProgressbarVisibility(View.GONE);
        }
    }

    @Override
    public void searchWebApiErrorHappened(String error) {
        mView.toast(error, Toast.LENGTH_LONG);
    }

}

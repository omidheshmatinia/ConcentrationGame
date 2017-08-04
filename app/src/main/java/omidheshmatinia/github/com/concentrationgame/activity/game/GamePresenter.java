package omidheshmatinia.github.com.concentrationgame.activity.game;

import android.os.Handler;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.AlphaAnimation;
import android.widget.Toast;
import android.widget.ViewAnimator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import eu.davidea.flipview.FlipView;
import omidheshmatinia.github.com.concentrationgame.base.MasterApplication;
import omidheshmatinia.github.com.concentrationgame.model.PictureCard;
import omidheshmatinia.github.com.concentrationgame.model.ScoreHistory;

/**
 * Created by Omid Heshmatinia on 8/3/17.
 */

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

    @Override
    public void listCardItemClicked(final PictureCard item) {
        //todo
        if(mView!=null){
            if(mModel.isWrongAnimationRunning()){
                // we should wait till animation end
                return;
            }
            if(item.isRevealed()){
                //do nothing card is reveled right now
                return;
            }
            if(mModel.getChosenCard()==null) {
                // user didn't choose the first card
                item.setRevealed(true);
                mModel.setFirstChosenCard(item);
                flipACard(item);
//            }
//            else if(item.equals(mModel.getChosenCard())){
//                mView.toast("Please choose your second card",Toast.LENGTH_SHORT);
//                return;
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
                        ScoreHistory history= new ScoreHistory("UNKNOWN",mModel.getTime(),mModel.getDifficulty().getType(),System.currentTimeMillis());
                        mModel.saveHistoryItemInDb(history);
                        mView.showSubmitView();
                        //todo game finished calculate Score
                    }
                } else {
                    mModel.setWrongAnimationIsRunning(true);
                    //items are not the same, return both card to the default position
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
                    },1000);
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
            mModel.saveHistoryItemInDb(historyData);
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
        //init list
        //begin timer
        if(mView!=null){
            for(PictureCard item:new ArrayList<>(items)){
                items.add(new PictureCard(item.getImageUrl(),item.getId()));
            }
            Collections.shuffle(items);
            for(int i=0;i<items.size();i++)
                items.get(i).setPosition(i+1);
            mModel.setCardList(items);
            mView.initList(items,mModel.getDifficulty().getColumnSize());

            mView.initChronometer();
            mView.changeChronometerStatus(true);
        }
    }

    @Override
    public void searchWebApiErrorHappened(String error) {
        mView.toast(error, Toast.LENGTH_LONG);
    }
    @Override
    public void oneSecondPassed() {
        mModel.addOneSecondToTime();
    }


}

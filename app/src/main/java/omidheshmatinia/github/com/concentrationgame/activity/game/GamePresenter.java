package omidheshmatinia.github.com.concentrationgame.activity.game;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import omidheshmatinia.github.com.concentrationgame.model.PictureCard;

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
    public void listCardItemClicked(PictureCard item) {
        //todo
        if(mView!=null){
            mView.toast(item.getId()+"",Toast.LENGTH_SHORT);
        }
    }

    @Override
    public void searchWebApiResponseIsOk(int maxItems, List<PictureCard> items) {
        //init list
        //begin timer

        if(mView!=null){
            items.addAll(items);
            Collections.shuffle(items);
            mView.initList(items,mModel.getDifficulty().getColumnSize());
        }
    }

    @Override
    public void searchWebApiErrorHappened(String error) {
        mView.toast(error, Toast.LENGTH_LONG);
    }
}

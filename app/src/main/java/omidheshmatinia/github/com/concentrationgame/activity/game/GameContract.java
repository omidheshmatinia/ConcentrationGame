package omidheshmatinia.github.com.concentrationgame.activity.game;

import java.util.List;

import omidheshmatinia.github.com.concentrationgame.PublicEnums;
import omidheshmatinia.github.com.concentrationgame.interfaces.MasterActivityViewInterface;
import omidheshmatinia.github.com.concentrationgame.model.PictureCard;

/**
 * Created by Omid Heshmatinia on 8/3/17.
 */

class GameContract {

    interface View extends MasterActivityViewInterface{

        void initList(List<PictureCard> items, int columnSize);
    }

    interface Presenter{

        void viewCreated();

        void onPause();

        void onResume();

        void viewDetached();

        void listCardItemClicked(PictureCard item);
    }

    interface Model{
        PublicEnums.Difficulty getDifficulty();
        void setDifficulty(PublicEnums.Difficulty difficulty);
        int getScore();
        void setScore(int score);
        int getTime();
        void add1SecondToTime();
        void getPicturesFromApi(PublicEnums.Difficulty difficulty,String term);
        String getSearchTermFromSharedPreference();
    }

    interface ModelPresenter{
        void searchWebApiResponseIsOk(int maxItems, List<PictureCard> items);
        void searchWebApiErrorHappened(String error);
    }
}

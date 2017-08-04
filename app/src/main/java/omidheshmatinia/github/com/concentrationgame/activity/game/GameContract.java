package omidheshmatinia.github.com.concentrationgame.activity.game;

import java.util.List;

import eu.davidea.flipview.FlipView;
import omidheshmatinia.github.com.concentrationgame.PublicEnums;
import omidheshmatinia.github.com.concentrationgame.interfaces.MasterActivityViewInterface;
import omidheshmatinia.github.com.concentrationgame.model.PictureCard;
import omidheshmatinia.github.com.concentrationgame.model.ScoreHistory;

/**
 * Created by Omid Heshmatinia on 8/3/17.
 */

class GameContract {

    interface View extends MasterActivityViewInterface{

        void initList(List<PictureCard> items, int columnSize);

        void flipCard(int position);
        void changeWrongViewVisibility(int visibility);

        void animateWrongView();

        void changeChronometerStatus(boolean start);

        void initChronometer();

        void showSubmitView();
        String getName();
    }

    interface Presenter{

        void viewCreated();

        void onPause();

        void onResume();

        void viewDetached();
        void oneSecondPassed();
        void listCardItemClicked(PictureCard item);

        void submitRecordClicked();
    }

    interface Model{
        PublicEnums.Difficulty getDifficulty();
        void setDifficulty(PublicEnums.Difficulty difficulty);
        int getSuccessfulPairs();
        int getTime();
        void addOneToSuccessfulPairs();
        void getPicturesFromApi(PublicEnums.Difficulty difficulty,String term);
        String getSearchTermFromSharedPreference();
        void setFirstChosenCard(PictureCard card);
        List<PictureCard> getAllCards();
        void setCardList(List<PictureCard> items);
        PictureCard getChosenCard();
        boolean isWrongAnimationRunning();
        void setWrongAnimationIsRunning(boolean isRunning);
        void addOneSecondToTime();
        void saveHistoryItemInDb(ScoreHistory history);
        ScoreHistory getLastHistoryData();
    }

    interface ModelPresenter{
        void searchWebApiResponseIsOk(int maxItems, List<PictureCard> items);
        void searchWebApiErrorHappened(String error);
    }
}

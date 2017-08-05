package omidheshmatinia.github.com.concentrationgame.activity.game;

import java.util.List;

import omidheshmatinia.github.com.concentrationgame.PublicEnums;
import omidheshmatinia.github.com.concentrationgame.interfaces.MasterActivityViewInterface;
import omidheshmatinia.github.com.concentrationgame.model.PictureCard;
import omidheshmatinia.github.com.concentrationgame.model.ScoreHistory;

class GameContract {

    interface View extends MasterActivityViewInterface{
        /**
         * init {@link GameActivity#mCardList} and fill it with cards
         * @param items
         * @param columnSize
         */
        void initList(List<PictureCard> items, int columnSize);

        /**
         * flip chosen card and show the other side
         * @param position
         */
        void flipCard(int position);

        /**
         * change {@link GameActivity#wrongView} visibility
         * @param visibility
         */
        void changeWrongViewVisibility(int visibility);

        /**
         * begin animation the red view as user choose the wrong cards
         */
        void animateWrongView();

        /**
         * start and stop chronometer
         * @param start
         */
        void changeChronometerStatus(boolean start);

        /**
         * Show Submit name view after user finished the game
         */
        void showSubmitView();

        /**
         * @return submitted name from {@link GameActivity#editTextName}
         */
        String getName();

        /**
         * Set begin time of chronometer
         * @param beginTime
         */
        void setBeginTimeOFChronometer(long beginTime);

        /**
         * change visibility of {@link GameActivity#progressBar}
         * @param visibility
         */
        void changeProgressbarVisibility(int visibility);
    }

    interface Presenter{
        /**
         * inform about finishing creation of view.
         */
        void viewCreated();

        /**
         * called when user click on a card
         * @param item
         */
        void listCardItemClicked(PictureCard item);

        /**
         * called when user enter his name and click save for submitting information in db
         */
        void submitRecordClicked();
    }

    interface Model{
        /**
         * @return currently chosen difficulty level
         */
        PublicEnums.Difficulty getDifficulty();

        /**
         * @return number of successful pairs
         */
        int getSuccessfulPairs();

        /**
         * When user choose two same card, this will be called
         */
        void addOneToSuccessfulPairs();

        /**
         * get image from 500px api
         * @param difficulty
         * @param term
         */
        void getPicturesFromApi(PublicEnums.Difficulty difficulty,String term);

        /**
         * @return currently chosen search term
         */
        String getSearchTermFromSharedPreference();

        /**
         * used to store information of first chosen card for later comparison
         * @param card
         */
        void setFirstChosenCard(PictureCard card);

        /**
         * @return all cards which are retrieved from server
         */
        List<PictureCard> getAllCards();

        /**
         * save all cards which are retrieved from server which get doubled and shuffled
         * @param items
         */
        void setCardList(List<PictureCard> items);

        /**
         * @return first chosen card info
         */
        PictureCard getChosenCard();

        /**
         * @return whether animation is running
         */
        boolean isWrongAnimationRunning();

        /**
         * set running status of red screen animation
         * @param isRunning
         */
        void setWrongAnimationIsRunning(boolean isRunning);

        /**
         * save history data to database
         * @param history
         */
        void saveHistoryItemInDb(ScoreHistory history);

        /**
         * @return last item to update its name
         */
        ScoreHistory getLastHistoryData();

        /**
         * Update the object in db
         * @param historyData
         */
        void updateHistoryData(ScoreHistory historyData);

        /**
         * used after getting data from server and as game begin
         */
        void resetBeginTime();

        /**
         * @return begin time of game
         */
        long getBeginTime();
    }

    interface ModelPresenter{
        /**
         * return successful result of webApi to presenter
         */
        void searchWebApiResponseIsOk(int maxItems, List<PictureCard> items);
        /**
         * return error of webApi to presenter
         */
        void searchWebApiErrorHappened(String error);
    }
}

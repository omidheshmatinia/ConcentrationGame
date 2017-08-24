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
         * @param items cards
         * @param columnSize size of columns
         */
        void initList(List<PictureCard> items, int columnSize);

        /**
         * flip chosen card and show the other side
         * @param position the card number which should flip
         */
        void flipCard(int position);

        /**
         * change {@link GameActivity#wrongView} visibility
         * @param visibility desired visibility
         */
        void changeWrongViewVisibility(int visibility);

        /**
         * begin animation the red view as user choose the wrong cards
         */
        void animateWrongView();

        /**
         * start and stop chronometer
         * @param start wheter chronometer should start or stop
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
         */
        void setBeginTimeOFChronometer(long beginTime);

        /**
         * change visibility of {@link GameActivity#progressBar}
         */
        void changeProgressbarVisibility(int visibility);
    }

    interface Presenter{
        /**
         * inform about finishing creation of view.
         */
        void viewCreated();

        /**
         * inform about detaching of view.
         */
        void viewDetached();

        /**
         * called when user click on a card
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
         * @param difficulty indicate number of cards which should be retrieved from api
         * @param term search parameter
         */
        void getPicturesFromApi(PublicEnums.Difficulty difficulty,String term);

        /**
         * @return currently chosen search term
         */
        String getSearchTermFromSharedPreference();

        /**
         * used to store information of first chosen card for later comparison
         */
        void setFirstChosenCard(PictureCard card);

        /**
         * @return all cards which are retrieved from server
         */
        List<PictureCard> getAllCards();

        /**
         * save all cards which are retrieved from server which get doubled and shuffled
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
         */
        void setWrongAnimationIsRunning(boolean isRunning);

        /**
         * save history data to database
         */
        void saveHistoryItemInDb(ScoreHistory history);

        /**
         * @return last item to update its name
         */
        ScoreHistory getLastHistoryData();

        /**
         * Update the object in db
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

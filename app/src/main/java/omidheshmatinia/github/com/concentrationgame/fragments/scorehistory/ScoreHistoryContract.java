package omidheshmatinia.github.com.concentrationgame.fragments.scorehistory;

import java.util.List;

import omidheshmatinia.github.com.concentrationgame.PublicEnums;
import omidheshmatinia.github.com.concentrationgame.model.ScoreHistory;

class ScoreHistoryContract {
    interface View{

        /**
         * Show History items in list
         * @param items
         */
        void initList(List<ScoreHistory> items);

        /**
         * Change visibility of {@link ScoreHistoryFragment#noRecordView}.
         * Used When there is no data to show.
         * @param visibility
         */
        void changeNoRecordVisibility(int visibility);
    }

    interface Presenter{
        /**
         * inform about finishing creation of view.
         */
        void viewCreated();

        /**
         * get difficulty type which passed by intent and send it to presenter
         * @return
         */
        void setDifficultyTypeFromIntent(int difficultyType);
    }

    interface Model{
        /**
         * store difficulty type passed by intent from presenter
         * @param type
         */
        void setDifficultyType(int type);

        /**
         * get difficulty type which passed by intent
         * @return
         */
        PublicEnums.Difficulty getDifficultyType();

        /**
         * get list of {@link ScoreHistory} items related to current difficulty type
         * @param difficulty
         * @return List of {@link ScoreHistory}
         */
        List<ScoreHistory> getHistoryList(PublicEnums.Difficulty difficulty);
    }

}

package omidheshmatinia.github.com.concentrationgame.fragments.scorehistory;

import java.util.List;

import omidheshmatinia.github.com.concentrationgame.PublicEnums;
import omidheshmatinia.github.com.concentrationgame.model.ScoreHistory;

class ScoreHistoryContract {
    interface View{

        /**
         * Show History items in list
         */
        void initList(List<ScoreHistory> items);

        /**
         * Change visibility of {@link ScoreHistoryFragment#noRecordView}.
         * Used When there is no data to show.
         * @param visibility desired Visibility
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
         */
        void setDifficultyTypeFromIntent(int difficultyType);

        /**
         * inform about detaching view to do clean up jobs
         */
        void viewDetached();
    }

    interface Model{
        /**
         * store difficulty type passed by intent from presenter
         */
        void setDifficultyType(int type);

        /**
         * get difficulty type which passed by intent
         */
        PublicEnums.Difficulty getDifficultyType();

        /**
         * get list of {@link ScoreHistory} items related to current difficulty type
         * @return List of {@link ScoreHistory} for that difficulty
         */
        List<ScoreHistory> getHistoryList(PublicEnums.Difficulty difficulty);
    }

}

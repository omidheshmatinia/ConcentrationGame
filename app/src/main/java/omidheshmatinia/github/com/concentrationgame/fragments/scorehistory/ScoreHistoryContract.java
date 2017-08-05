package omidheshmatinia.github.com.concentrationgame.fragments.scorehistory;

import java.util.List;

import omidheshmatinia.github.com.concentrationgame.PublicEnums;
import omidheshmatinia.github.com.concentrationgame.model.ScoreHistory;

/**
 * Created by Omid Heshmatinia on 8/5/17.
 */

class ScoreHistoryContract {
    interface View{
        void initList(List<ScoreHistory> items);
        void changeNoRecordVisibility(int visibility);
    }

    interface Presenter{
        void viewCreated();
        void setDifficultyTypeFromIntent(int difficultyType);
    }

    interface Model{
        void setDifficultyType(int type);
        PublicEnums.Difficulty getDifficultyType();
        List<ScoreHistory> getHistoryList(PublicEnums.Difficulty difficulty);
    }

}

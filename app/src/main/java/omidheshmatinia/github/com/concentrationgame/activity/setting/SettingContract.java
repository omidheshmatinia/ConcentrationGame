package omidheshmatinia.github.com.concentrationgame.activity.setting;

import java.util.List;

import omidheshmatinia.github.com.concentrationgame.PublicEnums;
import omidheshmatinia.github.com.concentrationgame.interfaces.MasterActivityViewInterface;

/**
 * Created by Omid Heshmatinia on 8/5/17.
 */

public class SettingContract {

    interface View extends MasterActivityViewInterface{
        String getSearchTerm();
        PublicEnums.Difficulty getDifficulty();
        void initSpinner(List<PublicEnums.Difficulty> types,PublicEnums.Difficulty currentDifficulty);
        void setSearchTerm(String term);
    }

    interface Presenter{
        void viewCreated();
        void buttonSaveClicked();
    }

    interface Model{
        PublicEnums.Difficulty getDifficulty();
        List<PublicEnums.Difficulty> getAllDifficulties();
        void setDifficultyAtSharedPreference(PublicEnums.Difficulty type);
        void setNewSearchTermAtSharedPreference(String term);
        String getSearchTerm();
    }
}

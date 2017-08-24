package omidheshmatinia.github.com.concentrationgame.activity.setting;

import java.util.List;

import omidheshmatinia.github.com.concentrationgame.PublicEnums;
import omidheshmatinia.github.com.concentrationgame.interfaces.MasterActivityViewInterface;

class SettingContract {

    interface View extends MasterActivityViewInterface{
        /**
         * get search term from {@link SettingActivity#etSearchTerm}
         */
        String getSearchTerm();

        /**
         * get difficulty level from {@link SettingActivity#spinnerDifficulty}
         */
        PublicEnums.Difficulty getDifficulty();

        /**
         * init {@link SettingActivity#spinnerDifficulty} and also select currently chosen difficulty
         */
        void initSpinner(List<PublicEnums.Difficulty> types,PublicEnums.Difficulty currentDifficulty);

        /**
         * fill {@link SettingActivity#etSearchTerm} by currently search term
         */
        void setSearchTerm(String term);
    }

    interface Presenter{
        /**
         * inform about finishing creation of view.
         */
        void viewCreated();

        /**
         * called whenever user click on save button
         */
        void buttonSaveClicked();
    }

    interface Model{

        /**
         * get currently chosen difficulty
         */
        PublicEnums.Difficulty getDifficulty();


        /**
         * get list of all difficulties
         */
        List<PublicEnums.Difficulty> getAllDifficulties();

        /**
         * save chosen difficulty to shared preference
         */
        void setDifficultyAtSharedPreference(PublicEnums.Difficulty type);


        /**
         * save chosen search term to shared preference
         */
        void setNewSearchTermAtSharedPreference(String term);

        /**
         * get currently chosen search term
         */
        String getSearchTerm();
    }
}

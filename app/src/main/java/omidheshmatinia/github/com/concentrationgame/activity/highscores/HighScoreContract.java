package omidheshmatinia.github.com.concentrationgame.activity.highscores;

import java.util.List;

import omidheshmatinia.github.com.concentrationgame.PublicEnums;
import omidheshmatinia.github.com.concentrationgame.interfaces.MasterActivityViewInterface;
import omidheshmatinia.github.com.concentrationgame.model.PictureCard;
import omidheshmatinia.github.com.concentrationgame.model.ScoreHistory;

/**
 * Created by Omid Heshmatinia on 8/5/17.
 */

class HighScoreContract {
    interface View extends MasterActivityViewInterface {
        void initViewPager();
        void initToolbar();
    }

    interface Presenter{
        void viewCreated();
        void onPause();
        void onResume();
        void viewDetached();
    }

}

package omidheshmatinia.github.com.concentrationgame.activity.highscores;

import omidheshmatinia.github.com.concentrationgame.interfaces.MasterActivityViewInterface;

class HighScoreContract {

    interface View extends MasterActivityViewInterface {
        /**
         *  init view pager and tab to show history items
         */
        void initViewPager();

        /**
         * initializing toolbar nad back button
         */
        void initToolbar();
    }

    interface Presenter{
        /**
         * inform about finishing creation of view.
         */
        void viewCreated();
    }

}

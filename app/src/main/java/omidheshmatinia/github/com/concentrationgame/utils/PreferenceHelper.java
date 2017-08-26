package omidheshmatinia.github.com.concentrationgame.utils;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;
import omidheshmatinia.github.com.concentrationgame.PublicEnums;
import omidheshmatinia.github.com.concentrationgame.base.MasterApplication;

/**
 * This class is a helper for anything related to Shared Preference
 * @author omid
 */

public class PreferenceHelper {
    private SharedPreferences mSharedPreferences;

    private class Parameters{
        private final static String SEARCH_TERM = "searchTerm";
        private final static String DIFFICULTY_LEVEL = "difficultyLevel";
    }

    //<editor-fold desc="Singleton Part">
    private final static class Instance {
        private final static PreferenceHelper ITEM = new PreferenceHelper (MasterApplication.getInstance());
    }

    @Inject
    public PreferenceHelper(Context context) {
        this.mSharedPreferences = context.getSharedPreferences("ConcentrationGame",0);
    }

    public static PreferenceHelper getInstance(){
        return Instance.ITEM;
    }
    //</editor-fold>


    /**
     * @return desired search term
     */
    public String getSearchTerm() {
        return mSharedPreferences.getString(Parameters.SEARCH_TERM,"kitten");
    }

    /**
     * save chosen search term at Shared Preferences
     * @param term chosen term
     */
    public void setSearchTerm(String term){
        mSharedPreferences.edit().putString(Parameters.SEARCH_TERM,term).apply();
    }


    /**
     * @return chosen difficulty level
     */
    public int getDifficultyLevel() {
        return mSharedPreferences.getInt(Parameters.DIFFICULTY_LEVEL, PublicEnums.Difficulty.Easy.getType());
    }

    /**
     * save chosen difficulty Type at Shared Preferences
     * @param type chosen type
     */
    public void setDifficultyLevel(int type){
        mSharedPreferences.edit().putInt(Parameters.DIFFICULTY_LEVEL,type).apply();
    }
}

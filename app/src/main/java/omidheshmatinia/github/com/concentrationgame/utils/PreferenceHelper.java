package omidheshmatinia.github.com.concentrationgame.utils;

import android.app.Application;
import android.content.SharedPreferences;

import omidheshmatinia.github.com.concentrationgame.PublicEnums;
import omidheshmatinia.github.com.concentrationgame.base.MasterApplication;

/**
 * Created by Omid Heshmatinia on 8/3/17.
 */

public class PreferenceHelper {
    private SharedPreferences mSharedPreferences;

    private class Parameters{
        private final static String SEARCH_TERM = "searchTerm";
        private final static String DIFFICULTY_LEVEL = "difficultyLevel";
    }

    private final static class Instance {
        private final static PreferenceHelper ITEM = new PreferenceHelper (MasterApplication.getInstance());
    }

    private PreferenceHelper(Application context) {
        this.mSharedPreferences = context.getSharedPreferences("ConcentrationGame",0);
    }

    public static PreferenceHelper getInstance(){
        return Instance.ITEM;
    }


    public String getSearchTerm() {
        return mSharedPreferences.getString(Parameters.SEARCH_TERM,"kitten");
    }

    public void setSearchTerm(String term){
        mSharedPreferences.edit().putString(Parameters.SEARCH_TERM,term).commit();
    }


    public int getDifficultyLevel() {
        return mSharedPreferences.getInt(Parameters.DIFFICULTY_LEVEL, PublicEnums.Difficulty.Easy.getType());
    }

    public void setDifficultyLevel(int type){
        mSharedPreferences.edit().putInt(Parameters.DIFFICULTY_LEVEL,type).commit();
    }
}

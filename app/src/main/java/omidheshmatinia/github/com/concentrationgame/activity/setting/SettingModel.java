package omidheshmatinia.github.com.concentrationgame.activity.setting;

import java.util.Arrays;
import java.util.List;

import omidheshmatinia.github.com.concentrationgame.PublicEnums;
import omidheshmatinia.github.com.concentrationgame.utils.PreferenceHelper;

/**
 * Created by Omid Heshmatinia on 8/5/17.
 */

public class SettingModel implements SettingContract.Model {

    @Override
    public PublicEnums.Difficulty getDifficulty() {
        return PublicEnums.Difficulty.getDifficultyByType(PreferenceHelper.getInstance().getDifficultyLevel());
    }

    @Override
    public List<PublicEnums.Difficulty> getAllDifficulties() {
        return Arrays.asList(PublicEnums.Difficulty.values());
    }

    @Override
    public void setDifficultyAtSharedPreference(PublicEnums.Difficulty type) {
        PreferenceHelper.getInstance().setDifficultyLevel(type.getType());
    }

    @Override
    public void setNewSearchTermAtSharedPreference(String term) {
        PreferenceHelper.getInstance().setSearchTerm(term);
    }

    @Override
    public String getSearchTerm() {
        return PreferenceHelper.getInstance().getSearchTerm();
    }
}

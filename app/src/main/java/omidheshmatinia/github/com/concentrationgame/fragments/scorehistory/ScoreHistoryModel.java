package omidheshmatinia.github.com.concentrationgame.fragments.scorehistory;

import java.util.List;

import omidheshmatinia.github.com.concentrationgame.PublicEnums;
import omidheshmatinia.github.com.concentrationgame.model.ScoreHistory;
import omidheshmatinia.github.com.concentrationgame.repository.ScoreHistoryRepository;

/**
 * Created by Omid Heshmatinia on 8/5/17.
 */

class ScoreHistoryModel implements ScoreHistoryContract.Model {
    private int mDifficultyType ;
    @Override
    public void setDifficultyType(int type) {
        mDifficultyType = type;
    }

    @Override
    public PublicEnums.Difficulty getDifficultyType() {
        return PublicEnums.Difficulty.getDifficultyByType(mDifficultyType);
    }

    @Override
    public List<ScoreHistory> getHistoryList(PublicEnums.Difficulty difficulty) {
        return ScoreHistoryRepository.getInstance().getSortedHistoryItems(difficulty.getType());
    }
}

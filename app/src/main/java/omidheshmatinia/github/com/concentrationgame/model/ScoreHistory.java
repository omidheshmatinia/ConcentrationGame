package omidheshmatinia.github.com.concentrationgame.model;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by Omid Heshmatinia on 8/4/17.
 */

public class ScoreHistory {
    public final static String DIFFICULTY_LEVEL = "difficultyLevel";
    public final static String MILLI_SECONDS = "milliSeconds";
    public final static String ID = "_id";
    @DatabaseField(generatedId = true)
    private long _id;
    @DatabaseField
    private String userName;
    @DatabaseField
    private long milliSeconds;
    @DatabaseField
    private int difficultyLevel;
    @DatabaseField
    private long recordDate;

    public ScoreHistory(){}
    public ScoreHistory(String userName, long milliSeconds, int difficultyLevel, long recordDate) {
        this.userName = userName;
        this.milliSeconds = milliSeconds;
        this.difficultyLevel = difficultyLevel;
        this.recordDate = recordDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getMilliSeconds() {
        return milliSeconds;
    }

    public void setMilliSeconds(long seconds) {
        this.milliSeconds = seconds;
    }

    public int getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(int difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public long getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(long recordDate) {
        this.recordDate = recordDate;
    }
}

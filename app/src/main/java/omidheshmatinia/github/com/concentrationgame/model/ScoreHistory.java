package omidheshmatinia.github.com.concentrationgame.model;

/**
 * Created by Omid Heshmatinia on 8/4/17.
 */

public class ScoreHistory {
    private String userName;
    private int seconds;
    private int difficultyLevel;
    private long recordDate;

    public ScoreHistory(String userName, int seconds, int dificultyLevel, long recordDate) {
        this.userName = userName;
        this.seconds = seconds;
        this.difficultyLevel = dificultyLevel;
        this.recordDate = recordDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
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

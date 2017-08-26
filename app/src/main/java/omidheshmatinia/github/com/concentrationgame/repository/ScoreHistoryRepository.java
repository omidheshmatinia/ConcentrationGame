package omidheshmatinia.github.com.concentrationgame.repository;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.dao.Dao;

import java.util.ArrayList;
import java.util.List;

import omidheshmatinia.github.com.concentrationgame.base.MasterApplication;
import omidheshmatinia.github.com.concentrationgame.base.MasterDatabase;
import omidheshmatinia.github.com.concentrationgame.model.ScoreHistory;

/**
 * Anything related to ScoreHistory data retrieval and saving in db is done by this class
 * @param <T>
 */

public class ScoreHistoryRepository <T extends ScoreHistory> {

    private DatabaseHelper mDbHelper;
    private ScoreHistoryRepository(){
        mDbHelper = new DatabaseHelper(
            MasterApplication.getInstance(),
            (Class<T>)ScoreHistory.class
        );
    }

    private static class Instance{
        private final static ScoreHistoryRepository<ScoreHistory> Item = new ScoreHistoryRepository<>();
    }

    public static ScoreHistoryRepository<ScoreHistory> getInstance(){
        return Instance.Item;
    }

    public List<T> getSortedHistoryItems(int difficultyType){
        return mDbHelper.getAllHistoryForADifficultySortedByScore(difficultyType);
    }

    public int createHistoryItem(T item){
        return mDbHelper.create(item);
    }

    public int updateHistoryItem(T item){
        return mDbHelper.update(item);
    }

    public List<T> getAllHistoryForADifficulty(int difficultyType){
        return mDbHelper.getListByField(difficultyType,T.DIFFICULTY_LEVEL);
    }

    public T getLastItem(){
        return mDbHelper.getLast();
    }

    private class DatabaseHelper extends MasterDatabase<T>{

         DatabaseHelper(Context context , Class<T> type) {
            super(context);
            MODEL_CLASS = type;
        }

        List<T> getAllHistoryForADifficultySortedByScore(int difficultyType){
            try{
                Dao<T,Long> localDao=getDao(MODEL_CLASS);
                return localDao.query(localDao.queryBuilder()
                        .orderBy(T.MILLI_SECONDS,true)
                        .where()
                        .eq(T.DIFFICULTY_LEVEL,difficultyType)
                        .prepare());
            }catch (Exception ignore){
                Log.d(MODEL_CLASS.getName(), ignore.getMessage());
                return new ArrayList<>();
            }
        }

        public T getLast() {
            try{
                Dao<T,Long> localDao=getDao(MODEL_CLASS);
                List<T> items =  localDao.query(localDao.queryBuilder()
                        .orderBy(T.ID,false)
                        .prepare());
                if(items != null && items.size()>0)
                    return items.get(0);
                else
                    return null;
            }catch (Exception ignore){
                Log.d(MODEL_CLASS.getName(), ignore.getMessage());
                return null;
            }
        }
    }

}

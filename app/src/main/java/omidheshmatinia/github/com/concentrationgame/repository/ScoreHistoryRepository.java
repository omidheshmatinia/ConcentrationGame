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
 * Created by Omid Heshmatinia on 8/5/17.
 */

public class ScoreHistoryRepository <T extends ScoreHistory> {

    private DatabaseHelper mDbHelper;
    private ScoreHistoryRepository(){
        mDbHelper = new DatabaseHelper(MasterApplication.getInstance());
    }

    private static class Instance{
        private final static ScoreHistoryRepository Item = new ScoreHistoryRepository();
    }

    public static ScoreHistoryRepository getInstance(){
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
         DatabaseHelper(Context context) {
            super(context);
            MODEL_CLASS = ScoreHistory.class;
        }

        List<T> getAllHistoryForADifficultySortedByScore(int difficultyType){
            try{
                Dao localDao=getDao(MODEL_CLASS);
                return localDao.query(localDao.queryBuilder()
                        .orderBy(T.MILLI_SECONDS,true)
                        .where()
                        .eq(T.DIFFICULTY_LEVEL,difficultyType)
                        .prepare());
            }catch (Exception ignore){
                Log.d(MODEL_CLASS.getName(), ignore.getMessage());
                return new ArrayList();
            }
        }

        public T getLast() {
            try{
                Dao localDao=getDao(MODEL_CLASS);
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

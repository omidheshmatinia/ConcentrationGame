package omidheshmatinia.github.com.concentrationgame.base;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.util.ArrayList;
import java.util.List;

import omidheshmatinia.github.com.concentrationgame.model.ScoreHistory;

public class MasterDatabase<T> extends OrmLiteSqliteOpenHelper {
    protected Class MODEL_CLASS;
    private final static String DB_NAME="ConcentrationGame";
    private final static int    DB_VERSION=1;

    protected MasterDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
            try{

                TableUtils.createTable(connectionSource, ScoreHistory.class);

            } catch (Exception e){
                Log.i("DB","errror "+e.getMessage());
            }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {

    }

    public int create (T data) {
        int count = 0;
        try {
            Dao connection = getDao(MODEL_CLASS);
            count = connection.create(data);
        } catch (Exception ignore) {
            Log.d(MODEL_CLASS.getClass().getName(), ignore.getMessage());
        }
        return count;
    }

    public T getFirst () {
        try {
            Dao localConnection = getDao(MODEL_CLASS);
            return (T)localConnection.queryForFirst(localConnection.queryBuilder().prepare());
        } catch (Exception ignore) {
            Log.d(MODEL_CLASS.getName(), ignore.getMessage());
        }
        return null;
    }

    public List<T> getAll () {
        List<T> objectList = new ArrayList<>();
        try {
            Dao localConnection = getDao(MODEL_CLASS);
            objectList = localConnection.queryForAll();
        } catch (Exception ignore) {
            Log.d(MODEL_CLASS.getName(), ignore.getMessage());
        }
        return objectList;
    }

    public int update (T data) {
        int count = 0;
        try {
            Dao connection = getDao(MODEL_CLASS);
            count = connection.update(data);
        } catch (Exception ignore) {
            Log.d(MODEL_CLASS.getName(), ignore.getMessage());
        }
        return count;
    }


    public int update (List<T> data) {
        int count = 0;
        try {
            Dao connection = getDao(MODEL_CLASS);
            for(int i=0;i<data.size();i++) {
                count += connection.update(data.get(i));
            }
        } catch (Exception ignore) {
            Log.d(MODEL_CLASS.getName(), ignore.getMessage());
        }
        return count;
    }

    public Boolean clearTable(){
        try {
            TableUtils.clearTable(getConnectionSource(), MODEL_CLASS);
            return true;
        } catch (Exception ignore){
            Log.d(MODEL_CLASS.getName(), ignore.getMessage());
            return false;
        }
    }

    public int create (List objects) {
        int count = 0;
        try {
            Dao connection = getDao(MODEL_CLASS);
            count = connection.create(objects);
        } catch (Exception ignore) {
            Log.d(MODEL_CLASS.getName(), ignore.getMessage());
        }
        return count;
    }

    public int delete (List objects) {
        int count = 0;
        try {
            Dao connection = getDao(MODEL_CLASS);
            count = connection.delete(objects);
        } catch (Exception ignore) {
            Log.d(MODEL_CLASS.getName(), ignore.getMessage());
        }
        return count;
    }

    public int delete (Object objects) {
        int count = 0;
        try {
            Dao connection = getDao(MODEL_CLASS);
            count = connection.delete(objects);
        } catch (Exception ignore) {
            Log.d(MODEL_CLASS.getName(), ignore.getMessage());
        }
        return count;
    }

    public T getByField (Object object, String columnName) {
        try{
            Dao localDao=getDao(MODEL_CLASS);
            return (T)localDao.queryForFirst(localDao.queryBuilder().where().eq(columnName,object).prepare());
        }catch (Exception ignore){
            Log.d(MODEL_CLASS.getName(), ignore.getMessage());
            return null;
        }
    }

    public List<T> getListByField (Object object, String columnName) {
        try{
            Dao localDao=getDao(MODEL_CLASS);
            return localDao.query(localDao.queryBuilder().where().eq(columnName,object).prepare());
        }catch (Exception ignore){
            Log.d(MODEL_CLASS.getName(), ignore.getMessage());
            return new ArrayList();
        }
    }
}

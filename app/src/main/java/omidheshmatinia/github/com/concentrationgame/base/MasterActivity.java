package omidheshmatinia.github.com.concentrationgame.base;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import omidheshmatinia.github.com.concentrationgame.interfaces.MasterActivityViewInterface;

/**
 * Created by Omid Heshmatinia on 8/3/17.
 */

public class MasterActivity extends AppCompatActivity implements MasterActivityViewInterface {
    @Override
    public void toast(Object item, int length) {
        if(item==null)
            return;
        String txt = item.toString();
        if(item instanceof Integer){
          txt = getString((Integer)item);
        }
        Toast.makeText(getActivityContext(),txt,length).show();
    }

    protected Context getActivityContext(){
        return this;
    }

}

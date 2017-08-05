package omidheshmatinia.github.com.concentrationgame.base;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import omidheshmatinia.github.com.concentrationgame.interfaces.MasterActivityViewInterface;
import omidheshmatinia.github.com.concentrationgame.interfaces.MasterFragmentViewInterface;

/**
 * Created by Omid Heshmatinia on 8/3/17.
 */

public class MasterFragment extends Fragment implements MasterFragmentViewInterface {
    @Override
    public void toast(Object item, int length) {
        if(item==null)
            return;
        String txt = item.toString();
        if(item instanceof Integer){
          txt = getString((Integer)item);
        }
        Toast.makeText(getContext(),txt,length).show();
    }

}

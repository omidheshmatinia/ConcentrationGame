package omidheshmatinia.github.com.concentrationgame.utils;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;

/**
 * Used for getting size and width of screen
 * @author omid
 */
public class ScreenHelper {

    /**
     * @param ct An activity
     * @return screen width
     */
    public static int getScreenWidth(AppCompatActivity ct){
        Display display = ct.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }

    /**
     * @param ct An activity
     * @return screen height
     */
    public static int getScreenHeight(AppCompatActivity ct){
        Display display = ct.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.y;
    }

}

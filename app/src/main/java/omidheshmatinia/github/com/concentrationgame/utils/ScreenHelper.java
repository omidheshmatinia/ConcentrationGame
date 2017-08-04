package omidheshmatinia.github.com.concentrationgame.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;

/**
 * Created by Omid Heshmatinia on 8/4/17.
 */

public class ScreenHelper {

    public static int getScreenWidth(AppCompatActivity ct){
        Display display = ct.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }

    public static int getScreenHeight(AppCompatActivity ct){
        Display display = ct.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.y;
    }

}

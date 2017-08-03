package omidheshmatinia.github.com.concentrationgame.utils;

import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;

import omidheshmatinia.github.com.concentrationgame.R;
import omidheshmatinia.github.com.concentrationgame.base.MasterApplication;

/**
 * Created by Omid Heshmatinia on 8/3/17.
 */

public class ColorHelper {
    private int[] colors = {
            R.color.md_blue_200,
            R.color.md_red_200,
            R.color.md_orange_200,
            R.color.md_green_200,
            R.color.md_pink_200,
            R.color.md_purple_200,
            R.color.md_lime_200
    };

    private ColorHelper(){
    }

    private final static class Instance {
        private final static ColorHelper ITEM = new ColorHelper();
    }

    public static ColorHelper getInstance(){
        return Instance.ITEM;
    }

    public int getColorFromResource(@ColorRes int colorId){
        return ContextCompat.getColor(MasterApplication.getInstance(), colorId);
    }

    public int getColorFromList(int row){
        return ContextCompat.getColor(MasterApplication.getInstance(), colors[row%colors.length]);
    }
}

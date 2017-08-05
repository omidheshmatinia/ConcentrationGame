package omidheshmatinia.github.com.concentrationgame.utils;

import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;

import omidheshmatinia.github.com.concentrationgame.R;
import omidheshmatinia.github.com.concentrationgame.base.MasterApplication;

/**
 * This class is a helper for generating colors and also a wrapper for ContextCompat to get color wherever we like
 * without passing a Context
 * @author omid
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


    //<editor-fold desc="Singleton part">
    private ColorHelper(){
    }

    private final static class Instance {
        private final static ColorHelper ITEM = new ColorHelper();
    }

    public static ColorHelper getInstance(){
        return Instance.ITEM;
    }
    //</editor-fold>

    /**
     * Used as wrapper for ContextCompat to return color without having access to context
     * @param colorId is the resource id of the color
     * @return Color
     */
    public int getColorFromResource(@ColorRes int colorId){
        return ContextCompat.getColor(MasterApplication.getInstance(), colorId);
    }

    /**
     * Return a color for card backgrounds
     * @param row index of color
     * @return a color from the list of colors
     */
    public int getColorFromList(int row){
        return ContextCompat.getColor(MasterApplication.getInstance(), colors[row%colors.length]);
    }
}

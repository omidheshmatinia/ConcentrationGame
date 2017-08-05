package omidheshmatinia.github.com.concentrationgame.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import omidheshmatinia.github.com.concentrationgame.PublicEnums;
import omidheshmatinia.github.com.concentrationgame.fragments.scorehistory.ScoreHistoryFragment;

public class ScoreHistoryViewPagerAdapter extends FragmentStatePagerAdapter {

    PublicEnums.Difficulty[] types = PublicEnums.Difficulty.values();

    public ScoreHistoryViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return ScoreHistoryFragment.newInstance(types[position]);
    }

    @Override
    public int getCount() {
        return types.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return types[position].getName();
    }
}

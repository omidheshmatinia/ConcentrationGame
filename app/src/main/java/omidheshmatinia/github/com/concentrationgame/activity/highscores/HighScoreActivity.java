package omidheshmatinia.github.com.concentrationgame.activity.highscores;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.ogaclejapan.smarttablayout.SmartTabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import javax.inject.Inject;
import omidheshmatinia.github.com.concentrationgame.R;
import omidheshmatinia.github.com.concentrationgame.adapter.ScoreHistoryViewPagerAdapter;
import omidheshmatinia.github.com.concentrationgame.base.MasterActivity;
import omidheshmatinia.github.com.concentrationgame.dagger.ApplicationModule;

public class HighScoreActivity extends MasterActivity implements HighScoreContract.View {

    @BindView(R.id.viewpager_score_history)
    ViewPager viewPager;
    @BindView(R.id.tab_score_history)
    SmartTabLayout tabLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @Inject
    HighScoreContract.Presenter presenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_history);
        ButterKnife.bind(this);
        daggerInitialize();
        presenter.viewCreated();
    }

    private void daggerInitialize() {
        DaggerHighScoreComponent.builder()
            .highScoreActivityModule(new HighScoreActivityModule(this))
            .build()
            .inject(this);
    }

    @Override
    public void initViewPager() {
        viewPager.setAdapter(new ScoreHistoryViewPagerAdapter(getSupportFragmentManager()));
        tabLayout.setViewPager(viewPager);
    }

    @Override
    public void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}

package omidheshmatinia.github.com.concentrationgame.activity.dashboard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;
import omidheshmatinia.github.com.concentrationgame.R;
import omidheshmatinia.github.com.concentrationgame.activity.game.GameActivity;
import omidheshmatinia.github.com.concentrationgame.activity.highscores.HighScoreActivity;
import omidheshmatinia.github.com.concentrationgame.activity.setting.SettingActivity;
import omidheshmatinia.github.com.concentrationgame.model.ScoreHistory;

public class DashboardActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.button_dashboard_game,
            R.id.button_dashboard_high_scores,
            R.id.button_dashboard_setting})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.button_dashboard_game:
                Intent mIntent = new Intent(this, GameActivity.class);
                startActivity(mIntent);
                break;
            case R.id.button_dashboard_high_scores:
                Intent mIntent2 = new Intent(this, HighScoreActivity.class);
                startActivity(mIntent2);
                break;
            case R.id.button_dashboard_setting:
                Intent mIntent3 = new Intent(this, SettingActivity.class);
                startActivity(mIntent3);
                break;
        }
    }
}

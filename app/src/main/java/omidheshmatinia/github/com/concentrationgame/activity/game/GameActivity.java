package omidheshmatinia.github.com.concentrationgame.activity.game;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Chronometer;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import eu.davidea.flipview.FlipView;
import omidheshmatinia.github.com.concentrationgame.R;
import omidheshmatinia.github.com.concentrationgame.adapter.CardAdapter;
import omidheshmatinia.github.com.concentrationgame.base.MasterActivity;
import omidheshmatinia.github.com.concentrationgame.customview.CircularReveal.CircularRevealView;
import omidheshmatinia.github.com.concentrationgame.customview.FixedGridLayoutManager;
import omidheshmatinia.github.com.concentrationgame.customview.GridItemDecoration;
import omidheshmatinia.github.com.concentrationgame.model.PictureCard;
import omidheshmatinia.github.com.concentrationgame.utils.ColorHelper;
import omidheshmatinia.github.com.concentrationgame.utils.ScreenHelper;

public class GameActivity extends MasterActivity implements GameContract.View {

    GamePresenter mPresenter;
    @BindView(R.id.recyclerview_game_board)
    RecyclerView mCardList;
    @BindView(R.id.chronometer_game)
    Chronometer mChronometer;
    @BindView(R.id.view_game_wrong_choose)
    View wrongView;
    @BindView(R.id.circularview_game)
    CircularRevealView circularView;
    @BindView(R.id.view_game_final_score)
    View viewFinalScore;
    @BindView(R.id.edittext_game_name)
    MaterialEditText editTextName;
    @BindView(R.id.progressbar_game)
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ButterKnife.bind(this);
        mPresenter = new GamePresenter(this);
        mPresenter.viewCreated();
    }

    @Override
    public void initList(List<PictureCard> items,int columnSize) {

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int screenWidth = ScreenHelper.getScreenWidth(this);

        int numberOfColumns = screenWidth / (int)getResources().getDimension(R.dimen.width_cards) ;
        mCardList.setLayoutManager(new GridLayoutManager(getActivityContext(),numberOfColumns));
        mCardList.setAdapter(new CardAdapter(items, new View.OnClickListener() {
            @Override public void onClick(View v) {
                PictureCard item = (PictureCard) v.getTag();
                mPresenter.listCardItemClicked(item);
            }
        }));

        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.grid_spacing);
        mCardList.addItemDecoration(new GridItemDecoration(numberOfColumns, spacingInPixels, true, 0));
    }

    @Override
    public void flipCard(int position) {
        View view = mCardList.getLayoutManager().findViewByPosition(position);
        if(view!=null){
            FlipView flipView= view.findViewById(R.id.flipview_item_card);
            Boolean isRear = flipView.isFlipped();
            flipView.flip(!isRear);
        }
    }

    @Override
    public void changeWrongViewVisibility(int visibility) {
        wrongView.setVisibility(visibility);
    }

    @Override
    public void animateWrongView() {
        AlphaAnimation animation = new AlphaAnimation(1.0f, 0.0f);
        animation.setDuration(500);
        animation.setFillAfter(true);
        wrongView.startAnimation(animation);
    }

    @Override
    public void changeChronometerStatus(boolean start) {
        if(start)
            mChronometer.start();
        else
            mChronometer.stop();
    }

    @Override
    public void showSubmitView() {
        int screenWidth = ScreenHelper.getScreenWidth(this);
        int screenHeight = ScreenHelper.getScreenHeight(this);
        circularView.reveal(screenWidth / 2, screenHeight,
                ColorHelper.getInstance().getColorFromResource(R.color.md_green_400), 0, 1000,
                new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        viewFinalScore.setVisibility(View.VISIBLE);
                        YoYo.with(Techniques.FadeIn).playOn(viewFinalScore);
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });
    }

    @Override
    public String getName() {
        return editTextName.getText().toString();
    }

    @Override
    public void setBeginTimeOFChronometer(long beginTime) {
        mChronometer.setBase(beginTime);
    }

    @Override
    public void changeProgressbarVisibility(int visibility) {
        progressBar.setVisibility(visibility);
    }

    @OnClick({R.id.button_game_submit})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.button_game_submit:
                mPresenter.submitRecordClicked();
                break;
        }
    }
}

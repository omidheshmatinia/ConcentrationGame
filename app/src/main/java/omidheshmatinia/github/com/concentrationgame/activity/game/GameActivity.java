package omidheshmatinia.github.com.concentrationgame.activity.game;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import omidheshmatinia.github.com.concentrationgame.R;
import omidheshmatinia.github.com.concentrationgame.adapter.CardAdapter;
import omidheshmatinia.github.com.concentrationgame.base.MasterActivity;
import omidheshmatinia.github.com.concentrationgame.customview.FixedGridLayoutManager;
import omidheshmatinia.github.com.concentrationgame.model.PictureCard;

public class GameActivity extends MasterActivity implements GameContract.View {

    GamePresenter mPresenter;
    @BindView(R.id.recyclerview_game_board)
    RecyclerView mCardList;

    public static void start(Context context) {
//        Intent starter = new Intent(context, GameActivity.class);
//        starter.putExtra();
//        context.startActivity(starter);
//        //todo
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ButterKnife.bind(this);
        mPresenter = new GamePresenter(this);
        mPresenter.viewCreated();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mPresenter.viewDetached();
    }

    @Override
    public void initList(List<PictureCard> items,int columnSize) {
        FixedGridLayoutManager lm = new FixedGridLayoutManager();
        lm.setTotalColumnCount(columnSize);
        mCardList.setLayoutManager(lm);
        mCardList.setAdapter(new CardAdapter(items, new View.OnClickListener() {
            @Override public void onClick(View v) {
                PictureCard item = (PictureCard) v.getTag();
                mPresenter.listCardItemClicked(item);
            }
        }));
    }
}

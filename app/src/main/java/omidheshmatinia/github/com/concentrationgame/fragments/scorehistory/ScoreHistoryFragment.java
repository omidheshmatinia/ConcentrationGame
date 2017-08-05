package omidheshmatinia.github.com.concentrationgame.fragments.scorehistory;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import omidheshmatinia.github.com.concentrationgame.PublicEnums;
import omidheshmatinia.github.com.concentrationgame.R;
import omidheshmatinia.github.com.concentrationgame.adapter.ScoreHistoryAdapter;
import omidheshmatinia.github.com.concentrationgame.base.MasterFragment;
import omidheshmatinia.github.com.concentrationgame.model.ScoreHistory;

public class ScoreHistoryFragment extends MasterFragment implements ScoreHistoryContract.View {

    private final static String DIFFICULTY_TYPE = "DifficultyType";
    @BindView(R.id.recyclerview_score_history)
    RecyclerView historyList;
    @BindView(R.id.view_score_no_record)
    View noRecordView;

    private ScoreHistoryPresenter mPresenter;

    public static ScoreHistoryFragment newInstance(PublicEnums.Difficulty type) {
        Bundle args = new Bundle();
        args.putInt(DIFFICULTY_TYPE, type.getType());
        ScoreHistoryFragment fragment = new ScoreHistoryFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_score_history, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = new ScoreHistoryPresenter(this);
        if (getArguments() == null || !getArguments().containsKey(DIFFICULTY_TYPE)) {
            throw new IllegalArgumentException("Please pass DifficultyType as one of arguments or use newInstance method");
        } else {
            mPresenter.setDifficultyTypeFromIntent(getArguments().getInt(DIFFICULTY_TYPE));
        }
        mPresenter.viewCreated();
    }

    @Override
    public void initList(List<ScoreHistory> items) {
        historyList.setLayoutManager(new LinearLayoutManager(getContext()));
        historyList.setAdapter(new ScoreHistoryAdapter(items));
    }

    @Override
    public void changeNoRecordVisibility(int visibility) {
        noRecordView.setVisibility(visibility);
    }

}

package omidheshmatinia.github.com.concentrationgame.activity.setting;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.ArrayAdapter;

import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import omidheshmatinia.github.com.concentrationgame.PublicEnums;
import omidheshmatinia.github.com.concentrationgame.R;
import omidheshmatinia.github.com.concentrationgame.base.MasterActivity;

public class SettingActivity extends MasterActivity implements SettingContract.View {

    @BindView(R.id.spinner_setting_difficulty)
    AppCompatSpinner spinnerDifficulty;
    @BindView(R.id.edittext_setting_search_term)
    MaterialEditText etSearchTerm;

    private SettingContract.Presenter mPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mPresenter= new SettingPresenter(this);
        mPresenter.viewCreated();
    }

    @Override
    public String getSearchTerm() {
        return etSearchTerm.getText().toString();
    }

    @Override
    public PublicEnums.Difficulty getDifficulty() {
        return (PublicEnums.Difficulty)spinnerDifficulty.getSelectedItem();
    }

    @Override
    public void initSpinner(List<PublicEnums.Difficulty> types,PublicEnums.Difficulty currentDifficulty) {
        spinnerDifficulty.setAdapter(new ArrayAdapter(getActivityContext(),R.layout.item_spinner,types));
        int index = types.indexOf(currentDifficulty);
        spinnerDifficulty.setSelection(index);
    }

    @Override
    public void setSearchTerm(String term) {
        etSearchTerm.setText(term);
    }

    @OnClick({R.id.button_setting_save})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.button_setting_save:
                mPresenter.buttonSaveClicked();
                break;
        }
    }
}

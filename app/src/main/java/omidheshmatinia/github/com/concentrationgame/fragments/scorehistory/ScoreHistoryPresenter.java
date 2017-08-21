package omidheshmatinia.github.com.concentrationgame.fragments.scorehistory;

import android.util.Log;
import android.view.View;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.observers.DisposableMaybeObserver;
import io.reactivex.schedulers.Schedulers;
import java.util.List;

import omidheshmatinia.github.com.concentrationgame.model.ScoreHistory;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import static dagger.internal.InstanceFactory.create;

class ScoreHistoryPresenter implements ScoreHistoryContract.Presenter {

  private ScoreHistoryContract.View mView;
  private ScoreHistoryContract.Model mModel;
  private Disposable dataSubscriber;

  ScoreHistoryPresenter(ScoreHistoryContract.View mView) {
    this.mView = mView;
    mModel = new ScoreHistoryModel();
  }

  @Override
  public void viewCreated() {

    dataSubscriber = Observable
        .<List<ScoreHistory>>create(e ->{
          e.onNext(mModel.getHistoryList(mModel.getDifficultyType()));
          e.onComplete();
        })
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(items ->{
          if(items!=null&& items.size()>0) {
            mView.initList(items);
          } else {
            mView.changeNoRecordVisibility(View.VISIBLE);
          }
        });
  }

  @Override
  public void setDifficultyTypeFromIntent(int difficultyType) {
    mModel.setDifficultyType(difficultyType);
  }

  @Override public void viewDetached() {
    dataSubscriber.dispose();
  }
}

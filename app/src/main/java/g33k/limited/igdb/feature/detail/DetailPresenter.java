package g33k.limited.igdb.feature.detail;

import java.util.List;

import javax.inject.Inject;

import g33k.limited.igdb.core.models.Game;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by sambains on 19/12/2016.
 */

public class DetailPresenter extends DisposableObserver<List<Game>> implements DetailContract.DetailPresenter {

    private DetailContract.DetailView detailView;
    private DetailContract.DetailInteractor detailInteractor;
    private Disposable disposable = Disposables.empty();

    @Inject
    DetailPresenter(DetailContract.DetailView detailView, DetailContract.DetailInteractor detailInteractor) {
        this.detailView = detailView;
        this.detailInteractor = detailInteractor;
    }

    @Override
    public void detachView() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }

        detailView = null;

        detailInteractor = null;
    }

    @Override
    public void getGame(String gameId) {
        detailView.showProgress();

        disposable = detailInteractor.getGame(gameId)
                .subscribeWith(this);
    }

    @Override
    public void onNext(List<Game> games) {
        detailView.showGame(games.get(0));
        detailView.hideProgress();
        detailView.showContent();
    }

    @Override
    public void onError(Throwable e) {
        detailView.showError(e.getMessage());
        detailView.hideProgress();
    }

    @Override
    public void onComplete() {

    }
}

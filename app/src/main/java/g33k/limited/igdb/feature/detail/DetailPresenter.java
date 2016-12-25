package g33k.limited.igdb.feature.detail;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * Created by sambains on 19/12/2016.
 */

class DetailPresenter implements DetailContract.DetailPresenter {

    @Inject
    DetailContract.DetailInteractor detailInteractor;

    private DetailContract.DetailView detailView;
    private Disposable disposable;

    @Inject
    DetailPresenter(DetailContract.DetailView detailView) {
        this.detailView = detailView;
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
                .subscribe(games -> {
                    detailView.showGame(games.get(0));
                    detailView.hideProgress();
                    detailView.showContent();
                }, throwable -> {
                    detailView.showError(throwable.getMessage());
                    detailView.hideProgress();
                });
    }
}

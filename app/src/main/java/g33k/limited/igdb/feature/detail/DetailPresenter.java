package g33k.limited.igdb.feature.detail;

import javax.inject.Inject;

import g33k.limited.igdb.BuildConfig;
import g33k.limited.igdb.core.api.Api;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by sambains on 19/12/2016.
 */

class DetailPresenter implements DetailContract.DetailPresenter {

    private CompositeDisposable compositeDisposable;
    private Api api;
    private DetailContract.DetailView detailView;

    @Inject
    DetailPresenter(Api api, DetailContract.DetailView detailView) {
        this.api = api;
        this.detailView = detailView;

        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void detachView() {
        detailView = null;

        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    @Override
    public void getGame() {
        detailView.showProgress();

        compositeDisposable.add(api.getGame(BuildConfig.API_KEY, "1020")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(game -> {
                    detailView.showGame(game.get(0));
                    detailView.hideProgress();
                    detailView.showContent();
                }, throwable -> {
                    detailView.showError(throwable.getMessage());
                    detailView.hideProgress();
                }));
    }
}

package g33k.limited.igdb.feature.detail;

import javax.inject.Inject;

/**
 * Created by sambains on 19/12/2016.
 */

class DetailPresenter implements DetailContract.DetailPresenter {

    @Inject
    DetailContract.DetailInteractor detailInteractor;

    private DetailContract.DetailView detailView;

    @Inject
    DetailPresenter(DetailContract.DetailView detailView) {
        this.detailView = detailView;
    }

    @Override
    public void detachView() {
        detailInteractor.unbind();

        detailView = null;
        detailInteractor = null;
    }

    @Override
    public void getGame() {
        detailView.showProgress();

        detailInteractor.getGame(games -> {
            detailView.showGame(games.get(0));
            detailView.hideProgress();
            detailView.showContent();
        }, throwable -> {
            detailView.showError(throwable.getMessage());
            detailView.hideProgress();
        }, "1020");
    }
}

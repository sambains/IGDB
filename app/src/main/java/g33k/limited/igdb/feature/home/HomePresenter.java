package g33k.limited.igdb.feature.home;

import javax.inject.Inject;

/**
 * Created by sambains on 19/12/2016.
 */

class HomePresenter implements HomeContract.HomePresenter {

    private HomeContract.HomeView view;

    @Inject
    HomePresenter(HomeContract.HomeView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void showDetailScreen() {
        view.navigateToDetailScreen();
    }
}

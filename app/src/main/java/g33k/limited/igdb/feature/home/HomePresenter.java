package g33k.limited.igdb.feature.home;

import javax.inject.Inject;

/**
 * Created by sambains on 19/12/2016.
 */

public class HomePresenter implements HomeContract.HomePresenter {

    private HomeContract.HomeView homeView;

    @Inject
    HomePresenter(HomeContract.HomeView homeView) {
        this.homeView = homeView;
    }

    @Override
    public void detachView() {
        homeView = null;
    }

    @Override
    public void onShowDetailButtonClicked() {
        homeView.navigateToDetailScreen();
    }
}

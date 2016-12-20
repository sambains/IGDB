package g33k.limited.igdb.feature.home;

import g33k.limited.igdb.core.base.BasePresenter;

/**
 * Created by sambains on 20/12/2016.
 */

interface HomeContract {

    interface HomeView {
        void navigateToDetailScreen();
    }

    interface HomePresenter extends BasePresenter {
        void showDetailScreen();
    }
}

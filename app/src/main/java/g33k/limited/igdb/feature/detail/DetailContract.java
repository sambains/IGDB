package g33k.limited.igdb.feature.detail;

import g33k.limited.igdb.core.base.BasePresenter;
import g33k.limited.igdb.core.models.Game;

/**
 * Created by sambains on 20/12/2016.
 */

interface DetailContract {

    interface DetailView {
        void showProgress();

        void hideProgress();

        void showContent();

        void showGame(Game game);

        void showError(String errorMessage);
    }

    interface DetailPresenter extends BasePresenter {
        void getGame();
    }
}

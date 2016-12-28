package g33k.limited.igdb.feature.detail;

import java.util.List;

import g33k.limited.igdb.core.base.BasePresenter;
import g33k.limited.igdb.core.models.Game;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by sambains on 20/12/2016.
 */

public interface DetailContract {

    interface DetailView {
        void showProgress();

        void hideProgress();

        void showContent();

        void showGame(Game game);

        void showError(String errorMessage);
    }

    interface DetailPresenter extends BasePresenter {
        void getGame(String gameId);
    }

    interface DetailInteractor {
        Observable<List<Game>> getGame(String gameId);
    }
}

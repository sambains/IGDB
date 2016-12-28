package g33k.limited.igdb.feature.detail;

import java.util.List;

import javax.inject.Inject;

import g33k.limited.igdb.BuildConfig;
import g33k.limited.igdb.core.api.Api;
import g33k.limited.igdb.core.dependencies.CustomScope;
import g33k.limited.igdb.core.models.Game;
import g33k.limited.igdb.core.util.SchedulerProvider;
import io.reactivex.Observable;

/**
 * Created by sambains on 24/12/2016.
 */
@CustomScope
public class DetailInteractor implements DetailContract.DetailInteractor {

    private Api api;
    private SchedulerProvider schedulerProvider;

    @Inject
    DetailInteractor(Api api, SchedulerProvider schedulerProvider) {
        this.api = api;
        this.schedulerProvider = schedulerProvider;
    }

    @Override
    public Observable<List<Game>> getGame(String gameId) {
        return api.getGame(BuildConfig.API_KEY, gameId)
                .subscribeOn(schedulerProvider.backgroundThread())
                .observeOn(schedulerProvider.mainThread());
    }
}

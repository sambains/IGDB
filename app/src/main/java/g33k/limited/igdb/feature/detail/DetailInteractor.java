package g33k.limited.igdb.feature.detail;

import java.util.List;

import javax.inject.Inject;

import g33k.limited.igdb.BuildConfig;
import g33k.limited.igdb.core.api.Api;
import g33k.limited.igdb.core.dependencies.CustomScope;
import g33k.limited.igdb.core.models.Game;
import g33k.limited.igdb.core.util.SchedulerProvider;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by sambains on 24/12/2016.
 */
@CustomScope
class DetailInteractor implements DetailContract.DetailInteractor {

    private Api api;
    private SchedulerProvider schedulerProvider;
    private Disposable disposable;

    @Inject
    DetailInteractor(Api api, SchedulerProvider schedulerProvider) {
        this.api = api;
        this.schedulerProvider = schedulerProvider;
    }

    @Override
    public void getGame(Consumer<List<Game>> onNext, Consumer<Throwable> onError, String gameId) {
        disposable = api.getGame(BuildConfig.API_KEY, gameId)
                .subscribeOn(schedulerProvider.backgroundThread())
                .observeOn(schedulerProvider.mainThread())
                .subscribe(onNext, onError);
    }

    @Override
    public void unbind() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}

package g33k.limited.igdb.feature.detail;

import java.util.List;

import javax.inject.Inject;

import g33k.limited.igdb.BuildConfig;
import g33k.limited.igdb.core.api.Api;
import g33k.limited.igdb.core.dependencies.CustomScope;
import g33k.limited.igdb.core.models.Game;
import g33k.limited.igdb.core.util.SchedulerProvider;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by sambains on 24/12/2016.
 */
@CustomScope
class DetailInteractor implements DetailContract.DetailInteractor {

    private Api api;
    private SchedulerProvider schedulerProvider;
    private PublishSubject<List<Game>> publishSubject;
    private Disposable disposable;

    @Inject
    DetailInteractor(Api api, SchedulerProvider schedulerProvider) {
        this.api = api;
        this.schedulerProvider = schedulerProvider;
    }

    @Override
    public Observable<List<Game>> getGame(String gameId) {
        if (disposable == null || disposable.isDisposed()) {
            publishSubject = PublishSubject.create();

            disposable = api.getGame(BuildConfig.API_KEY, gameId)
                    .subscribeOn(schedulerProvider.backgroundThread())
                    .subscribeWith(new DisposableObserver<List<Game>>() {
                        @Override
                        public void onNext(List<Game> games) {
                            publishSubject.onNext(games);
                        }

                        @Override
                        public void onError(Throwable e) {
                            publishSubject.onError(e);
                        }

                        @Override
                        public void onComplete() {
                            publishSubject.onComplete();
                        }
                    });
        }

        return publishSubject.hide();
    }

    @Override
    public void unbind() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}

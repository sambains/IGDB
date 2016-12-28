package g33k.limited.igdb.feature.detail;

import dagger.Module;
import dagger.Provides;
import g33k.limited.igdb.core.api.Api;
import g33k.limited.igdb.core.dependencies.CustomScope;
import g33k.limited.igdb.core.util.SchedulerProvider;

/**
 * Created by sambains on 20/12/2016.
 */
@Module
public class DetailModule {

    private DetailContract.DetailView detailView;

    DetailModule(DetailContract.DetailView detailView) {
        this.detailView = detailView;
    }

    @Provides
    @CustomScope
    DetailContract.DetailView providesDetailView() {
        return detailView;
    }

    @Provides
    @CustomScope
    DetailContract.DetailPresenter providesDetailPresenter(DetailContract.DetailView detailView, DetailInteractor detailInteractor) {
        return new DetailPresenter(detailView, detailInteractor);
    }

    @Provides
    @CustomScope
    DetailContract.DetailInteractor providesDetailInteractor(Api api, SchedulerProvider schedulerProvider) {
        return new DetailInteractor(api, schedulerProvider);
    }
}

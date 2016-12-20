package g33k.limited.igdb.feature.detail;

import dagger.Module;
import dagger.Provides;
import g33k.limited.igdb.core.dependencies.CustomScope;

/**
 * Created by sambains on 20/12/2016.
 */
@Module
class DetailModule {

    private DetailContract.DetailView detailView;

    DetailModule(DetailContract.DetailView detailView) {
        this.detailView = detailView;
    }

    @Provides
    @CustomScope
    DetailContract.DetailView providesDetailView() {
        return detailView;
    }
}

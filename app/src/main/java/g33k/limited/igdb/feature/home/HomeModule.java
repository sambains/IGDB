package g33k.limited.igdb.feature.home;

import dagger.Module;
import dagger.Provides;
import g33k.limited.igdb.core.dependencies.CustomScope;

/**
 * Created by sambains on 20/12/2016.
 */
@Module
public class HomeModule {

    private HomeContract.HomeView homeView;

    HomeModule(HomeContract.HomeView homeView) {
        this.homeView = homeView;
    }

    @Provides
    @CustomScope
    HomeContract.HomeView providesHomeView() {
        return homeView;
    }

    @Provides
    @CustomScope
    HomeContract.HomePresenter providesHomePresenter(HomeContract.HomePresenter homePresenter) {
        return homePresenter;
    }
}

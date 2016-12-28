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

    public HomeModule(HomeContract.HomeView homeView) {
        this.homeView = homeView;
    }

    @Provides
    @CustomScope
    public HomeContract.HomeView providesHomeView() {
        return homeView;
    }

    @Provides
    @CustomScope
    public HomeContract.HomePresenter providesHomePresenter(HomeContract.HomeView homeView) {
        return new HomePresenter(homeView);
    }
}

package g33k.limited.igdb.feature.home;

import dagger.Module;
import dagger.Provides;
import g33k.limited.igdb.core.dependencies.CustomScope;

/**
 * Created by sambains on 20/12/2016.
 */
@Module
class HomeModule {

    private HomeContract.HomeView view;

    HomeModule(HomeContract.HomeView view) {
        this.view = view;
    }

    @Provides
    @CustomScope
    HomeContract.HomeView providesHomeView() {
        return view;
    }
}

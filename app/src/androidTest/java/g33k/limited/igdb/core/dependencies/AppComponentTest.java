package g33k.limited.igdb.core.dependencies;

import javax.inject.Singleton;

import dagger.Component;
import g33k.limited.igdb.feature.detail.DetailComponentTest;
import g33k.limited.igdb.feature.detail.DetailModule;
import g33k.limited.igdb.feature.home.HomeComponentTest;
import g33k.limited.igdb.feature.home.HomeModule;

/**
 * Created by sambains on 26/12/2016.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponentTest extends AppComponent {

    @Override
    HomeComponentTest plus(HomeModule homeModule);

    @Override
    DetailComponentTest plus(DetailModule detailModule);
}

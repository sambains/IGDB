package g33k.limited.igdb.core.dependencies;

import javax.inject.Singleton;

import dagger.Component;
import g33k.limited.igdb.feature.detail.DetailComponent;
import g33k.limited.igdb.feature.detail.DetailModule;
import g33k.limited.igdb.feature.home.HomeComponent;
import g33k.limited.igdb.feature.home.HomeModule;

/**
 * Created by sambains on 19/12/2016.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    HomeComponent plus(HomeModule homeModule);

    DetailComponent plus(DetailModule detailModule);
}

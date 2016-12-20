package g33k.limited.igdb.feature.detail;

import dagger.Component;
import g33k.limited.igdb.core.dependencies.AppComponent;
import g33k.limited.igdb.core.dependencies.CustomScope;

/**
 * Created by sambains on 20/12/2016.
 */
@CustomScope
@Component(dependencies = AppComponent.class, modules = DetailModule.class)
interface DetailComponent {

    void inject(DetailActivity detailActivity);
}

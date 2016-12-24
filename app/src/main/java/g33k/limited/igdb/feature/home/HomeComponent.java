package g33k.limited.igdb.feature.home;

import dagger.Subcomponent;
import g33k.limited.igdb.core.dependencies.CustomScope;

/**
 * Created by sambains on 20/12/2016.
 */
@CustomScope
@Subcomponent(modules = HomeModule.class)
public interface HomeComponent {

    void inject(HomeActivity homeActivity);
}

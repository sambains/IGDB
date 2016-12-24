package g33k.limited.igdb.feature.detail;

import dagger.Subcomponent;
import g33k.limited.igdb.core.dependencies.CustomScope;

/**
 * Created by sambains on 20/12/2016.
 */
@CustomScope
@Subcomponent(modules = DetailModule.class)
public interface DetailComponent {

    void inject(DetailActivity detailActivity);
}

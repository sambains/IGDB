package g33k.limited.igdb.core.base;

import g33k.limited.igdb.core.dependencies.AppComponentTest;
import g33k.limited.igdb.core.dependencies.AppModule;
import g33k.limited.igdb.core.dependencies.DaggerAppComponentTest;

/**
 * Created by sambains on 26/12/2016.
 */

public class BaseApplicationTest extends BaseApplication {

    @Override
    public AppComponentTest createAppComponent() {
        return DaggerAppComponentTest.builder()
                .appModule(new AppModule())
                .build();
    }
}

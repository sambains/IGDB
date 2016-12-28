package g33k.limited.igdb.core.base;

import android.app.Application;

import com.facebook.stetho.Stetho;

import g33k.limited.igdb.BuildConfig;
import g33k.limited.igdb.core.dependencies.AppComponent;
import g33k.limited.igdb.core.dependencies.AppModule;
import g33k.limited.igdb.core.dependencies.DaggerAppComponent;
import timber.log.Timber;

/**
 * Created by sambains on 19/12/2016.
 */

public class BaseApplication extends Application {

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        initApplication();

        appComponent = createAppComponent();
    }

    protected void initApplication() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());

            Stetho.initialize(Stetho.newInitializerBuilder(this)
                    .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                    .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                    .build());
        }
    }

    public AppComponent createAppComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule())
                .build();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}

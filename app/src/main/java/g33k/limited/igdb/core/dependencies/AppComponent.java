package g33k.limited.igdb.core.dependencies;

import javax.inject.Singleton;

import dagger.Component;
import g33k.limited.igdb.core.api.Api;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by sambains on 19/12/2016.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    Api api();

    Retrofit retrofit();

    OkHttpClient okHttpClient();
}

package g33k.limited.igdb.core.dependencies;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import g33k.limited.igdb.BuildConfig;
import g33k.limited.igdb.core.api.Api;
import g33k.limited.igdb.core.util.AppSchedulerProvider;
import g33k.limited.igdb.core.util.SchedulerProvider;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

/**
 * Created by sambains on 19/12/2016.
 */
@Module
public class AppModule {

    private static final String TAG = AppModule.class.getCanonicalName();

    @Provides
    @Singleton
    SchedulerProvider providesSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @Singleton
    Api providesApi(Retrofit retrofit) {
        return retrofit.create(Api.class);
    }

    @Provides
    @Singleton
    Retrofit providesRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    OkHttpClient providesOkHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor(message -> Timber.tag(TAG).d(message))
                        .setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE))
                .addInterceptor(new StethoInterceptor())
                .build();
    }
}

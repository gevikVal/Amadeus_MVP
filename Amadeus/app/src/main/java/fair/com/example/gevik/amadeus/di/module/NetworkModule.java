package fair.com.example.gevik.amadeus.di.module;

import android.app.Application;
import android.content.Context;

import java.io.File;

import dagger.Module;
import dagger.Provides;
import fair.com.example.gevik.amadeus.network.AuthorizedNetworkInterceptor;
import fair.com.example.gevik.amadeus.network.CarHubService;
import fair.com.example.gevik.amadeus.network.NetworkState;
import fair.com.example.gevik.amadeus.scope.ApplicationScope;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

/**
 * Created by gevik on 2/25/2018.
 */
@Module
public class NetworkModule {


    @Provides
    public Cache provideCache(Application application) {
        int cacheSize = 10 * 1024 * 1024; // 10MB
        Cache cache = null;
        // Install an HTTP cache in the application cache directory.
        try {
            File cacheDir = new File(application.getCacheDir(), "http");
            cache = new Cache(cacheDir, cacheSize);
        } catch (Exception e) {
            Timber.e(e, "Unable to install disk cache.");
        }
        return cache;
    }

    @Provides
    public HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

    @Provides
    public OkHttpClient provideOkHttpClient(Cache cache, AuthorizedNetworkInterceptor authorizedNetworkInterceptor, HttpLoggingInterceptor httpLoggingInterceptor) {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        okHttpClientBuilder.cache(cache);

        if (authorizedNetworkInterceptor != null) {
            okHttpClientBuilder.addNetworkInterceptor(authorizedNetworkInterceptor);
        }

        okHttpClientBuilder.addInterceptor(httpLoggingInterceptor);

        return okHttpClientBuilder.build();
    }

    @Provides
    public AuthorizedNetworkInterceptor provideAuthorizedNetworkInterceptor(Context context) {
        return new AuthorizedNetworkInterceptor(context);
    }

    @Provides
    public NetworkState provideNetworkState(Context context) {
        return new NetworkState(context);
    }

    @Provides
    public Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(CarHubService.BASE_URL)
                .client(okHttpClient)
                .build();
        return retrofit;
    }

    @Provides
    @ApplicationScope
    public CarHubService provideCarHubService(Retrofit retrofit) {
        return retrofit.create(CarHubService.class);
    }

}

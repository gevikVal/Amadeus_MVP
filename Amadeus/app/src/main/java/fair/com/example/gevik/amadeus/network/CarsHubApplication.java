package fair.com.example.gevik.amadeus.network;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;

import fair.com.example.gevik.amadeus.BuildConfig;
import fair.com.example.gevik.amadeus.di.component.ApplicationComponent;


import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;


import fair.com.example.gevik.amadeus.di.component.DaggerApplicationComponent;
import fair.com.example.gevik.amadeus.di.module.AndroidModule;
import fair.com.example.gevik.amadeus.di.module.ApplicationModule;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import timber.log.Timber;

public class CarsHubApplication extends Application {

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }


    private static CarsHubApplication currentApplication = null;

    private RefWatcher refWatcher;
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        initializeTimber();
        initializeLeakCanary();
        initializeRealm();

        currentApplication = this;

        applicationComponent = createApplicationComponent();

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                        .build());
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }


    public ApplicationComponent createApplicationComponent() {
        return DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .androidModule(new AndroidModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    public static CarsHubApplication getInstance() {
        return currentApplication;
    }

    public static RefWatcher getRefWatcher(Context context) {
        CarsHubApplication application = (CarsHubApplication) context.getApplicationContext();
        return application.refWatcher;
    }

    private void initializeLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {

            return;
        }
        refWatcher = LeakCanary.install(this);
    }

    private void initializeRealm() {
        Realm.init(getApplicationContext());

        RealmConfiguration config = new RealmConfiguration
                .Builder().deleteRealmIfMigrationNeeded()
                .schemaVersion(0)
                .build();

        Realm.setDefaultConfiguration(config);
    }

    private void initializeTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree() {
                // Add the line number to the tag
                @Override
                protected String createStackElementTag(StackTraceElement element) {
                    return super.createStackElementTag(element) + ":" + element.getLineNumber();
                }
            });
        } else {
            Timber.plant(new ReleaseTree());
        }
    }

    private static class ReleaseTree extends Timber.Tree {

        private static final int MAX_LOG_LENGTH = 4000;

        @Override
        protected boolean isLoggable(String tag, int priority) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG || priority == Log.INFO) {
                return false;
            }

            return true;
        }

        @Override
        protected void log(int priority, String tag, String message, Throwable t) {
            if (isLoggable(priority)) {

                if (message.length() < MAX_LOG_LENGTH) {
                    if (priority == Log.ASSERT) {
                        Log.wtf(tag, message);
                    } else {
                        Log.println(priority, tag, message);
                    }
                    return;
                }

                for (int i = 0, length = message.length(); i < length; i++) {
                    int newline = message.indexOf('\n', i);
                    newline = newline != -1 ? newline : length;
                    do {
                        int end = Math.min(newline, i + MAX_LOG_LENGTH);
                        String part = message.substring(i, end);
                        if (priority == Log.ASSERT) {
                            Log.wtf(tag, part);
                        } else {
                            Log.println(priority, tag, part);
                        }
                        i = end;
                    } while (i < newline);
                }
            }
        }
    }

}

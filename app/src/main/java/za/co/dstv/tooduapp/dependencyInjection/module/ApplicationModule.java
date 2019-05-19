package za.co.dstv.tooduapp.dependencyInjection.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import za.co.dstv.tooduapp.configuration.Constants;
import za.co.dstv.tooduapp.data.AppDataManager;
import za.co.dstv.tooduapp.data.AppDatabaseHelper;
import za.co.dstv.tooduapp.data.DataManager;
import za.co.dstv.tooduapp.data.DatabaseHelper;
import za.co.dstv.tooduapp.dependencyInjection.ApplicationContext;
import za.co.dstv.tooduapp.dependencyInjection.DatabaseInfo;

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return Constants.DB_NAME;
    }


    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @Singleton
    DatabaseHelper provideDbHelper(AppDatabaseHelper appDbHelper) {
        return appDbHelper;
    }
}

package za.co.dstv.tooduapp;

import android.app.Application;

import javax.inject.Inject;

import za.co.dstv.tooduapp.data.DataManager;
import za.co.dstv.tooduapp.dependencyInjection.component.ApplicationComponent;
import za.co.dstv.tooduapp.dependencyInjection.component.DaggerApplicationComponent;
import za.co.dstv.tooduapp.dependencyInjection.module.ApplicationModule;

public class TooduApp extends Application {

    @Inject
    DataManager mDataManager;

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();

        mApplicationComponent.inject(this);

    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }

}

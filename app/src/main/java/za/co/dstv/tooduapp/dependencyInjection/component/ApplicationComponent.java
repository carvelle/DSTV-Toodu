package za.co.dstv.tooduapp.dependencyInjection.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import za.co.dstv.tooduapp.TooduApp;
import za.co.dstv.tooduapp.data.DataManager;
import za.co.dstv.tooduapp.dependencyInjection.ApplicationContext;
import za.co.dstv.tooduapp.dependencyInjection.module.ApplicationModule;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(TooduApp app);

    @ApplicationContext
    Context context();

    Application application();

    DataManager getDataManager();
}

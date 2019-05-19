package za.co.dstv.tooduapp.data;


import android.content.Context;

import org.greenrobot.greendao.database.Database;

import javax.inject.Inject;
import javax.inject.Singleton;

import za.co.dstv.tooduapp.data.model.DaoMaster;
import za.co.dstv.tooduapp.dependencyInjection.ApplicationContext;
import za.co.dstv.tooduapp.dependencyInjection.DatabaseInfo;

@Singleton
public class DatabaseOpenHelper extends DaoMaster.OpenHelper {

    @Inject
    public DatabaseOpenHelper(@ApplicationContext Context context, @DatabaseInfo String name) {
        super(context, name);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
        switch (oldVersion) {
            case 1:
            case 2:

        }
    }
}

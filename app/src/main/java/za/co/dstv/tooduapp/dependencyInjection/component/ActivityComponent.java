package za.co.dstv.tooduapp.dependencyInjection.component;

import dagger.Component;
import za.co.dstv.tooduapp.dependencyInjection.PerActivity;
import za.co.dstv.tooduapp.dependencyInjection.module.ActivityModule;
import za.co.dstv.tooduapp.ui.AddItem.AddItemFragment;
import za.co.dstv.tooduapp.ui.ListItems.ListItemsFragment;
import za.co.dstv.tooduapp.ui.TodoActivity;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(TodoActivity activity);

    void inject(AddItemFragment fragment);

    void inject(ListItemsFragment fragment);
}

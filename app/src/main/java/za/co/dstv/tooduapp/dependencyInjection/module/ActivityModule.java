package za.co.dstv.tooduapp.dependencyInjection.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import za.co.dstv.tooduapp.data.model.TodoItem;
import za.co.dstv.tooduapp.dependencyInjection.ActivityContext;
import za.co.dstv.tooduapp.ui.AddItem.AddItemMainPresenter;
import za.co.dstv.tooduapp.ui.AddItem.AddItemPresenter;
import za.co.dstv.tooduapp.ui.AddItem.AddItemView;
import za.co.dstv.tooduapp.ui.ListItems.ListItemView;
import za.co.dstv.tooduapp.ui.ListItems.ListItemsMainPresenter;
import za.co.dstv.tooduapp.ui.ListItems.ListItemsPresenter;
import za.co.dstv.tooduapp.ui.ListItems.TodoAdapter;
import za.co.dstv.tooduapp.ui.TodoMainPresenter;
import za.co.dstv.tooduapp.ui.TodoPresenter;
import za.co.dstv.tooduapp.ui.TodoView;
import za.co.dstv.tooduapp.utils.SchedulerProvider;

@Module
public class ActivityModule {

    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new SchedulerProvider();
    }

    @Provides
    TodoPresenter<TodoView> provideTodoMainPresenter(
                    TodoMainPresenter<TodoView> presenter) {
        return presenter;
    }

    @Provides
    AddItemPresenter<AddItemView> provideAddItemMainPresenter(
            AddItemMainPresenter<AddItemView> presenter) {
        return presenter;
    }

    @Provides
    ListItemsPresenter<ListItemView> provideListItemMainPresenter(
            ListItemsMainPresenter<ListItemView> presenter) {
        return presenter;
    }


    @Provides
    TodoAdapter provideTodoAdapter() {
        return new TodoAdapter(new ArrayList<TodoItem>());
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(AppCompatActivity activity) {
        return new LinearLayoutManager(activity);
    }
}

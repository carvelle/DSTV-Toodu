package za.co.dstv.tooduapp.ui.ListItems;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import za.co.dstv.tooduapp.data.DataManager;
import za.co.dstv.tooduapp.data.model.TodoItem;
import za.co.dstv.tooduapp.ui.base.BasePresenter;
import za.co.dstv.tooduapp.utils.SchedulerProvider;

public class ListItemsMainPresenter<V extends ListItemView> extends BasePresenter<V>
        implements ListItemsPresenter<V> {

    @Inject
    public ListItemsMainPresenter(DataManager dataManager, CompositeDisposable compositeDisposable, SchedulerProvider schedulerProvider) {
        super(dataManager, compositeDisposable, schedulerProvider);
    }

    @Override
    public boolean deleteItem(TodoItem item) {

        getCompositeDisposable().add(getDataManager()
                .deleteTodoItem(item)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean didDelete) throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }

                        if (didDelete) {
                            refreshList();
                        }
                    }
                }));
        return false;
    }

    @Override
    public boolean updateItem(TodoItem item) {
        getCompositeDisposable().add(getDataManager()
                .updateTodoItem(item)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean didDelete) throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }

                        if (didDelete) {
                            refreshList();
                        }
                    }
                }));
        return false;
    }

    @Override
    public void onViewInitialized() {
        refreshList();
    }

    @Override
    public void refreshList() {
        getCompositeDisposable().add(getDataManager()
                .getAllTodoItems()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<List<TodoItem>>() {
                    @Override
                    public void accept(List<TodoItem> todoItems) throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }
                        if (todoItems != null) {
                            getMvpView().refreshTodoItemList(todoItems);
                            Completion(todoItems);
                        }
                    }
                }));
    }

    @Override
    public void Completion(List<TodoItem> todoItems) {

        if (todoItems != null) {
            int completed = 0;
            for (TodoItem todoItem : todoItems) {
                if (todoItem.isCompleted()) {
                    completed++;
                }
            }
            double progress = ((double)completed/(double)todoItems.size()) * 100;
            getMvpView().updateProgress((int)progress);
        }
    }
}

package za.co.dstv.tooduapp.ui.AddItem;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import za.co.dstv.tooduapp.data.DataManager;
import za.co.dstv.tooduapp.data.model.TodoItem;
import za.co.dstv.tooduapp.ui.base.BasePresenter;
import za.co.dstv.tooduapp.utils.SchedulerProvider;

public class AddItemMainPresenter<V extends AddItemView> extends BasePresenter<V>
        implements AddItemPresenter<V>  {

    @Inject
    public AddItemMainPresenter(DataManager dataManager, CompositeDisposable compositeDisposable, SchedulerProvider schedulerProvider) {
        super(dataManager, compositeDisposable, schedulerProvider);
    }

    @Override
    public boolean saveItem(TodoItem item) {

        getCompositeDisposable().add(getDataManager()
                .saveTodoItem(item)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean itemSaved) throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }
                        if (itemSaved) {
                            //getMvpView().refreshTodoItemList(todoItems);
                        }
                    }
                }));
        return false;
    }
}

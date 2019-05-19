package za.co.dstv.tooduapp.data;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import za.co.dstv.tooduapp.data.model.DaoMaster;
import za.co.dstv.tooduapp.data.model.DaoSession;
import za.co.dstv.tooduapp.data.model.TodoItem;

public class AppDatabaseHelper implements DatabaseHelper {

    private final DaoSession mDaoSession;

    @Inject
    public AppDatabaseHelper(DatabaseOpenHelper dbOpenHelper) {
        this.mDaoSession = new DaoMaster(dbOpenHelper.getWritableDb()).newSession();
    }

    @Override
    public Observable<List<TodoItem>> getAllTodoItems() {
        return Observable.fromCallable(new Callable<List<TodoItem>>() {
            @Override
            public List<TodoItem> call() throws Exception {
                return mDaoSession.getTodoItemDao().loadAll();
            }
        });
    }

    @Override
    public Observable<Boolean> isItemEmpty() {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return !(mDaoSession.getTodoItemDao().count() > 0);
            }
        });
    }

    @Override
    public Observable<Boolean> saveTodoItem(final TodoItem todoItem) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                mDaoSession.getTodoItemDao().insert(todoItem);
                return true;
            }
        });
    }

    @Override
    public Observable<Boolean> deleteTodoItem(final TodoItem todoItem) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                mDaoSession.getTodoItemDao().delete(todoItem);
                return true;
            }
        });
    }

    @Override
    public Observable<Boolean> updateTodoItem(final TodoItem todoItem) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                mDaoSession.getTodoItemDao().update(todoItem);
                return true;
            }
        });
    }

    @Override
    public Observable<Boolean> saveTodoItemList(final List<TodoItem> todoItems) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                mDaoSession.getTodoItemDao().insertInTx(todoItems);
                return true;
            }
        });
    }
}

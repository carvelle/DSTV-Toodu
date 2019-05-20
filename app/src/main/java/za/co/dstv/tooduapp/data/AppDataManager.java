package za.co.dstv.tooduapp.data;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import za.co.dstv.tooduapp.data.model.TodoItem;

public class AppDataManager implements DataManager {

    private final DatabaseHelper mDatabaseHelper;

    @Inject
    public AppDataManager(DatabaseHelper dbHelper) {
        mDatabaseHelper = dbHelper;
    }

    @Override
    public Observable<List<TodoItem>> getAllTodoItems() {
        return mDatabaseHelper.getAllTodoItems();
    }

    @Override
    public Observable<Boolean> isItemEmpty() {
        return mDatabaseHelper.isItemEmpty();
    }

    @Override
    public Observable<Boolean> saveTodoItem(TodoItem todoItem) {
        return mDatabaseHelper.saveTodoItem(todoItem);
    }

    @Override
    public Observable<Boolean> deleteTodoItem(TodoItem todoItem) {
        return mDatabaseHelper.deleteTodoItem(todoItem);
    }

    @Override
    public Observable<Boolean> updateTodoItem(TodoItem todoItem) {
        return mDatabaseHelper.updateTodoItem(todoItem);
    }

}


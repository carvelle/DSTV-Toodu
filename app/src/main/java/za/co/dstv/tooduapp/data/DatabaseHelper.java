package za.co.dstv.tooduapp.data;

import java.util.List;

import io.reactivex.Observable;
import za.co.dstv.tooduapp.data.model.TodoItem;

public interface DatabaseHelper {

    Observable<List<TodoItem>> getAllTodoItems();

    Observable<Boolean> isItemEmpty();

    Observable<Boolean> saveTodoItem(TodoItem todoItem);

    Observable<Boolean> deleteTodoItem(TodoItem todoItem);

    Observable<Boolean> updateTodoItem(TodoItem todoItem);

    Observable<Boolean> saveTodoItemList(List<TodoItem> todoItems);
}

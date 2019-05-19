package za.co.dstv.tooduapp.ui.ListItems;

import java.util.List;

import za.co.dstv.tooduapp.data.model.TodoItem;
import za.co.dstv.tooduapp.ui.base.Presenter;

public interface ListItemsPresenter<V extends ListItemView> extends Presenter<V> {

    boolean deleteItem(TodoItem item);

    boolean updateItem(TodoItem item);

    void onViewInitialized();

    void refreshList();

    void Completion(List<TodoItem> todoItems);
}

package za.co.dstv.tooduapp.ui.ListItems;

import java.util.List;

import za.co.dstv.tooduapp.data.model.TodoItem;
import za.co.dstv.tooduapp.ui.base.BaseView;

public interface ListItemView extends BaseView {

    void refreshTodoItemList(List<TodoItem> todoItems);

    void updateProgress(int progress);
}

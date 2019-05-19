package za.co.dstv.tooduapp.ui.AddItem;

import za.co.dstv.tooduapp.data.model.TodoItem;
import za.co.dstv.tooduapp.ui.base.Presenter;

public interface AddItemPresenter<V extends AddItemView> extends Presenter<V> {

    boolean saveItem(TodoItem item);
}

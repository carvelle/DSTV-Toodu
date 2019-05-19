package za.co.dstv.tooduapp.ui;

import android.support.v4.app.Fragment;

import java.util.List;

import za.co.dstv.tooduapp.data.model.TodoItem;
import za.co.dstv.tooduapp.ui.base.BaseView;

public interface TodoView extends BaseView {

    void showListItemsFragment();

    void showAddItemFragment();

    void replaceFragment(Fragment fragment);
}

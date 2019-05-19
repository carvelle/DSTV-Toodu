package za.co.dstv.tooduapp.ui.ListItems;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import za.co.dstv.tooduapp.R;
import za.co.dstv.tooduapp.data.model.TodoItem;
import za.co.dstv.tooduapp.ui.base.BaseViewHolder;

public class TodoAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE_EMPTY = 0;
    public static final int VIEW_TYPE_NORMAL = 1;

    private Callback mCallback;
    private List<TodoItem> mTodoItemList;

    private ItemClickListener mItemClickListener;

    private CheckboxCallback mCheckboxCallback;


    public TodoAdapter(List<TodoItem> todoItemList) {
        mTodoItemList = todoItemList;
    }

    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                return new ViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_item_view, parent, false));
            case VIEW_TYPE_EMPTY:
            default:
                return new EmptyViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.empty_item_view, parent, false));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mTodoItemList != null && mTodoItemList.size() > 0) {
            return VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }

    @Override
    public int getItemCount() {
        if (mTodoItemList != null && mTodoItemList.size() > 0) {
            return mTodoItemList.size();
        } else {
            return 1;
        }
    }

    public void addItems(List<TodoItem> todoItems) {
        mTodoItemList=todoItems;
        notifyDataSetChanged();
    }

    public interface Callback {
        void onEmptyViewRetryClick();
    }

    public interface CheckboxCallback {
        void onCheck(CompoundButton compoundButton, TodoItem item);
    }

    public interface ItemClickListener {
        void onItemClick(TodoItem item, int position);
    }

    public class ViewHolder extends BaseViewHolder {

        @BindView(R.id.taskTitle)
        TextView taskTitle;

        @BindView(R.id.taskSubtitle)
        TextView taskSubTitle;

        @BindView(R.id.checkBox)
        CheckBox checkBox;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        protected void clear() {

        }

        public void onBind(final int position) {
            super.onBind(position);

            final TodoItem todoItem = mTodoItemList.get(position);

            if (todoItem.getTaskName() != null) {
                taskTitle.setText(todoItem.getTaskName());
            }

            if (todoItem.isCompleted()) {
                checkBox.setChecked(true);
            }

            if (todoItem.getSubTaskName() != null) {
                taskSubTitle.setText(todoItem.getSubTaskName());
            }
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(mCheckboxCallback != null)
                        mCheckboxCallback.onCheck(compoundButton, todoItem);
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mItemClickListener != null)
                        mItemClickListener.onItemClick(todoItem, position);
                }
            });
        }
    }

    public void setItemClickListener(ItemClickListener itemClickListener){
        mItemClickListener = itemClickListener;
    }

    public void setCheckboxCallback(CheckboxCallback checkboxCallback){
        mCheckboxCallback = checkboxCallback;
    }

    public class EmptyViewHolder extends BaseViewHolder {

        @BindView(R.id.tv_message)
        TextView messageTextView;

        public EmptyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        protected void clear() {

        }

    }
}

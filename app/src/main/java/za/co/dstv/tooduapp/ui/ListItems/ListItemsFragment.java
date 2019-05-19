package za.co.dstv.tooduapp.ui.ListItems;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import za.co.dstv.tooduapp.R;
import za.co.dstv.tooduapp.data.model.TodoItem;
import za.co.dstv.tooduapp.dependencyInjection.component.ActivityComponent;
import za.co.dstv.tooduapp.ui.TodoActivity;
import za.co.dstv.tooduapp.ui.base.BaseFragment;

public class ListItemsFragment extends BaseFragment implements ListItemView{

    public static final String TAG = "ListItemsFragment";

    @Inject
    ListItemsPresenter<ListItemView> mPresenter;

    @Inject
    TodoAdapter mTodoAdapter;

    @Inject
    LinearLayoutManager mLayoutManager;

    @BindView(R.id.todoRecyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.textProgress)
    TextView textProgress;

    public static ListItemsFragment newInstance() {
        ListItemsFragment fragment = new ListItemsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.content_todo, container, false);
        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);
            //mTodoAdapter.setCallback(this);
        }
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    protected void setUp(View view) {
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mTodoAdapter.setItemClickListener(new TodoAdapter.ItemClickListener() {
            @Override
            public void onItemClick(TodoItem item, int position) {
                mPresenter.deleteItem(item);
            }
        });

        mTodoAdapter.setCheckboxCallback(new TodoAdapter.CheckboxCallback() {
            @Override
            public void onCheck(CompoundButton compoundButton, TodoItem item) {

                if(compoundButton.isChecked()){
                    item.setCompleted(true);

                }
                else{
                    item.setCompleted(false);
                }

                mPresenter.updateItem(item);
            }
        });
        mRecyclerView.setAdapter(mTodoAdapter);
        mPresenter.onViewInitialized();
    }

    @Override
    public void refreshTodoItemList(List<TodoItem> todoItems) {

        mTodoAdapter.addItems(todoItems);
    }

    @Override
    public void updateProgress(int progress) {
        progressBar.setProgress(progress);
        textProgress.setText("Done \n"+ progress +"%");
    }

    @OnClick(R.id.add_item_button)
    public void onClick(View v)
    {
        ((TodoActivity)getActivity()).showAddItemFragment();
    }
}

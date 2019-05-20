package za.co.dstv.tooduapp.ui.AddItem;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import za.co.dstv.tooduapp.R;
import za.co.dstv.tooduapp.data.model.TodoItem;
import za.co.dstv.tooduapp.dependencyInjection.component.ActivityComponent;
import za.co.dstv.tooduapp.ui.base.BaseFragment;

public class AddItemFragment extends BaseFragment  implements AddItemView{

    public static final String TAG = "AddItemFragment";

    @BindView(R.id.title)
    TextView page_title;

    @BindView(R.id.taskTitle)
    EditText task_title;

    @BindView(R.id.taskSubtitle)
    TextView task_sub_title;
    @Inject
    AddItemPresenter<AddItemView> mPresenter;
    public static AddItemFragment newInstance() {
        AddItemFragment fragment = new AddItemFragment();
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
        View view =  inflater.inflate(R.layout.fragment_add_item, container, false);
        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);
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

    }

    @OnClick(R.id.add_item_but)
    public void onClick(View v)
    {
        if(task_title.getText() != null && task_sub_title.getText() != null){

            TodoItem item = new TodoItem();

            item.setTaskName(task_title.getText().toString());
            item.setSubTaskName(task_sub_title.getText().toString());

            mPresenter.saveItem(item);
            task_sub_title.setText(null);
            task_title.setText(null);
            task_title.requestFocus();
        }
    }
}

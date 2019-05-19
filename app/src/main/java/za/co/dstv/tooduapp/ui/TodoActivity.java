package za.co.dstv.tooduapp.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import za.co.dstv.tooduapp.R;
import za.co.dstv.tooduapp.data.model.TodoItem;
import za.co.dstv.tooduapp.dependencyInjection.component.ActivityComponent;
import za.co.dstv.tooduapp.ui.AddItem.AddItemFragment;
import za.co.dstv.tooduapp.ui.ListItems.ListItemsFragment;
import za.co.dstv.tooduapp.ui.base.BaseActivity;

public class TodoActivity extends BaseActivity implements TodoView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this));
        }
        setUp();
    }

    @Override
    public void onFragmentAttached() {
    }

    @Override
    public void onFragmentDetached(String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if (fragment != null) {
            fragmentManager
                    .beginTransaction()
                    .disallowAddToBackStack()
                    .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                    .remove(fragment)
                    .commitNow();
        }
    }

    @Override
    protected void setUp() {
        showListItemsFragment();
    }

    @Override
    public void replaceFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction().replace(R.id.root_view, fragment, fragment.toString())
                .addToBackStack(fragment.toString())
                .commit();
    }

    @Override
    public void showListItemsFragment() {
        replaceFragment(ListItemsFragment.newInstance());
    }

    @Override
    public void showAddItemFragment() {
        replaceFragment(AddItemFragment.newInstance());
    }

}

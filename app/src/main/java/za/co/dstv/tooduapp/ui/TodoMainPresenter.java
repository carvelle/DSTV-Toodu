package za.co.dstv.tooduapp.ui;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import za.co.dstv.tooduapp.data.DataManager;
import za.co.dstv.tooduapp.ui.base.BasePresenter;
import za.co.dstv.tooduapp.utils.SchedulerProvider;

public class TodoMainPresenter<V extends TodoView> extends BasePresenter<V>
        implements TodoPresenter<V> {

    @Inject
    public TodoMainPresenter(DataManager dataManager, CompositeDisposable compositeDisposable, SchedulerProvider schedulerProvider) {
        super(dataManager, compositeDisposable, schedulerProvider);
    }

}
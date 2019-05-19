package za.co.dstv.tooduapp.ui.base;

public interface Presenter<V extends BaseView> {

    void onAttach(V view);

    void onDetach();
}

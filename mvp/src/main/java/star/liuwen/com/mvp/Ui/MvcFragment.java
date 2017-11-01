package star.liuwen.com.mvp.Ui;

import star.liuwen.com.mvp.Base.BaseFragment;
import star.liuwen.com.mvp.Base.BasePresenter;
import star.liuwen.com.mvp.R;

/**
 * Created by liuwen on 2017/11/1.
 */
public class MvcFragment extends BaseFragment {
    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_mvc;
    }
}

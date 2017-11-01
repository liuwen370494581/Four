package star.liuwen.com.mvp.Base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.nukc.stateview.StateView;

import star.liuwen.com.mvp.R;

/**
 * Created by liuwen on 2017/11/1.
 */
public abstract class BaseFragment<T extends BasePresenter> extends Fragment {


    protected T mPresenter;
    private View rootView;
    protected StateView mStateView;//用于显示加载中、网络异常，空布局、内容布局
    protected Activity mActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(provideContentViewId(), container, false);
            mStateView = StateView.inject(getStateViewRoot());
            if (mStateView != null) {
                mStateView.setLoadingResource(R.layout.base_loading);
                mStateView.setRetryResource(R.layout.base_retry);
                mStateView.setEmptyResource(R.layout.base_empty);
            }
            initView(rootView);
            initData();
            setListener();
        } else {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null) {
                parent.removeView(rootView);
            }
        }
        return rootView;
    }

    //用于创建Presenter和判断是否使用MVP模式(由子类实现)
    protected abstract T createPresenter();

    //得到当前界面的布局文件id(由子类实现)
    protected abstract int provideContentViewId();


    //可重写此方法
    public void initView(View view) {

    }

    public void initData() {

    }

    public void setListener() {

    }

    //默认是整个界面，如果需要变换可以重写此方法
    public View getStateViewRoot() {
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
        rootView = null;
    }
}

package star.liuwen.com.mvp.Ui;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.github.nukc.stateview.StateView;

import java.util.ArrayList;
import java.util.List;

import star.liuwen.com.mvp.Adapter.MyAdapter;
import star.liuwen.com.mvp.Base.BaseActivity;
import star.liuwen.com.mvp.Base.BasePresenter;
import star.liuwen.com.mvp.Model.wuLiuInfo;
import star.liuwen.com.mvp.Mvp.Persenter.IExpressPresenter;
import star.liuwen.com.mvp.Mvp.View.IExpressView;
import star.liuwen.com.mvp.R;

/**
 * Created by liuwen on 2017/11/1.
 */
public class MvpActivity extends BaseActivity<IExpressPresenter> implements IExpressView {

    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;


    @Override
    protected IExpressPresenter createPresenter() {
        return new IExpressPresenter(this);
    }


    @Override
    public void initView() {
        super.initView();
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mStateView.setOnRetryClickListener(new StateView.OnRetryClickListener() {
            @Override
            public void onRetryClick() {
                LoadDate();
            }
        });

    }

    @Override
    public Activity getCurrentActivity() {
        return this;
    }

    @Override
    public void initData() {
        super.initData();
        mAdapter = new MyAdapter(mRecyclerView);
        mAdapter.setData(new ArrayList<wuLiuInfo>());
        mRecyclerView.setAdapter(mAdapter);
        LoadDate();
    }

    private void LoadDate() {
        mStateView.showLoading();
        mPresenter.getExpressDate("zhongtong", "457895720220");

    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_mvp;
    }

    @Override
    public void getExpressSuccess(List<wuLiuInfo> info) {
        mAdapter.setData(info);
        mStateView.showContent();
    }

    @Override
    public void onError(String ErrorMsg) {
        mStateView.showRetry();
    }
}

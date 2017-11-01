package star.liuwen.com.mvp.Ui;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.github.nukc.stateview.StateView;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import star.liuwen.com.mvp.Adapter.MyAdapter;
import star.liuwen.com.mvp.Api.ApiRetrofit;
import star.liuwen.com.mvp.Api.SubscriberCallBack;
import star.liuwen.com.mvp.Base.BaseActivity;
import star.liuwen.com.mvp.Base.BasePresenter;
import star.liuwen.com.mvp.Model.wuLiuInfo;
import star.liuwen.com.mvp.R;

/**
 * Created by liuwen on 2017/11/1.
 * mvc的列子
 */
public class MvcActivity extends BaseActivity {

    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private CompositeSubscription mCompositeSubscription;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_mvc;
    }

    @Override
    public Activity getCurrentActivity() {
        return this;
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
    public void initData() {
        super.initData();
        mAdapter = new MyAdapter(mRecyclerView);
        mAdapter.setData(new ArrayList<wuLiuInfo>());
        mRecyclerView.setAdapter(mAdapter);
        mCompositeSubscription = new CompositeSubscription();
        LoadDate();

    }

    private void LoadDate() {
        mStateView.showLoading();
        mCompositeSubscription.
                add(ApiRetrofit.getInstance().getApiService().getExpressDate("zhongtong", "457895720220")
                        .observeOn(AndroidSchedulers.mainThread()).
                                subscribeOn(Schedulers.io()).
                                subscribe(new SubscriberCallBack<List<wuLiuInfo>>() {
                                    @Override
                                    protected void onSuccess(List<wuLiuInfo> response) {
                                        mAdapter.setData(response);
                                        mStateView.showContent();
                                    }

                                    @Override
                                    protected void onError(String e) {
                                        mStateView.showRetry();
                                    }
                                }));

    }

}

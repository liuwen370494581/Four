package star.liuwen.com.mvp.Mvp.Persenter;

import java.util.List;

import star.liuwen.com.mvp.Api.SubscriberCallBack;
import star.liuwen.com.mvp.Base.BasePresenter;
import star.liuwen.com.mvp.Model.wuLiuInfo;
import star.liuwen.com.mvp.Mvp.View.IExpressView;

/**
 * Created by liuwen on 2017/11/1.
 */
public class IExpressPresenter extends BasePresenter<IExpressView> {

    public IExpressPresenter(IExpressView view) {
        super(view);
    }

    public void getExpressDate(String express, String postId) {
        addSubscription(mApiService.getExpressDate(express, postId), new SubscriberCallBack<List<wuLiuInfo>>() {

            @Override
            protected void onSuccess(List<wuLiuInfo> response) {
                mView.getExpressSuccess(response);
            }

            @Override
            protected void onError(String e) {
                mView.onError(e);
            }
        });
    }
}

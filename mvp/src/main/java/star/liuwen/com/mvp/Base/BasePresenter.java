package star.liuwen.com.mvp.Base;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import star.liuwen.com.mvp.Api.ApiRetrofit;
import star.liuwen.com.mvp.Api.ApiService;

/**
 * Created by liuwen on 2017/11/1.
 */
public abstract class BasePresenter<V> {

    protected ApiService mApiService = ApiRetrofit.getInstance().getApiService();
    protected V mView;
    private CompositeSubscription mCompositeSubscription;//这里可以写在BaseFragment中和BaseActivity中

    public BasePresenter(V view) {
        attachView(view);
    }

    public void attachView(V view) {
        mView = view;
    }

    public void detachView() {
        mView = null;
        cancelSubscription();//取消观察者 避免内存泄漏

    }


    public void addSubscription(Observable observable, Subscriber subscriber) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber));
    }


    public void cancelSubscription() {
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }
}



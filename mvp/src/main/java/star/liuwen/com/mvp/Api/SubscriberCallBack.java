package star.liuwen.com.mvp.Api;

import android.text.TextUtils;

import rx.Subscriber;
import star.liuwen.com.mvp.Model.ResultResponse;


/**
 * Created by liuwen on 2017/11/1.
 * 封装的callback 返回的数据在此验证了 在这里就以快递的返回参数为模板来设置
 */
public abstract class SubscriberCallBack<T> extends Subscriber<ResultResponse<T>> {


    @Override
    public void onNext(ResultResponse response) {
        boolean isSuccess = (!TextUtils.isEmpty(response.message) && response.message.equals("ok"));
        if (isSuccess) {
            onSuccess((T) response.data);
        } else {
            onFailure(response);
        }

    }

    @Override
    public void onError(Throwable e) {
        e.getLocalizedMessage();
        onError(e.getLocalizedMessage());
    }

    @Override
    public void onCompleted() {

    }

    protected abstract void onSuccess(T response);

    protected abstract void onError(String e);

    protected void onFailure(ResultResponse response) {

    }
}

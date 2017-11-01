package star.liuwen.com.mvp.Mvp.View;

import java.util.List;

import star.liuwen.com.mvp.Model.wuLiuInfo;

/**
 * Created by liuwen on 2017/11/1.
 */
public interface IExpressView {
    void getExpressSuccess(List<wuLiuInfo> info);

    void onError(String ErrorMsg);
}

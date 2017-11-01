package star.liuwen.com.mvp.Base;

import android.app.Application;
import android.content.Context;

/**
 * Created by liuwen on 2017/11/1.
 */
public class MyApp extends Application {
    private static Context mContext;//上下文

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getContext() {
        return mContext;
    }

}

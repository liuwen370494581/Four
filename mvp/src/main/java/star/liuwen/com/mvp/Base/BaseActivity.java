package star.liuwen.com.mvp.Base;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.chaychan.lib.SlidingLayout;
import com.github.nukc.stateview.StateView;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import star.liuwen.com.mvp.MainActivity;
import star.liuwen.com.mvp.R;

/**
 * Created by liuwen on 2017/11/1.
 */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {


    protected T mPresenter;
    private static long mPreTime;
    private static Activity mCurrentActivity;//对所有的Activity进行管理
    public static List<Activity> mActivity = new ArrayList<>();
    protected Bundle saveInstanceState;
    protected StateView mStateView;//进行布局管理 无网络 无数据 正在加载
    private PermissionListener mPermissionListener;
    private View mContextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (enableSlideClose()) {
            SlidingLayout rootView = new SlidingLayout(this);
            rootView.bindActivity(this);
        }

        mContextView = LayoutInflater.from(this).inflate(provideContentViewId(), null);
        setContentView(mContextView);
        mStateView = StateView.inject(getCurrentActivity());//注入
        if (mStateView != null) {
            mStateView.setLoadingResource(R.layout.base_loading);
            mStateView.setEmptyResource(R.layout.base_empty);
            mStateView.setRetryResource(R.layout.base_retry);
            //这里的资源可以替换成你所需要的资源
        }

        this.saveInstanceState = savedInstanceState;
        //初始化的时候将其添加到集合当中
        synchronized (mActivity) {
            mActivity.add(this);
        }

        mPresenter = createPresenter();
        //子类不再需要设置布局ID，也不再需要使用ButterKnife.bind()
        // ButterKnife.bind(this);
        initView();
        initData();
        initListener();
    }


    public void initView() {
    }

    public void initData() {
    }

    public void initListener() {
    }


    //用于创建Presenter和判断是否使用MVP模式(由子类实现)
    protected abstract T createPresenter();

    public boolean enableSlideClose() {
        return true;
    }

    //得到当前界面的布局文件id(由子类实现)
    protected abstract int provideContentViewId();

    @Override
    protected void onResume() {
        super.onResume();
        mCurrentActivity = this;
    }

    @Override
    protected void onPause() {
        super.onPause();
        mCurrentActivity = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //销毁的时候从集合中移除
        synchronized (mActivity) {
            mActivity.remove(this);
        }

        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }


    public static void exitApp() {

        ListIterator<Activity> iterator = mActivity.listIterator();

        while (iterator.hasNext()) {
            Activity next = iterator.next();
            next.finish();
        }
    }


    //在activity中需要重写这个布局 返回this 即可
    public Activity getCurrentActivity() {
        return mCurrentActivity;
    }


    @Override
    public void onBackPressed() {
        if (mCurrentActivity instanceof MainActivity) {
            //如果是主页面
            if (System.currentTimeMillis() - mPreTime > 2000) {// 两次点击间隔大于2秒
//                UIUtils.showToast("再按一次，退出应用");
                mPreTime = System.currentTimeMillis();
                return;
            }
        }
        super.onBackPressed();// finish()
    }

    /**
     * 申请运行时权限
     */
    public void requestRuntimePermission(String[] permissions, PermissionListener permissionListener) {
        mPermissionListener = permissionListener;
        List<String> permissionList = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(permission);
            }
        }

        if (!permissionList.isEmpty()) {
            ActivityCompat.requestPermissions(this, permissionList.toArray(new String[permissionList.size()]), 1);
        } else {
            permissionListener.onGranted();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    List<String> deniedPermissions = new ArrayList<>();
                    for (int i = 0; i < grantResults.length; i++) {
                        int grantResult = grantResults[i];
                        String permission = permissions[i];
                        if (grantResult != PackageManager.PERMISSION_GRANTED) {
                            deniedPermissions.add(permission);
                        }
                    }
                    if (deniedPermissions.isEmpty()) {
                        mPermissionListener.onGranted();
                    } else {
                        mPermissionListener.onDenied(deniedPermissions);
                    }
                }
                break;
        }
    }


}

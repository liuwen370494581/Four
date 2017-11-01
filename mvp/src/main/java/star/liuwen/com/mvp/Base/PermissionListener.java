package star.liuwen.com.mvp.Base;

import java.util.List;

/**
 * Created by liuwen on 2017/11/1.
 */
public interface PermissionListener {

    void onGranted();

    void onDenied(List<String> deniedPermissions);
}

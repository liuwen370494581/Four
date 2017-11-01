package star.liuwen.com.mvp.Model;

/**
 * Created by liuwen on 2017/11/1.
 */
public class wuLiuInfo {

    private String time;
    private String ftime;
    private String context;


    @Override
    public String toString() {
        return "wuLiuInfo{" +
                "time='" + time + '\'' +
                ", ftime='" + ftime + '\'' +
                ", context='" + context + '\'' +
                '}';
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFtime() {
        return ftime;
    }

    public void setFtime(String ftime) {
        this.ftime = ftime;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}

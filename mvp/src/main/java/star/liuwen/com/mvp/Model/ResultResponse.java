package star.liuwen.com.mvp.Model;

/**
 * Created by liuwen on 2017/11/1.
 * 这个是服务器统一返回的数据 要和服务器那边协商  一般接口都是按此规格来返回的
 */
public class ResultResponse<T> {

    /**
     * message : ok
     * nu : 457895720220
     * ischeck : 1
     * condition : F00
     * com : zhongtong
     * status : 200
     * state : 3
     * data : [{"time":"2017-10-14 11:43:19","ftime":"2017-10-14 11:43:19","context":"[广州市] [广州科学城]的派件已签收 感谢使用中通快递,期待再次为您服务!","location":"广州科学城"},{"time":"2017-10-14 11:35:28","ftime":"2017-10-14 11:35:28","context":"[广州市] [广州科学城]的何如林正在第1次派件 电话:18620732541 请保持电话畅通、耐心等待","location":"广州科学城"},{"time":"2017-10-14 08:10:05","ftime":"2017-10-14 08:10:05","context":"[广州市] 快件到达 [广州科学城]","location":"广州科学城"},{"time":"2017-10-14 04:07:24","ftime":"2017-10-14 04:07:24","context":"[广州市] 快件离开 [广州中心]已发往[广州科学城]","location":"广州中心"},{"time":"2017-10-12 23:21:16","ftime":"2017-10-12 23:21:16","context":"[成都市] 快件离开 [成都中转]已发往[广州中心]","location":"成都中转"},{"time":"2017-10-12 23:19:18","ftime":"2017-10-12 23:19:18","context":"[成都市] 快件到达 [成都中转]","location":"成都中转"},{"time":"2017-10-12 22:54:29","ftime":"2017-10-12 22:54:29","context":"[成都市] 快件离开 [成都市场部]已发往[成都中转]","location":"成都市场部"},{"time":"2017-10-12 10:21:38","ftime":"2017-10-12 10:21:38","context":"[成都市] [成都市场部]的盈棚食品已收件 电话:暂无","location":"成都市场部"}]
     */
    public String message;
    public String nu;
    public String ischeck;
    public String condition;
    public String com;
    public String status;
    public String state;
    public T data;

    public ResultResponse(String message, String nu, String ischeck, String condition, String com, String status, String state, T data) {
        this.message = message;
        this.nu = nu;
        this.ischeck = ischeck;
        this.condition = condition;
        this.com = com;
        this.status = status;
        this.state = state;
        this.data = data;
    }
}

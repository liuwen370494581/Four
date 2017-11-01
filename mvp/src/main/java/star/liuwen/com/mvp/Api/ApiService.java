package star.liuwen.com.mvp.Api;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import star.liuwen.com.mvp.Model.ResultResponse;
import star.liuwen.com.mvp.Model.wuLiuInfo;


/**
 * Created by liuwen on 2017/11/1.
 */
public interface ApiService {

    //http://www.kuaidi100.com/query?type=zhongtong&postid=418271182599
    @GET("query")
    Observable<ResultResponse<List<wuLiuInfo>>> getExpressDate(@Query("type") String type, @Query("postid") String postid);
}

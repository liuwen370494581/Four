package star.liuwen.com.mvp.Adapter;

import android.support.v7.widget.RecyclerView;

import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;
import star.liuwen.com.mvp.Model.wuLiuInfo;
import star.liuwen.com.mvp.R;

/**
 * Created by liuwen on 2017/11/1.
 */

public class MyAdapter extends BGARecyclerViewAdapter<wuLiuInfo> {

    public MyAdapter(RecyclerView recyclerView) {
        super(recyclerView, R.layout.item_test);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, wuLiuInfo model) {
        helper.setText(R.id.tv_content, model.getContext());
    }
}

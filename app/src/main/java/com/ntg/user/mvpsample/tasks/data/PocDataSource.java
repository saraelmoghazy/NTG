package com.ntg.user.mvpsample.tasks.data;

import java.util.List;

/**
 * Created by mohamed yassin on 1/29/2018.
 */

public interface PocDataSource {
    interface GetPocsCallBack {
        void onPocsLoaded(List<Poc> pocList);

        void onPocsFailed(String errMsg);
    }
    void getPocs(GetPocsCallBack getPocsCallBack);
}

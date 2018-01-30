package com.ntg.user.mvpsample.poc.data.local;



import com.ntg.user.mvpsample.poc.data.Poc;
import com.ntg.user.mvpsample.poc.data.PocDataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 6/14/2017.
 */

public class LocalPocRepo implements PocDataSource {
    private static LocalPocRepo instance;
    List<Poc> pocList = new ArrayList<>();

    @Override
    public void getPocs(PocDataSource.GetPocsCallBack callBack) {
        if (pocList.size() < 1) {
            Poc poc = new Poc();
            poc.setTitle("title1");
            poc.setCompleted(true);

            Poc poc2 = new Poc();
            poc2.setTitle("title2");
            poc2.setCompleted(true);

            Poc poc3 = new Poc();
            poc3.setTitle("title3");
            poc3.setCompleted(false);

            pocList.add(poc);
            pocList.add(poc2);
            pocList.add(poc3);

            callBack.onPocsFailed("Empty List");
        }
        callBack.onPocsLoaded(pocList);

    }


    private LocalPocRepo() {
    }

    public static LocalPocRepo getInstance() {
        if (instance == null)
            instance = new LocalPocRepo();

        return instance;

    }
}

package com.ntg.user.mvpsample.tasks.data;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by mohamed yassin on 1/29/2018.
 */

public class PocRepository implements PocDataSource {

    private boolean mCacheDirty = false;
    List<Poc> cachedPocs = new ArrayList<>();
    private final PocDataSource localPocRepository;
    private final PocDataSource remotePocRepository;
    private static PocRepository instance = null;


    private PocRepository(PocDataSource localPocRepository, PocDataSource remotePocRepository) {
        checkNotNull(localPocRepository);
        checkNotNull(remotePocRepository);

        this.localPocRepository = localPocRepository;
        this.remotePocRepository = remotePocRepository;
    }

    public static PocRepository getInstance(PocDataSource localPocRepository, PocDataSource remotePocRepository) {
        if (instance == null)
            instance = new PocRepository(localPocRepository, remotePocRepository);
        return instance;
    }


    /*
    * User to force create new instance
    * */
    public static void destroyInstance() {
        instance = null;
    }

    @Override
    public void getPocs(final GetPocsCallBack callBack) {

// allow to get from cache , mCacheDirty indicate that user want to force update so makes cache dirty
        if (cachedPocs != null && !ismCacheDirty())
            callBack.onPocsLoaded(cachedPocs);

        // case first load or user request refresh
        if (ismCacheDirty()) {
            getRemotePocs(callBack);
        } else {
            getLocalPocs(callBack);
        }
    }


    private void getLocalPocs(final GetPocsCallBack callBack) {
        localPocRepository.getPocs(new GetPocsCallBack() {
            @Override
            public void onPocsLoaded(List<Poc> pocList) {
                callBack.onPocsLoaded(pocList);
            }

            @Override
            public void onPocsFailed(String errMsg) {
                getRemotePocs(callBack);
            }
        });
    }


    private void getRemotePocs(final GetPocsCallBack callBack) {
        remotePocRepository.getPocs(new GetPocsCallBack() {
            @Override
            public void onPocsLoaded(List<Poc> pocList) {
                // refresh cached pocs
                refreshCache(pocList);
                callBack.onPocsLoaded(cachedPocs);

            }

            @Override
            public void onPocsFailed(String errMsg) {
                callBack.onPocsFailed(errMsg);

            }
        });
    }


    public void refreshPocs() {
        setmCacheDirty(true);
    }


    private void refreshCache(List<Poc> pocList) {
        cachedPocs.clear();
        cachedPocs.addAll(pocList);
        setmCacheDirty(false);
    }

    public boolean ismCacheDirty() {
        return mCacheDirty;
    }

    public void setmCacheDirty(boolean mCacheDirty) {
        this.mCacheDirty = mCacheDirty;
    }
}

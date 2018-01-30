package com.ntg.user.mvpsample.poc;


import com.ntg.user.mvpsample.poc.data.Poc;
import com.ntg.user.mvpsample.poc.data.PocDataSource;
import com.ntg.user.mvpsample.poc.data.PocRepository;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class PocPresenter implements PocContract.IPocPresenter {

    private final PocRepository mPocRepository;
    private final PocContract.IPocView mPocIPocView;
    private boolean isFirstLoad = true;


    @Override
    public void getPocs(boolean forceUpdate) {
        mPocIPocView.showLoadingIndicator(true);

        // force update when refresh or first load
        if (forceUpdate || isFirstLoad) {
            mPocRepository.refreshPocs();
            isFirstLoad = false;
        }
        mPocRepository.getPocs(new PocDataSource.GetPocsCallBack() {
            @Override
            public void onPocsLoaded(List<Poc> pocList) {
                mPocIPocView.showLoadingIndicator(false);
                if (pocList == null || pocList.size() < 1)
                    mPocIPocView.showNoPocs();
                else {
                    mPocIPocView.showPocs(pocList);
                }
                mPocIPocView.showLoadingIndicator(false);
            }

            @Override
            public void onPocsFailed(String errMsg) {
                mPocIPocView.showLoadingIndicator(false);
                mPocIPocView.showMessageError(errMsg);
            }
        });
    }

    @Override
    public void start() {
        getPocs(false);

    }


    public PocPresenter(PocRepository mPocRepository, PocContract.IPocView mPocIPocView) {
        this.mPocRepository = checkNotNull(mPocRepository, "mPocRepository cannot be null");
        this.mPocIPocView = checkNotNull(mPocIPocView, "mPocIPocView can't be null");
        mPocIPocView.setPresenter(this);
    }
}

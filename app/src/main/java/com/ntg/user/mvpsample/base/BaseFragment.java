package com.ntg.user.mvpsample.base;

import android.app.Dialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.widget.Toast;

import com.ntg.user.mvpsample.R;

/**
 * Created by Sara Elmoghazy on 19/02/2018.
 */

public class BaseFragment extends Fragment implements BaseView {
    private Dialog loadingIndicator = null;

    @Override
    public void showLoadingIndicator() {
        if (loadingIndicator == null && isAdded()) {
            loadingIndicator = new Dialog(getActivity());
            loadingIndicator.requestWindowFeature(Window.FEATURE_NO_TITLE);
            loadingIndicator.setContentView(R.layout.partial_blocking_loading_indicator);
            loadingIndicator.setCancelable(false);
            loadingIndicator.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            loadingIndicator.show();
        }
    }

    @Override
    public void hideLoadingIndicator() {
        if (loadingIndicator != null) {
            loadingIndicator.dismiss();
            loadingIndicator = null;
        }
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }
}

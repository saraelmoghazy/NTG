package com.ntg.user.mvpsample.base;

import android.app.Dialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.widget.Toast;

import com.ntg.user.mvpsample.R;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

/**
 * Acts as base class for all fragments in the app
 *
 * @author Sara Elmoghazy
 */
public class BaseFragment extends Fragment implements BaseView {
    private Dialog loadingIndicator = null;

    /**
     * Display progress that indicates loading process is in progress.
     */
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

    /**
     * Hide previously displayed progress indicator to indicates that the process was finished.
     */
    @Override
    public void hideLoadingIndicator() {
        if (loadingIndicator != null) {
            loadingIndicator.dismiss();
            loadingIndicator = null;
        }
    }

    /**
     * Handle common errors
     *
     * @param message
     */
    @Override
    public void showErrorMessage(String message) {
        Crouton.makeText(getActivity(), message, Style.ALERT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Crouton.cancelAllCroutons();
    }
}

package com.ntg.user.mvpsample.base;

import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;

import com.ntg.user.mvpsample.R;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

/**
 * @author Sara Elmoghazy
 */
public class BaseDialogFragment extends DialogFragment implements BaseView {
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
        Crouton.makeText(getActivity(), message, Style.ALERT).show();
        dismiss();
    }
}

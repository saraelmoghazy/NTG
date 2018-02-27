package com.ntg.user.mvpsample.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.UnderlineSpan;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.ntg.user.mvpsample.R;

/**
 * Utility class for views.
 * Created by Sahar Almohamady on 9/26/2017.
 */

public class ViewUtility {

    /**
     * Add shadow to view by setting background in pre LOLLIPOP devices otherwise add elevation
     *
     * @param context context
     * @param view    the view to add shadow for it.
     */
    public static void addShadowToView(Context context, View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view.setElevation(context.getResources().getDimension(R.dimen.card_recycler_elevation));
        } else {
            //store view padding and restore them as setBackground overrides the padding attributes.
            int paddingBottom = view.getPaddingBottom();
            int paddingStart = view.getPaddingStart();
            int paddingEnd = view.getPaddingEnd();
            int paddingTop = view.getPaddingTop();
            view.setBackground(ContextCompat.getDrawable(context, R.drawable.card_bg));
            view.setPadding(paddingStart, paddingTop, paddingEnd, paddingBottom);
        }
    }

}
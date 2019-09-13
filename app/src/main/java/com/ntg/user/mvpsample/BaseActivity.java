package com.ntg.user.mvpsample;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by GM7 on 2/8/2018.
 */

public class BaseActivity extends AppCompatActivity {

    public void dialog(Context context) {
        ProgressDialog pd = new ProgressDialog(context);
        pd.setMessage("loading");
        pd.show();
        new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
            pd.dismiss();

        }).start();
    }


}

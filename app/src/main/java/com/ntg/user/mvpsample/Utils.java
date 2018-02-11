package com.ntg.user.mvpsample;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.List;
/**
 * Created by GM7 on 2/6/2018.
 */

public class Utils {
    public static int percentage(List<Integer> integers){
        Integer sum = 0;
        if (integers !=null){
            for (Integer integer : integers){
                sum+=integer;
            }
        }
        if(integers.size() ==0){
            return 0;
        }else {
            return (sum/integers.size()) ;
        }
    }
    public static boolean isNetworkAvailable(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if ((activeNetworkInfo != null)&&(activeNetworkInfo.isConnected())){
            return true;
        }else{
            return false;
        }
}}

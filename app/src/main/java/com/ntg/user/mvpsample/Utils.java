package com.ntg.user.mvpsample;

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
}

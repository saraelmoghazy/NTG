package com.ntg.user.mvpsample.utils;

import java.util.List;

/**
 * Created by ilias on 01/02/2018.
 */

public class MathUtil {
    public static double average(List<Integer> integers) {
        Integer sum = 0;
        if (integers != null && !integers.isEmpty()) {
            for (Integer integer : integers) {
                sum += integer;
            }
            return sum.doubleValue() / integers.size();
        }
        return sum;
    }

    public int sum(int s1, int s2){
        return s1 + s2;
    }

    public int diff(int s1, int s2){
        return s1 - s2;
    }

    public double div(double d1, double d2){
//        if (d2 == 0) return 0;
        return d1 / d2;
    }
}

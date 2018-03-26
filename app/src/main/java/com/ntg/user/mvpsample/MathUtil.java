package com.ntg.user.mvpsample;

import java.util.List;

/**
 * Created by mohamed yassin on 2/4/2018.
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
}

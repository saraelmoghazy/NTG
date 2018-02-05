package com.ntg.user.mvpsample.tasks;

import java.util.List;

/**
 * Created by ilias on 01/02/2018.
 */

public class CalculatAverage {
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

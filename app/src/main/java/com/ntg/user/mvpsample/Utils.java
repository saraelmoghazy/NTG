package com.ntg.user.mvpsample;

import java.util.List;

public class Utils {
    public static int getAverage(List<Integer> integers) {
        Integer sum = 0;
        if (integers != null) {
            for (Integer integer : integers) {
                sum += integer;
            }
        }
        if (integers.size() == 0) {
            return 0;
        } else {
            return (sum / integers.size());
        }
    }
}

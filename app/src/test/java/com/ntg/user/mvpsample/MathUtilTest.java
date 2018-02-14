package com.ntg.user.mvpsample;

import com.ntg.user.mvpsample.utils.MathUtil;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by ilias on 07/02/2018.
 */

public class MathUtilTest {

    static MathUtil mathUtil;

    @BeforeClass
    public static void before(){
        mathUtil = new MathUtil();
        System.out.println("before class");
    }

    @AfterClass
    public static void after(){
        System.out.println("after class");
    }

    @Before
    public void setup(){

        System.out.println("setup");
    }

    @After
    public void cleanup(){
        System.out.println("clean up");
    }

    @Test
    public void testAdd(){
        int sum = mathUtil.sum(3,4);
        Assert.assertEquals(7, sum);
    }

    @Test
    public void testDiv(){
        double result = mathUtil.div(9, 3);
        Assert.assertEquals(3.0, result, 0.0);
    }


}

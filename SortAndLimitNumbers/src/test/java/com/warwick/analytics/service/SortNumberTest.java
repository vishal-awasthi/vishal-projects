package com.warwick.analytics.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class SortNumberTest {
    private SortNumbers sortNumbers;

    @Before
    public void setUp() {
        sortNumbers = new SortNumbers();
    }

    @After
    public void cleanUp() {
        sortNumbers = null;
    }

    @Test
    public void testQuickSortSuccessCase()
    {
        double[] testArray = new double[]{.2, .3, .67, .1, .78, 0.0};
        double[] expectedArray = new double[]{0.0, .1, .2, .3, .67, .78};

        sortNumbers.quickSort(testArray, 0, testArray.length - 1);

        Assert.assertArrayEquals(expectedArray, testArray, 0.0);
    }
}

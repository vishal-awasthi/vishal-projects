package com.warwick.analytics.service;


import com.warwick.analytics.exception.ApplicationException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class UtilityServiceTest
{
    private ManageNumbersService utility;

    @Before
    public void setUp() {
        utility = new ManageNumbersService();
    }

    @After
    public void cleanUp() {
        utility = null;
    }

    @Test
    public void testManageNumbersSuccessCase()
    {
        String sourceFilePath = "src/main/resources/summary.csv";
        String destinationFile = "summary_result.csv";
        double min_value = 0.2;
        int limit = 4;
        String expectedFirstLine = "[ID:1.0, C8:0.207027015, C2:0.277829791, :0.406847988]";

        // Performing the test
        utility.manageNumbers(sourceFilePath, min_value, limit);

        // Verifying the results
        try (BufferedReader csvReader = new BufferedReader(new FileReader(destinationFile)))
        {
            Assert.assertEquals(expectedFirstLine, csvReader.readLine());
        } catch (IOException exception) {
            exception.printStackTrace();
            throw new ApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getLocalizedMessage());
        }
    }

}
package com.netpay.SaveFileSystem.FormValidation.backend.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/*
* Test class to test data insertion and fetching of File system paths
* This class is for demo purpose hence not included exception scenarios
* */
public class ManageFileSystemTest
{
    private ManageFileSystem manageFileSystem;

    @Before
    public void setUp() {
        manageFileSystem = new ManageFileSystem();
    }

    @After
    public void cleanUp() {
        manageFileSystem = null;
    }

    @Test
    public void testInsertFilesSuccessCase() {
        boolean insertStatus = manageFileSystem.insertFiles("src/main/resources/File system.csv");
        Assert.assertTrue(insertStatus);
    }

    @Test
    public void testGetFilePathsSuccessCase()
    {
        List<String> filePathsList;

        // Verify results for search keyword : Documen
        filePathsList =  manageFileSystem.getFilePaths("Documen").getFileLocations();

        Assert.assertTrue(filePathsList.contains("C:\\Documents\\images"));
        Assert.assertTrue(filePathsList.contains("C:\\Documents\\images\\Image1.jpg"));
        Assert.assertTrue(filePathsList.contains("C:\\Documents\\Images\\Image2.jpg"));
        Assert.assertTrue(filePathsList.contains("C:\\Documents\\Images\\Image3.jpg"));
        Assert.assertTrue(filePathsList.contains("C:\\Documents\\Works"));
        Assert.assertTrue(filePathsList.contains("C:\\Documents\\Works\\Letter.doc"));
        Assert.assertTrue(filePathsList.contains("C:\\Documents\\Works\\Accountant"));
        Assert.assertTrue(filePathsList.contains("C:\\Documents\\Works\\Accountant\\AnnualReport.xls"));

        Assert.assertFalse(filePathsList.contains("C:\\Program Files\\Skype"));
        Assert.assertFalse(filePathsList.contains("C:\\Program Files\\Mysql"));


        // Verify results for search keyword : Image
        filePathsList =  manageFileSystem.getFilePaths("Image").getFileLocations();

        Assert.assertTrue(filePathsList.contains("C:\\Documents\\images"));
        Assert.assertTrue(filePathsList.contains("C:\\Documents\\images\\Image1.jpg"));
        Assert.assertTrue(filePathsList.contains("C:\\Documents\\Images\\Image2.jpg"));
        Assert.assertTrue(filePathsList.contains("C:\\Documents\\Images\\Image3.jpg"));

        Assert.assertFalse(filePathsList.contains("C:\\Documents\\Works\\Letter.doc"));
        Assert.assertFalse(filePathsList.contains("C:\\Program Files\\Mysql"));
    }
}

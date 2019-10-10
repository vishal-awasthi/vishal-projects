package com.netpay.SaveFileSystem.FormValidation.backend.service;

import com.netpay.SaveFileSystem.FormValidation.backend.dao.PersistDataDAO;
import com.netpay.SaveFileSystem.FormValidation.backend.data.FileLocationsRequest;
import com.netpay.SaveFileSystem.FormValidation.backend.data.FileLocationsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ManageFileSystem
{
    private Logger logger = LoggerFactory.getLogger(ManageFileSystem.class);

    public boolean insertFiles(String sourceFilePath) {
        String singleLine;
        List<FileLocationsRequest> paths = new ArrayList<>();
        boolean dataPersisted = false;

        try(BufferedReader csvReader = new BufferedReader(new FileReader(sourceFilePath)))
        {
            while ((singleLine = csvReader.readLine()) != null)
            {
                String[] filePaths = singleLine.split(",");
                for(int i=0; i<filePaths.length; i++) {
                    String filePath = filePaths[i] ;
                    paths.add(new FileLocationsRequest(filePath));
                }
            }
            csvReader.close();
            // Calling the DAO and saving the object
            dataPersisted = new PersistDataDAO().SaveFilePaths(paths);
        } catch (IOException ioException) {
            logger.error("Exception in insertFiles - "+ ioException);
        }
        return dataPersisted;
    }

    public FileLocationsResponse getFilePaths(String keyword)
    {
        List<String> fileLocations = new ArrayList<>();
        List<FileLocationsRequest> fileLocationsList =  new PersistDataDAO().GetFilePaths(keyword);

        for(FileLocationsRequest fileLocationsRequest: fileLocationsList)
            fileLocations.add(fileLocationsRequest.getPath());

        FileLocationsResponse fileLocationsResponse = new FileLocationsResponse();
        fileLocationsResponse.setFileLocations(fileLocations);

        return fileLocationsResponse;
    }
}

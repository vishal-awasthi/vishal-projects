package com.netpay.SaveFileSystem.FormValidation.backend.controller;

import com.netpay.SaveFileSystem.FormValidation.backend.data.FileLocationsResponse;
import com.netpay.SaveFileSystem.FormValidation.backend.service.ManageFileSystem;
import com.netpay.SaveFileSystem.FormValidation.backend.utilities.ResourceURIConstant;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ResourceURIConstant.FILES)
@Api(value = ResourceURIConstant.FILES)
@CrossOrigin
public class ManageFilePathsController
{
    @Autowired
    private ManageFileSystem fileSystemService;

    private Logger logger =  LoggerFactory.getLogger(ManageFilePathsController.class);

    @GetMapping(value = ResourceURIConstant.GET_PATHS)
    public @ResponseBody String getFilePaths(@RequestParam String keyword)
    {
        logger.info("Processing get file locations Request");

        logger.info("Inserting the file structure in DB");
        fileSystemService.insertFiles("src/main/resources/File system.csv");

        FileLocationsResponse fileLocationsResponse = fileSystemService.getFilePaths(keyword);
        return fileLocationsResponse.toString();
    }

}

package com.warwick.analytics.controller;


import com.warwick.analytics.service.ManageNumbersService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utilities.ResourceURIConstant;


@RestController
@RequestMapping(ResourceURIConstant.MANAGE_NUMBERS)
@Api(value = ResourceURIConstant.MANAGE_NUMBERS)
@CrossOrigin
public class ManageNumbersController
{
    @Autowired
    private ManageNumbersService manageNumbersService;

    private Logger logger =  LoggerFactory.getLogger(ManageNumbersController.class);


    @GetMapping(value = ResourceURIConstant.SORT_AND_LIMIT)
    public String sortAndLimitNumbers()
    {
        logger.info("Processing manage numbers Request");

        // These values will come as request body parameters
        // Here they are hard coded for demo
        String sourceFileLocation = "src/main/resources/summary.csv";
        double min_value = 0.2;
        int limit = 4;

        return manageNumbersService.manageNumbers(sourceFileLocation, min_value, limit);
    }


}

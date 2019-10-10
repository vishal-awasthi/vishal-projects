package com.warwick.analytics.service;

import com.warwick.analytics.exception.ApplicationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

@Component
public class ManageNumbersService
{
    private Logger logger =  LoggerFactory.getLogger(ManageNumbersService.class);
    /*
     *This is the main function which is called from the client / Junit class
     * */
    public String manageNumbers(String sourceFilePath, double min_value, int limit)
    {
        String singleLine;
        String destinationFile = "summary_result.csv";

        try(BufferedReader csvReader = new BufferedReader(new FileReader(sourceFilePath));
            FileWriter csvWriter = new FileWriter(destinationFile))
        {
            String[] headers = csvReader.readLine().split(",");
            // Iterating through each row
            while ((singleLine = csvReader.readLine()) != null)
            {
                String[] numbersStrings = singleLine.split(",");

                // Converting the Strings to doubles
                double[] numberDoubles = convertNumberStringsToDouble(numbersStrings);

                // Removing values which are less than min-value
                double finalArray[] = removeExtraValues(numberDoubles, min_value);

                // Adding in map to link column names with respective values
                Map<String, Double> valueMap = addInMap(headers, finalArray);

                // Performing quick sort on values
                new SortNumbers().quickSort(finalArray, 1, finalArray.length-1);

                // Limiting the values according to provided limit and concatinating with column header eg- C4:0.2534578
                numbersStrings = prepareFinalValues(finalArray, limit, valueMap);

                // Adding the new row into the destination file
                csvWriter.append(Arrays.toString(numbersStrings));
                csvWriter.append("\n");

                // Logging the new row into the console
                logger.info(Arrays.toString(numbersStrings));
                logger.info("\n");
            }
        } catch (IOException exception) {
            logger.error("Exception in accessing files - "+ exception);
            exception.printStackTrace();
            throw new ApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getLocalizedMessage());
        }

        logger.info("Numbers task completed successfully");
        // This message is returned to the user if no exception comes and code executes successfully
        return "Numbers task completed successfully. ";
    }

    private String[] prepareFinalValues(double[] finalArray, int limit, Map<String, Double> valueMap)
    {
        String[] finalStringValue = new String[limit];
        for (int i=0; i < limit ;i ++){
            finalStringValue[i] = getKey(finalArray[i], valueMap)+ ":" + finalArray[i];
         }
        return finalStringValue;
    }

    private String getKey(double value, Map<String, Double> valueMap)
    {
        for (Map.Entry<String, Double> entry : valueMap.entrySet()) {
            if(entry.getValue() == value){
                valueMap.remove(entry.getKey());
                return entry.getKey();
            }
        }
        return "";
    }

    private double[] removeExtraValues(double[] numberDoubles, double min_value)
    {
        List<Double> finalList = new ArrayList<>();
        double[] finalArray ;

        for (int i=0 ; i< numberDoubles.length ; i++){
            if (numberDoubles[i] >= min_value)
                finalList.add(numberDoubles[i]);
        }
        finalArray = new double[finalList.size()];
        for(int j=0;j<finalArray.length ; j++){
            finalArray[j] = finalList.get(j);
        }
        return finalArray;
    }

    private double[] convertNumberStringsToDouble(String[] numbersStrings)
    {
        double[] doubleArray = new double[numbersStrings.length];
        for (int i = 0; i< numbersStrings.length ; i++)
            doubleArray[i] = Double.parseDouble(numbersStrings[i]);

        return doubleArray;
    }

    private Map<String, Double> addInMap(String[] headers, double[] numbers)
    {
        Map<String, Double> valueMap = new HashMap<>();
        valueMap = new HashMap<>();
        for (int i=0; i < numbers.length-1 ; i++)
            valueMap.put(headers[i], numbers[i]);

        return valueMap;
    }
}

package com.warwick.analytics.service;

import org.springframework.stereotype.Component;

@Component
public class SortNumbers
{
    public void quickSort(double[] array, int begin, int end)
    {
        if (end <= begin) return;
        int pivot = partition(array, begin, end);
        quickSort(array, begin, pivot-1);
        quickSort(array, pivot+1, end);
    }

    private int partition(double[] array, int begin, int end)
    {
        int pivot = end;

        int counter = begin;
        for (int i = begin; i < end; i++) {
            if (array[i] < array[pivot]) {
                double temp = array[counter];
                array[counter] = array[i];
                array[i] = temp;
                counter++;
            }
        }
        double temp = array[pivot];
        array[pivot] = array[counter];
        array[counter] = temp;

        return counter;
    }
}

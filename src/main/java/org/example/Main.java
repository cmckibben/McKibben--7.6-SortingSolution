package org.example;

import java.io.BufferedWriter;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

class Main {

    /**
     * @return returns the array array that is sorted from
     *  least to greatest.
     * @param array array containing integers to sort
     * @precondition none
     * @postcondition array in the parameter will be sorted
     */
    public static int[] selectionSort(int[] array){
      for (int i = 0; i < array.length - 1; i++)
        {
          int minIndex = i;
          for (int j = i + 1; j < array.length; j++)
          {
              if (array[j] < array[minIndex])
              {
                minIndex = j;
              }
          }
          int temp = array[i];
          array[i] = array[minIndex];
          array[minIndex] = temp;
        }
      return array;
    }

    /**
     * @return returns the array array that is sorted from
     *  least to greatest.
     * @param array array containing integers to sort
     * @precondition none
     * @postcondition array in the parameter will be sorted
     */
    public static int[] insertionSort(int[] array){
      int j   = 0; //number of items sorted so far
      int i   = 0; //starting point of unsorted array
      int key = 0; //the item to be inserted

      for(j=1; j<array.length;j++) {//start with 1 since the first is "sorted"
        key = array[j]; //The item to be inserted.
        for(i= j-1; ((i >= 0) && (array[i] > key)); i--) {
          array[i+1] = array[i]; // move smaller values up
        }
        array[i+1] = key;
      }
      return array;
    }

    public static void main(String[] args) {

        int[] temp = {36,24,10,6,12};
        selectionSort(temp);
        //insertionSort(temp);
        for(int element : temp) System.out.println(element);
//No reason to change anything below this line, but feel free to browse
        final long runs = 100;
        //final long runs = 1000000;

        System.out.format("Number of trials: %,d\n", runs);
        //unsorted array to search
        final int size = 1000;
        int[] unsortedArray = new int[size];
        for (int i = 0; i < size; i++)
            unsortedArray[i] = (int) ((Math.random() * 99) + 1);
        int[] unsortedArray_copy = new int[size];
        //for timing
        long startTime = 0;
        long endTime = 0;
        ArrayList<Long> selectionSortRunTimes = new ArrayList<Long>();
        ArrayList<Long> insertionSortRunTimes = new ArrayList<Long>();
        long counter = 0;
        int average = 0;

        for (int i = 0; i < runs; i++) {
            //copy first, don't want to include copy in the time
            for(int j =0; j < size; j++)
              unsortedArray_copy[j] = unsortedArray[j];
            startTime = System.nanoTime();
            selectionSort(unsortedArray_copy);
            endTime = System.nanoTime();
            selectionSortRunTimes.add((endTime - startTime));
        }
        counter = 0;
        for (Long value : selectionSortRunTimes) {
            counter += value;
        }
        average = (int) (counter / runs);
        System.out.println("\tSelection Sort average runtime: " + average + " nanoseconds");

        for (int i = 0; i < runs; i++) {
            //copy first, don't want to include copy in the time
            for(int j =0; j < size; j++)
              unsortedArray_copy[j] = unsortedArray[j];
            startTime = System.nanoTime();
            insertionSort(unsortedArray_copy);
            endTime = System.nanoTime();
            insertionSortRunTimes.add((endTime - startTime));
        }
        counter = 0;
        for (Long value : insertionSortRunTimes) {
            counter += value;
        }
        average = (int) (counter / runs);
        System.out.println("\tInsertion Sort average runtime: " + average + " nanoseconds");

        //Write out the arrays to a csv for later analysis
        String fileName = "runtimessorting.csv";
        try (FileWriter writer = new FileWriter(fileName);
             BufferedWriter csv = new BufferedWriter(writer)) {
            csv.append("\"Selection Sort\",\"Insertion Sort\"\n");
            for (int i = 0; i < runs; i++) {
                csv.append(String.valueOf(selectionSortRunTimes.get(i))).append(",");
                csv.append(String.valueOf(insertionSortRunTimes.get(i)));
                csv.append("\n");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


    }
}
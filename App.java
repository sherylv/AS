package project1;

import java.util.*;
import java.io.*;
import jxl.*;
import jxl.write.*;

public class App {
    public static void main(String[] args) throws Exception {
        long[] worstTimes = new long[101];
        long[] averageTimes = new long[101];
        long[] bestTimes = new long[101];

        File best = new File("C://Users//Sherry//Desktop//CPS//CPS3440//selection.xls");
        WritableWorkbook workbook = Workbook.createWorkbook(best);
        WritableSheet sheet = workbook.createSheet("Sheryl", 0);
        Label label;

        for (int arrLength = 100; arrLength <= 10000; arrLength += 100) {
            long totalTime = 0;
            long bestTime = Long.MAX_VALUE;
            long worstTime = 0;
            for (int N = 0; N < 100; N++) {
                int[] arr = new int[arrLength];
                for (int i = 0; i < arr.length; i++) {
                    arr[i] = (int) (Math.random() * arr.length);
                }
                long tStart = System.nanoTime();
                selectionSort(arr);
                long tEnd = System.nanoTime();
                long tRunTime = tEnd - tStart;
                if (tRunTime < bestTime) {
                    bestTime = tRunTime;
                }
                if (tRunTime > worstTime) {
                    worstTime = tRunTime;
                }
                totalTime += tRunTime;
            }
            bestTimes[arrLength / 100] = bestTime;
            worstTimes[arrLength / 100] = worstTime;
            averageTimes[arrLength / 100] = totalTime / 100;
            System.out.println(bestTimes[arrLength / 100] + ", " + worstTimes[arrLength / 100] + ", "
                    + averageTimes[arrLength / 100]);
        }
        for (int i = 1; i <= 100; i++) {
            label = new Label(0, i, i * 100 + "");
            sheet.addCell(label);
            label = new Label(1, i, bestTimes[i] + "");
            sheet.addCell(label);
            label = new Label(2, i, worstTimes[i] + "");
            sheet.addCell(label);
            label = new Label(3, i, averageTimes[i] + "");
            sheet.addCell(label);
        }
        workbook.write();
        workbook.close();
    }

    public static void mergeSort(int[] list, int low, int mid, int high) {
        int[] temp = new int[high - low + 1];
        int i = low;
        int j = mid + 1;
        int k = 0;

        while (i <= mid && j <= high) {
            if (list[i] < list[j]) {
                temp[k++] = list[i++];
            } else {
                temp[k++] = list[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = list[i++];
        }
        while (j <= high) {
            temp[k++] = list[j++];
        }
        for (int x = 0; x < temp.length; x++) {
            list[x + low] = temp[x];
        }
    }

    public static void insertionSort(int[] list) {
        for (int i = 1; i < list.length; i++) {
            int currentElement = list[i];
            int k;
            for (k = i - 1; k >= 0 && list[k] > currentElement; k--) {
                list[k + 1] = list[k];
            }
            list[k + 1] = currentElement;
        }
    }

    public static void selectionSort(int[] list) {
        for (int i = 0; i < list.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < list.length; j++) {
                if (list[j] < list[minIndex]) {
                    minIndex = j;
                }
            }
            swap(list, i, minIndex);
        }
    }

    private static void swap(int[] list, int i, int j) {
        int temp = list[i];
        list[i] = list[j];
        list[j] = temp;
    }

    void sort(int arr[]) {
        for (int i = 1; i < arr.length; ++i) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
        }
    }
    
}

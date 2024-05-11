import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        int[] arraySizes = {500, 1000, 3000};
        int maxThreads = 4;

        try {
            FileWriter quickSortWriter = new FileWriter("quick_sort_performance" + ".csv");
            FileWriter mergeSortWriter = new FileWriter("merge_sort_performance" + ".csv");
            FileWriter bubbleSortWriter = new FileWriter("bubble_sort_performance" + ".csv");
            FileWriter selectionSortWriter = new FileWriter("selection_sort_performance" + ".csv");

            quickSortWriter.append("TamanhoArray,Threads,Tempo\n");
            mergeSortWriter.append("TamanhoArray,Threads,Tempo\n");
            bubbleSortWriter.append("TamanhoArray,Threads,Tempo\n");
            selectionSortWriter.append("TamanhoArray,Threads,Tempo\n");

            for (int size : arraySizes) {
                for (int i = 0; i < 3; i++) {
                    int[] list = generateRandomArray(size);

                    // quick sort -----------------------------------------------------
                    int[] listClone = list.clone();
                    long startTime = System.nanoTime();
                    ParallelSorts.quickSort(listClone);
                    long endTime = System.nanoTime();
                    quickSortWriter.append(size+ "," + i + "," + (endTime - startTime) / 1_000_000 + "\n");

                    // merge sort -----------------------------------------------------
                    listClone = list.clone();
                    startTime = System.nanoTime();
                    ParallelSorts.mergeSort(listClone);
                    endTime = System.nanoTime();
                    mergeSortWriter.append(size+ "," + i + "," + (endTime - startTime) / 1_000_000 + "\n");

                    // bubble sort -----------------------------------------------------
                    listClone = list.clone();
                    startTime = System.nanoTime();
                    ParallelSorts.bubbleSort(listClone);
                    endTime = System.nanoTime();
                    bubbleSortWriter.append(size+ "," + i + "," + (endTime - startTime) / 1_000_000 + "\n");

                    // selection sort -----------------------------------------------------
                    listClone = list.clone();
                    startTime = System.nanoTime();
                    ParallelSorts.selectionSort(listClone);
                    endTime = System.nanoTime();
                    selectionSortWriter.append(size+ "," + i + "," + (endTime - startTime) / 1_000_000 + "\n");

                }
            }

            quickSortWriter.close();
            mergeSortWriter.close();
            bubbleSortWriter.close();
            selectionSortWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int[] generateRandomArray(int size) {
        int[] array = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(1000); // Gera números aleatórios entre 0 e 999
        }
        return array;
    }
}

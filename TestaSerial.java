import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class TestaSerial {

    public static void main(String[] args) {

        int[] arraySizes = {500, 1000, 3000};

        try {
            FileWriter quickSortWriter = new FileWriter("quick_sort_performance" + ".csv");
            FileWriter mergeSortWriter = new FileWriter("merge_sort_performance" + ".csv");
            FileWriter bubbleSortWriter = new FileWriter("bubble_sort_performance" + ".csv");
            FileWriter selectionSortWriter = new FileWriter("selection_sort_performance" + ".csv");

            quickSortWriter.append("TamanhoArray,Tempo\n");
            mergeSortWriter.append("TamanhoArray,Tempo\n");
            bubbleSortWriter.append("TamanhoArray,Tempo\n");
            selectionSortWriter.append("TamanhoArray,Tempo\n");

            for (int size : arraySizes) {
                for (int i = 4; i < 8; i++) {
                    int[] list = generateRandomArray(size);

                    // quick sort -----------------------------------------------------
                    int[] listClone = list.clone();
                    long startTime = System.nanoTime();
                    SerialSorts.quickSort(listClone);
                    long endTime = System.nanoTime();
                    quickSortWriter.append(size+ "," + (endTime - startTime) / 1_000_000 + "\n");

                    // merge sort -----------------------------------------------------
                    listClone = list.clone();
                    startTime = System.nanoTime();
                    SerialSorts.mergeSort(listClone);
                    endTime = System.nanoTime();
                    mergeSortWriter.append(size+ "," + (endTime - startTime) / 1_000_000 + "\n");

                    // bubble sort -----------------------------------------------------
                    listClone = list.clone();
                    startTime = System.nanoTime();
                    SerialSorts.bubbleSort(listClone);
                    endTime = System.nanoTime();
                    bubbleSortWriter.append(size+  "," + (endTime - startTime) / 1_000_000 + "\n");

                    // selection sort -----------------------------------------------------
                    listClone = list.clone();
                    startTime = System.nanoTime();
                    SerialSorts.selectionSort(listClone);
                    endTime = System.nanoTime();
                    selectionSortWriter.append(size+  "," + (endTime - startTime) / 1_000_000 + "\n");

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

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;


public class ParallelSorts {

    private static void swap(int[] list, int positionA, int positionB){
        int aux = list[positionA];
        list[positionA] = list[positionB];
        list[positionB] = aux;
    }


    // QUICK SORT ------------------------------------------------------------

    public static void quickSort(int[] list) {
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(new QuickSortTask(list, 0, list.length - 1));
        pool.shutdown();
    }

    public static void quickSort(int[] list, int numThreads) {
        ForkJoinPool pool = new ForkJoinPool(numThreads);
        pool.invoke(new QuickSortTask(list, 0, list.length - 1));
        pool.shutdown();
    }

    private static class QuickSortTask extends RecursiveAction {
        private final int[] list;
        private final int init;
        private final int end;

        public QuickSortTask(int[] list, int init, int end) {
            this.list = list;
            this.init = init;
            this.end = end;
        }

        @Override
        protected void compute() {
            if (end - init < 2) {
                return;
            }

            int pivo = partition(list, init, end);
            QuickSortTask leftTask = new QuickSortTask(list, init, pivo - 1);
            QuickSortTask rightTask = new QuickSortTask(list, pivo + 1, end);
            leftTask.fork();
            rightTask.compute();
            leftTask.join();
        }

        private int partition(int[] list, int init, int end) {
            int pivot = list[end];
            int pLess = init;

            for (int i = init; i < end; i++) {
                if (list[i] <= pivot) {
                    swap(list, i, pLess);
                    pLess++;
                }
            }
            swap(list, pLess, end);
            return pLess;
        }
    }


    // MERGE SORT ------------------------------------------------------------

    public static void mergeSort(int[] list) {
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(new MergeSortTask(list, 0, list.length - 1));
        pool.shutdown();
    }

    public static void mergeSort(int[] list, int numThreads) {
        ForkJoinPool pool = new ForkJoinPool(numThreads);
        pool.invoke(new MergeSortTask(list, 0, list.length - 1));
        pool.shutdown();
    }

    private static class MergeSortTask extends RecursiveAction {
        private final int[] list;
        private final int start;
        private final int end;

        public MergeSortTask(int[] list, int start, int end) {
            this.list = list;
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {
            if (end - start < 1) {
                return;
            }

            int mid = (start + end) / 2;

            MergeSortTask leftTask = new MergeSortTask(list, start, mid);
            MergeSortTask rightTask = new MergeSortTask(list, mid + 1, end);

            invokeAll(leftTask, rightTask);

            merge(list, start, mid, end);
        }

        private void merge(int[] list, int start, int mid, int end) {
            int[] temp = new int[end - start + 1];
            int i = start;
            int j = mid + 1;
            int k = 0;

            while (i <= mid && j <= end) {
                if (list[i] <= list[j]) {
                    temp[k++] = list[i++];
                } else {
                    temp[k++] = list[j++];
                }
            }

            while (i <= mid) {
                temp[k++] = list[i++];
            }

            while (j <= end) {
                temp[k++] = list[j++];
            }

            System.arraycopy(temp, 0, list, start, temp.length);
        }
    }


    // BUBBLE SORT ------------------------------------------------------------

    public static void bubbleSort(int[] list) {
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(new BubbleSortTask(list, 0, list.length - 1));
        pool.shutdown();
    }

    public static void bubbleSort(int[] list, int numThreads) {
        ForkJoinPool pool = new ForkJoinPool(numThreads);
        pool.invoke(new BubbleSortTask(list, 0, list.length - 1));
        pool.shutdown();
    }

    private static class BubbleSortTask extends RecursiveAction {
        private final int[] list;
        private final int start;
        private final int end;

        public BubbleSortTask(int[] list, int start, int end) {
            this.list = list;
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {
            for (int i = end; i >= start; i--) {
                int numChanges = 0;
                for (int j = start; j < i; j++) {
                    if ((j + 1 < list.length) && (list[j] > list[j + 1])) {
                        swap(list, j, j + 1);
                        numChanges++;
                    }
                }
                if (numChanges == 0) break;
            }
        }
    }

    // SELECTION SORT ------------------------------------------------------------

    public static void selectionSort(int[] list) {
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(new ParallelSelectionSortTask(list, 0, list.length));
        pool.shutdown();
    }

    public static void selectionSort(int[] list, int numThreads) {
        ForkJoinPool pool = new ForkJoinPool(numThreads);
        pool.invoke(new ParallelSelectionSortTask(list, 0, list.length));
        pool.shutdown();
    }

    private static class ParallelSelectionSortTask extends RecursiveAction {
        private final int[] arr;
        private final int start;
        private final int end;

        public ParallelSelectionSortTask(int[] arr, int start, int end) {
            this.arr = arr;
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {
            if (end - start <= 1) {
                return;
            }
            int minIndex = start;
            for (int i = start + 1; i < end; i++) {
                if (arr[i] < arr[minIndex]) {
                    minIndex = i;
                }
            }
            int temp = arr[minIndex];
            arr[minIndex] = arr[start];
            arr[start] = temp;

            ParallelSelectionSortTask leftTask = new ParallelSelectionSortTask(arr, start + 1, end);
            leftTask.fork();

            leftTask.join();
        }
    }


}

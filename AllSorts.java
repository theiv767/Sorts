public class AllSorts{

    private static void swap(int[] list, int positionA, int positionB){
        int aux = list[positionA];
        list[positionA] = list[positionB];
        list[positionB] = aux;
    }


    // QUICK SORT ------------------------------------------------------------
    public static void quickSort(int[] list){
        if (list.length<2) return;
        quickSort(list, 0, list.length-1);
    }

    private static void quickSort(int[] list, int init, int pivo){
        int iterator = init;
        int pLess = init;

        while (iterator < pivo){
            if(list[iterator] <= list[pivo]){   // COMPARAÇÃO
                swap(list, iterator, pLess);
                pLess++;
            }
            iterator++;
        }
        swap(list, pLess, pivo);

        if(pLess-1>init) quickSort(list, init, pLess-1);
        if(pLess+1<pivo) quickSort(list, pLess+1, pivo);

    }


    // MERGE SORT ------------------------------------------------------------
    public static void mergeSort(int[] list){
        if (list.length<2) return;
        mergeSort(list, 0, list.length-1);
    }

    private static void mergeSort(int[] list, int init, int end){
        int mid = (end + init) / 2;
        if(end - init > 1) {
            mergeSort(list, init, mid);
            mergeSort(list, mid + 1, end);
        }
        merge(list, init, mid, end);
    }

    private static void merge(int[] list, int init, int mid, int end){
        int iteratorLeft = init;
        int iteratorRight = mid+1;
        int iteratorGlobal = 0;
        int subArrayLenght = end-init+1;

        int[] temp = new int[subArrayLenght];

        while(iteratorLeft<=mid && iteratorRight<=end){
            if(list[iteratorLeft] <= list[iteratorRight]){  // COMPARAÇÃO
                temp[iteratorGlobal] = list[iteratorLeft];
                iteratorLeft++;
            }else {
                temp[iteratorGlobal] = list[iteratorRight];
                iteratorRight++;
            }
            iteratorGlobal++;

        }
        while (iteratorLeft<=mid && iteratorGlobal<=end){
            temp[iteratorGlobal] = list[iteratorLeft];
            iteratorLeft++;
            iteratorGlobal++;
        }
        while (iteratorRight<=end && iteratorGlobal<=end){
            temp[iteratorGlobal] = list[iteratorRight];
            iteratorRight++;
            iteratorGlobal++;
        }
        for(int i=0; i< subArrayLenght; i++) {
            list[init + i] = temp[i];
        }
    }


    // HEAP SORT ------------------------------------------------------------
    public static void heapSort(){
        //implementação ...
    }


    // SHELL SORT ------------------------------------------------------------
    public static void shellSort(int[] list){
        if (list.length<2) return;

        int h = 1;
        while (h < list.length / 3) {
            h = 3 * h + 1;
        }

        // aplicar o algoritmo ShellSort com a variável h
        while (h >= 1) {
            for (int i = h; i < list.length; i++) {
                int temp = list[i];
                int j;
                for (j = i; j >= h && list[j - h] > temp; j -= h) {
                    list[j] = list[j - h];
                }
                list[j] = temp;
            }
            h = h / 3;
        }
    }

}

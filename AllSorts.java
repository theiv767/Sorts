/**
 * Classe feita para reunir 4 implementações diferentes de algoritmos de sort
 * (quickSort, mergeSort, heapSort e shellSort)
 */
public class AllSorts{

    private static void swap(int[] list, int positionA, int positionB){
        int aux = list[positionA];
        list[positionA] = list[positionB];
        list[positionB] = aux;
    }


    // QUICK SORT ------------------------------------------------------------

    /**
     * Ordena um array usando o algoritmo quickSort
     * @param list array para ser ordenado
     */
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
    /**
     * Ordena um array usando o algoritmo mergeSort
     * @param list array para ser ordenado
     */
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
        System.arraycopy(temp, 0, list, init, subArrayLenght);
    }


    // HEAP SORT ------------------------------------------------------------

    public static class MaxHeap {
        private int heapSize;
        private final int[] list;

        public MaxHeap(int[] list) {
            this.list = list;
            this.heapSize = this.list.length;
            this.heapfy();
        }

        public void goDown(int currentPosition) {
            int right = (currentPosition + 1) * 2; // posição do filho direito

            if (right <= this.heapSize) {
            //j=heapSize --> existe filho esquerdo, mas não existe filho direito
            //j<heapSize --> exite filho esquerdo e filho direito


                if (right < heapSize) { //existe filho direito?
                    // se existir filho direito e esquerdo, comparar quem é maior
                    if (this.list[right] > this.list[right-1])
                        right++;
                }

                if (this.list[right - 1] > this.list[currentPosition]) {
                    //fazer a troca com o maior filho e descer mais
                    swap(this.list, currentPosition, right - 1);
                    this.goDown(right -1);
                }
            }
        }

        public void heapfy() {
            int mid = this.heapSize / 2; // depois de mid as posições são reservadas para folhas

            for (int i = mid; i >=0 ; i--) { // descendo todos os valores do array do meio até 0
                this.goDown(i);
            }
        }

        public void pop() {
            if (heapSize > 0) {
                swap(this.list, 0, this.heapSize-1);
                this.heapSize--;
                this.goDown(0); // verificando a posição certa da nova raiz
            }
        }

        public boolean isEmpty() {
            return this.heapSize == 0;
        }
    }

    /**
     * Ordena um array usando o algoritmo heapSort
     * @param list array para ser ordenado
     */
    public static void heapSort(int[] list){
        if (list.length<2) return;
        MaxHeap heap = new MaxHeap(list);
        while (!heap.isEmpty())heap.pop();
    }


    // SHELL SORT ------------------------------------------------------------
    /**
     * Ordena um array usando o algoritmo shellSort
     * @param list array para ser ordenado
     */
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

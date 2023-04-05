import java.util.Arrays;

public class Main {


    public static void quickSort(int[] list, int init, int pivo){
        if(list.length == 0){
            return;
        }
        int iterator = init;
        int pLess = init;

        while(iterator < pivo){
            if(list[iterator] > list[pivo]){
                iterator++;
            }else {
                int aux = list[pLess];
                list[pLess] = list[iterator];
                list[iterator] = aux;
                pLess++;
                iterator++;
            }

        }
        int aux = list[pLess];
        list[pLess] = list[pivo];
        list[pivo] = aux;

        if(pLess-1 > init){
            quickSort(list, init, pLess-1);
        }
        if(pLess+1 < pivo){
            quickSort(list, pLess+1, pivo);
        }

    }

    public static void quickSort(int[] list){
        quickSort(list, 0, list.length-1);
    }

    public static void main(String[] args) {

        int[] teste = {56, 345, 7, 234 ,27, 1345 ,257, 78};
        quickSort(teste);
        System.out.println(Arrays.toString(teste));


    }
}

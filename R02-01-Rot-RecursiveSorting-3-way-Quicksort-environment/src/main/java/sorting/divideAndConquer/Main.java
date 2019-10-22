package sorting.divideAndConquer;

import java.util.Arrays;

public class Main {

    public static void main(String[] args){
        Integer[] array1 = {};
        MergeSort<Integer> ms = new MergeSort<>();
        ms.sort(array1);

        System.out.println(Arrays.toString(array1));
    }


}

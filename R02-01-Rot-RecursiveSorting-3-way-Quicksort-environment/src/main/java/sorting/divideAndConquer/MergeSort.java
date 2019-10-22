package sorting.divideAndConquer;

import sorting.AbstractSorting;

/**
 * Merge sort is based on the divide-and-conquer paradigm. The algorithm
 * consists of recursively dividing the unsorted list in the middle, sorting
 * each sublist, and then merging them into one single sorted list. Notice that
 * if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

   @Override
   public void sort(T[] array, int leftIndex, int rightIndex) {
      if (leftIndex < rightIndex) {
         int meio = (leftIndex + rightIndex) / 2;

         sort(array, leftIndex, meio);
         sort(array, meio + 1, rightIndex);

         merge(array, leftIndex, rightIndex, meio);
      }

   }

   private void merge(T[] array, int leftIndex, int rightIndex, int meio) {
      T[] copia = (T[]) new Comparable[array.length];

      for (int i = leftIndex; i < array.length; i++) {
         copia[i] = array[i];
      }

      int inicio = leftIndex;
      int fim = meio + 1;

      for (int i = leftIndex; i <= rightIndex; i++) {
         if (inicio > meio) { //Segunda metade
            array[i] = copia[fim++];
         } else if (fim > rightIndex) { //Primeira metade
            array[i] = copia[inicio++];
         } else if (copia[inicio].compareTo(copia[fim]) < 0) {
            array[i] = copia[inicio++];
         } else {
            array[i] = copia[fim++];
         }
      }
   }
}

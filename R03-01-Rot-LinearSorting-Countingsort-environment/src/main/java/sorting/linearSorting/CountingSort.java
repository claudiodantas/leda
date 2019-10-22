package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala. Procure
 * evitar desperdicio de memoria alocando o array de contadores com o tamanho
 * sendo o máximo inteiro presente no array a ser ordenado.
 * 
 */
public class CountingSort extends AbstractSorting<Integer> {

   @Override
   public void sort(Integer[] array, int leftIndex, int rightIndex) {

      if (!(array.length == 0)) {
         int tamanho = maximoInteiro(array, leftIndex, rightIndex) + 1;

         Integer[] counting = new Integer[tamanho];
         Integer[] copia = new Integer[array.length];

         //inicializando o array counting
         for (int i = 0; i < counting.length; i++) {
            counting[i] = 0;
         }

         //realiza a contagem dos elementos
         for (int j = leftIndex; j <= rightIndex; j++) {
            counting[array[j]] = counting[array[j]] + 1;
         }

         for (int i = 1; i < counting.length; i++) {
            counting[i] = counting[i] + counting[i - 1];
         }

         //ordena os numeros na copia
         for (int j = rightIndex; j >= leftIndex; j--) {
            copia[counting[array[j]] - 1] = array[j];
            counting[array[j]] = counting[array[j]] - 1;
         }

         //Atribui os valores da copia ao array original
         for (int i = 0; i < array.length; i++) {
            array[i] = copia[i];
         }
      }

   }

   private int maximoInteiro(Integer[] array, int leftIndex, int rightIndex) {
      int maior = array[leftIndex];

      for (int i = leftIndex; i <= rightIndex; i++) {
         if (array[i] > maior) {
            maior = array[i];
         }
      }

      return maior;
   }

}

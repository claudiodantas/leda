package sorting.linearSorting;

import sorting.AbstractSorting;

import static java.lang.Math.abs;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos: - Alocar o tamanho minimo
 * possivel para o array de contadores (C) - Ser capaz de ordenar arrays
 * contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {

		if (!(array.length == 0)) {
			int menorInteiro = menorInteiro(array, leftIndex, rightIndex);
			int tamanho = abs(maximoInteiro(array, leftIndex, rightIndex) + 1 - menorInteiro);

			Integer[] counting = new Integer[tamanho];
			Integer[] copia = new Integer[array.length];

			//inicializando o array counting
			for (int i = 0; i < counting.length; i++) {
				counting[i] = 0;
			}

			//realiza a contagem dos elementos
			for (int j = leftIndex; j <= rightIndex; j++) {
				counting[array[j] - menorInteiro] = counting[array[j] - menorInteiro] + 1;
			}

			for (int i = 1; i < counting.length; i++) {
				counting[i] = counting[i] + counting[i - 1];
			}

			//ordena os numeros na copia
			for (int j = rightIndex; j >= leftIndex; j--) {
				copia[counting[array[j] - menorInteiro] - 1] = array[j];
				counting[array[j] - menorInteiro] = counting[array[j] - menorInteiro] - 1;
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

	private int menorInteiro(Integer[] array, int leftIndex, int rightIndex){
		int menor = array[leftIndex];

		for (int i = leftIndex; i <= rightIndex; i++){
			if (array[i] < menor){
				menor = array[i];
			}
		}

		return menor;

	}

}

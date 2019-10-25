package problems;


/**
 * Dado dois arrays ordenados em ordem crescente, encontrar a k-esima estatistica de ordem 
 * da uniao ordenada deles. 
 * 
 * Restricoes:
 * - os arrays nao possuem elementos em comum e nem repetidos
 * - k eh um numero compreendido entre 1 e array1.length + array2.length
 * - caso o k-esima estatistica de ordem nao exista, o metodo deve retornar null
 * - voce nao pode usar memoria extra
 * - seu algoritmo deve ter complexidade O(array1.length + array2.length). 
 * - voce nao pode usar nenhum metodo pronto de manipulacao de arrays, exceto length.
 * 
 * @author adalbertocajueiro
 *
 */
public class OrderStatisticsSortedUnion<T extends Comparable<T>> {

	public T statisticsOrder(T[] array1, T[] array2, int k) {
		return statisticsOrderAuxiliar(array1, array2, k, null);

	}

	private T statisticsOrderAuxiliar(T[] array1, T[] array2, int k, T menorElemento) {
		T retorno;

		if (k <= 0 || k > (array1.length + array2.length)){ //caso base
			retorno = menorElemento;

		} else {

			T menor1 = null; //menor elemento do primeiro array
			T menor2 = null; // menor elemento do segundo array

			T menorFinal = null; //menor elemento dentre os menores

			boolean primeiro = false;

			if (menorElemento == null){
				primeiro = true;
			}

			//Inicializa a primeira estatística de ordem dos arrays.
			if (primeiro){
				if (array1.length > 0){
					menor1 = array1[0];
				}

				if (array2.length > 0){
					menor2 = array2[0];
				}

			} else {
				for (int i = 0; i < array1.length; i++){
					if (array1[i].compareTo(menorElemento) > 0){
						if (menor1 == null){
							menor1 = array1[i];
						} else{
							if (array1[i].compareTo(menor1) < 0){
								menor1 = array1[i];
							}
						}

					}
				}

				for (int i = 0; i < array2.length; i++){
					if (array2[i].compareTo(menorElemento) > 0){
						if (menor2 == null){
							menor2 = array2[i];
						} else{
							if (array2[i].compareTo(menor2) < 0){
								menor2 = array2[i];
							}
						}
					}
				}

			}


			//Verifica qual elemento é o menor dentre os menores selecionados
			if (menor1 != null){
				if (menor2 != null){
					if (menor1.compareTo(menor2) < 0){
						menorFinal = menor1;
					} else{
						menorFinal = menor2;
					}
				} else {
					menorFinal = menor1;
				}

			} else {
				if (menor2 != null){
					menorFinal = menor2;
				}
			}

			retorno = statisticsOrderAuxiliar(array1, array2, k-1, menorFinal);
		}

		return retorno;

	}


}

package orderStatistic;

public class OrderStatisticsSelectionImpl<T extends Comparable<T>> implements OrderStatistics<T> {

	/**
	 * Esta eh uma implementacao do calculo da estatistica de ordem seguindo a estrategia 
	 * de usar o selection sem modificar o array original. Note que seu algoritmo vai 
	 * apenas aplicar sucessivas vezes o selection ate encontrar a estatistica de ordem 
	 * desejada sem modificar o array original. 
	 * 
	 * Restricoes:
	 * - Preservar o array original, ou seja, nenhuma modificacao pode ser feita no 
	 *   array original
	 * - Nenhum array auxiliar deve ser criado e utilizado.
	 * - Voce nao pode encontrar a k-esima estatistica de ordem por contagem de
	 *   elementos maiores/menores, mas sim aplicando sucessivas selecoes (selecionar um elemento
	 *   como o selectionsort mas sem modificar nenhuma posicao do array).
	 * - Caso a estatistica de ordem nao exista no array, o algoritmo deve retornar null. 
	 * - Considerar que k varia de 1 a N 
	 * - Sugestao: o uso de recursao ajudara sua codificacao.
	 */
	@Override
	public T getOrderStatistics(T[] array, int k) {
		return orderStatisticsRecursive(array, null, k);
	}

	private T orderStatisticsRecursive(T[] array, T menorElemento, int k) {
		T menor = null;

		if (k <= 0){
			return menorElemento;
		}

		if (menorElemento == null){
			menor = array[0];
			for (int j = 0; j < array.length; j++){
				if (!(menor.compareTo(array[j]) == 0)){
					if (array[j].compareTo(menor) < 0){
						menor = array[j];
					}
				}
			}

			return orderStatisticsRecursive(array, menor, k-1);

		} else {
			boolean primeiro = true;
			for (int j = 0; j < array.length; j++){
				if (!(array[j].compareTo(menorElemento) < 0)){
					if (!(array[j].compareTo(menorElemento) == 0)){
						if (primeiro){
							menor = array[j];
							primeiro = false;
						} else {
							if (array[j].compareTo(menor) < 0){
								menor = array[j];
							}
						}
					}
				}

			}

			return orderStatisticsRecursive(array, menor, k-1);
		}

	}

}

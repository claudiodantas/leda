package problems;

/**
 * Calcula o floor e ceil de um numero em um array usando a estrategia de busca
 * binaria.
 * 
 * Restricoes: 
 * - Algoritmo in-place (nao pode usar memoria extra a nao ser variaveis locais) 
 * - O tempo de seu algoritmo deve ser O(log n).
 * 
 * @author Adalberto
 *
 */
public class FloorCeilBinarySearch implements FloorCeil {

	@Override
	public Integer floor(Integer[] array, Integer x) {
		return floorRecursive(array, 0, array.length, x);
	}

	private Integer floorRecursive(Integer[] array, int leftIndex, int rightIndex, Integer x) {
		int meio = (leftIndex + rightIndex-1) / 2;

		if (array[meio] == x){
			return array[meio];
		} else if (leftIndex >= rightIndex){
			return array[meio];
		} else{
			if (array[meio] < x){
				return floorRecursive(array, meio+1, rightIndex, x);
			} else{
				return floorRecursive(array, leftIndex, meio, x);
			}
		}

	}

	@Override
	public Integer ceil(Integer[] array, Integer x) {
		return ceilRecursive(array, 0, array.length, x);
	}

	private Integer ceilRecursive(Integer[] array, int leftIndex, int rightIndex, Integer x) {
		int meio = (leftIndex + rightIndex-1) / 2;

		if (array[meio] == x){
			return array[meio];
		} else if (leftIndex >= rightIndex){
			return array[meio];
		} else{
			if (array[meio] > x){
				return ceilRecursive(array, meio+1, rightIndex-1, x);
			} else{
				return ceilRecursive(array, meio+1, rightIndex, x);
			}
		}
	}


}

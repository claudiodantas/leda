package adt.bst.extended;

import java.util.Comparator;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

/**
 * Implementacao de SortComparatorBST, uma BST que usa um comparator interno em suas funcionalidades
 * e possui um metodo de ordenar um array dado como parametro, retornando o resultado do percurso
 * desejado que produz o array ordenado. 
 * 
 * @author Adalberto
 *
 * @param <T>
 */
public class SortComparatorBSTImpl<T extends Comparable<T>> extends BSTImpl<T> implements SortComparatorBST<T> {

	private Comparator<T> comparator;

	public SortComparatorBSTImpl(Comparator<T> comparator) {
		super();
		this.comparator = comparator;
	}

	//TODO dá certo se eu sobreescrever apenas a parte recursiva dos métodos insert e search?

	@Override
	public BSTNode<T> search(T element) {
		BSTNode<T> result = new BSTNode<>();

		if (element != null && !isEmpty()){
			result = searchRecursive(this.root, element);
		}

		return result;
	}

	private BSTNode<T> searchRecursive(BSTNode<T> node, T element) {
		BSTNode<T> result = new BSTNode<>();

		if (!node.isEmpty()){
			if (node.getData().equals(element)){
				result = node;
			} else if (this.comparator.compare(node.getData(), element) > 0){
				result = searchRecursive((BSTNode<T>) node.getLeft(), element);
			} else {
				result = searchRecursive((BSTNode<T>) node.getRight(), element);
			}
		}

		return result;
	}

	@Override
	public void insert(T element) {
		if (element != null){
			insertRecursive(this.root, element);
		}
	}

	private void insertRecursive(BSTNode<T> node, T element) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BSTNode.Builder<T>().data(null).left(null).right(null).parent(node).build());
			node.setRight(new BSTNode.Builder<T>().data(null).left(null).right(null).parent(node).build());
		} else {
			if (this.comparator.compare(node.getData(), element) > 0) {
				insertRecursive((BSTNode<T>) node.getLeft(), element);
			} else {
				insertRecursive((BSTNode<T>) node.getRight(), element);
			}
		}
	}

	@Override
	public T[] sort(T[] array) {
		this.root = new BSTNode<>();

		if (array != null){
			for (int i = 0; i < array.length; i++){
				this.insert(array[i]);
			}
		}

		return super.order();

	}


	@Override
	public T[] reverseOrder() {
		T[] arr = (T[]) new Comparable[this.size()];

		ReverseOrderRecursive(this.root, arr, 0);

		return arr;
	}

	private int ReverseOrderRecursive(BSTNode<T> node, T[] arr, int indice) {
		if (node != null && !node.isEmpty()){
			indice = orderRecursive((BSTNode<T>) node.getRight(), arr, indice);
			arr[indice++] = node.getData();
			indice = orderRecursive((BSTNode<T>) node.getLeft(), arr, indice);
		}

		return indice;
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}
	
}

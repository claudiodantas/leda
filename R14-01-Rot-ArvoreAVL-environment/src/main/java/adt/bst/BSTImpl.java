package adt.bst;

import adt.bt.BTNode;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		return heightRecursive(this.root);
	}

	public int heightRecursive(BTNode<T> node) {
		int result = -1;

		if (!node.isEmpty()){
			result = 1 + Math.max(heightRecursive(node.getLeft()),
					heightRecursive(node.getRight()));
		}

		return result;
	}


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
			} else if (node.getData().compareTo(element) > 0){
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
			if (node.getData().compareTo(element) > 0) {
				insertRecursive((BSTNode<T>) node.getLeft(), element);
			} else {
				insertRecursive((BSTNode<T>) node.getRight(), element);
			}
		}
	}

	@Override
	public BSTNode<T> maximum() {
		BSTNode<T> result = null;

		if (!this.isEmpty()){
			result = maximumRecursive(this.root);
		}

		return result;
	}

	private BSTNode<T> maximumRecursive(BSTNode<T> node) {
		BSTNode<T> result = node;

		if (!node.getRight().isEmpty()){
			result = maximumRecursive((BSTNode<T>) node.getRight());
		}

		return result;
	}

	@Override
	public BSTNode<T> minimum() {
		BSTNode<T> result = null;

		if (!this.isEmpty()){
			result = minimumRecursive(this.root);
		}

		return result;
	}

	private BSTNode<T> minimumRecursive(BSTNode<T> node) {
		BSTNode<T> result = node;

		if (!node.getLeft().isEmpty()){
			result = minimumRecursive((BSTNode<T>) node.getLeft());
		}

		return result;
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> result = search(element);

		if (element != null && !this.root.isEmpty() && !result.isEmpty()){
			if (!result.getRight().isEmpty()){
				result = minimumRecursive((BSTNode<T>) result.getRight());
			} else {
				result = sucessorRecursive(result);
			}
		} else {
			result = null;
		}

		return result;
	}

	private BSTNode<T> sucessorRecursive(BSTNode<T> node) {
		BSTNode<T> result = (BSTNode<T>) node.getParent();

		if (node.getParent() != null){
			if (!result.isEmpty() && result.getRight().equals(node)){
				result = sucessorRecursive((BSTNode<T>) node.getParent());
			}
		}

		return result;
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> result = search(element);

		if (element != null && !this.root.isEmpty() && !result.isEmpty()){
			if (!result.getLeft().isEmpty()){
				result = maximumRecursive((BSTNode<T>) result.getLeft());
			} else {
				result = predecessorRecursive(result);
			}
		} else {
			result = null;
		}

		return result;
	}

	private BSTNode<T> predecessorRecursive(BSTNode<T> node) {
		BSTNode<T> result = (BSTNode<T>) node.getParent();

		if (node.getParent() != null){
			if (!result.isEmpty() && result.getLeft().equals(node)){
				result = predecessorRecursive((BSTNode<T>) node.getParent());
			}
		}

		return result;

	}

	@Override
	public void remove(T element) {
		BSTNode<T> node = search(element);

		if (!node.isEmpty()){
			if (node.isLeaf()){
				node.setData(null);
				node.setLeft(null);
				node.setRight(null);
			} else if (node.getLeft().isEmpty() || node.getRight().isEmpty()){
				BSTNode<T> parent = (BSTNode<T>) node.getParent();

				if (parent != null){
					if (!parent.getLeft().equals(node)){
						if (!node.getLeft().isEmpty()){
							parent.setRight(node.getLeft());
							node.getLeft().setParent(parent);
						} else {
							parent.setRight(node.getRight());
							node.getRight().setParent(parent);
						}
					} else {
						if (!node.getLeft().isEmpty()){
							parent.setLeft(node.getLeft());
							node.getLeft().setParent(parent);
						} else {
							parent.setLeft(node.getRight());
							node.getRight().setParent(parent);
						}
					}
				} else {
					if (node.getLeft().isEmpty()){
						this.root = (BSTNode<T>) node.getRight();
					} else {
						this.root = (BSTNode<T>) node.getLeft();
					}

					this.root.setParent(null);
				}
			} else {
				T sucessor = sucessor(node.getData()).getData();
				remove(sucessor);
				node.setData(sucessor);
			}
		}
	}

	@Override
	public T[] preOrder() {
		T[] arr = (T[]) new Comparable[this.size()];

		preOrderRecursive(this.root, arr, 0);

		return arr;
	}

	private void preOrderRecursive(BSTNode<T> node, T[] arr, int indice) {
		if (!node.isEmpty()){
			arr[indice] = node.getData();
			preOrderRecursive((BSTNode<T>) node.getLeft(), arr, indice+1);
			preOrderRecursive((BSTNode<T>) node.getRight(), arr, indice+1 + size((BSTNode<T>) node.getLeft()));
		}
	}

	@Override
	public T[] order() {
		T[] arr = (T[]) new Comparable[this.size()];

		orderRecursive(this.root, arr, 0);

		return arr;
	}

	public int orderRecursive(BSTNode<T> node, T[] arr, int indice){
		if (!node.isEmpty()){
			indice = orderRecursive((BSTNode<T>) node.getLeft(), arr, indice);
			arr[indice++] = node.getData();
			indice = orderRecursive((BSTNode<T>) node.getRight(), arr, indice);
		}

		return indice;
	}

	@Override
	public T[] postOrder() {
		T[] arr = (T[]) new Comparable[this.size()];

		postOrderRecursive(this.root, arr, 0);

		return arr;
	}

	private int postOrderRecursive(BSTNode<T> node, T[] arr, int indice) {
		if (!node.isEmpty()){
			indice = postOrderRecursive((BSTNode<T>) node.getLeft(), arr, indice);
			indice = postOrderRecursive((BSTNode<T>) node.getRight(), arr, indice);
			arr[indice++] = node.getData();
		}

		return indice;
	}

	/**
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(this.root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft())
					+ size((BSTNode<T>) node.getRight());
		}
		return result;
	}

}
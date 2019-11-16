package adt.bt;

import adt.bst.BSTNode;

public class Util {


	/**
	 * A rotacao a esquerda em node deve subir e retornar seu filho a direita
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> leftRotation(BSTNode<T> node) {
		// PIVOT = NODE.OS
		BSTNode<T> pivot = (BSTNode<T>) node.getRight();
		// NODE.OS = PIVOT.RS
		node.setRight(pivot.getLeft());
		if (pivot.getLeft() != null) {
			pivot.getLeft().setParent(node);
		}
		pivot.setParent(node.getParent());
		if (node.getParent() != null) {
			if (node.equals(node.getParent().getLeft())) {
				node.getParent().setLeft(pivot);
			} else {
				node.getParent().setRight(pivot);
			}
		}
		// PIVOT.RS = NODE
		pivot.setLeft(node);
		// ROOT = PIVOT
		node.setParent(pivot);
		return pivot;
	}

	/**
	 * A rotacao a direita em node deve subir e retornar seu filho a esquerda
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> rightRotation(BSTNode<T> node) {
		// PIVOT = NODE.OS
		BSTNode<T> pivot = (BSTNode<T>) node.getLeft();
		// NODE.OS = PIVOT.RS
		node.setLeft(pivot.getRight());
		if (pivot.getRight() != null) {
			pivot.getRight().setParent(node);
		}
		pivot.setParent(node.getParent());
		if (node.getParent() != null) {
			if (node.equals(node.getParent().getRight())) {
				node.getParent().setRight(pivot);
			} else {
				node.getParent().setLeft(pivot);
			}
		}
		// PIVOT.RS = NODE
		pivot.setRight(node);
		// ROOT = PIVOT
		node.setParent(pivot);
		return pivot;
	}

	public static <T extends Comparable<T>> T[] makeArrayOfComparable(int size) {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[size];
		return array;
	}
}

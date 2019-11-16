package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

/**
 * 
 * Implementacao de uma arvore AVL
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements
		AVLTree<T> {

	// TODO Do not forget: you must override the methods insert and remove
	// conveniently.
	@Override
	public void insert(T element){
		if (element != null){
			insertRecursive(this.root, element);
		}
	}

	private void insertRecursive(BSTNode<T> node, T element){
		if (node.isEmpty()){
			node.setData(element);
			node.setLeft(new BSTNode.Builder<T>().data(null).left(null).right(null).parent(node).build());
			node.setRight(new BSTNode.Builder<T>().data(null).left(null).right(null).parent(node).build());
		} else {
			if (node.getData().compareTo(element) > 0) {
				insertRecursive((BSTNode<T>) node.getLeft(), element);
			} else {
				insertRecursive((BSTNode<T>) node.getRight(), element);
			}

			rebalance(node);
		}
	}

	@Override
	public void remove(T element){
		BSTNode<T> node = search(element);
		super.remove(element);
		rebalanceUp(node);
	}

	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		int result = 0;

		if (!node.isEmpty() && node != null){
			result = heightRecursive(node.getLeft()) - heightRecursive(node.getRight());
		}

		return result;
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		int balance = calculateBalance(node);
		BSTNode<T> novaRoot;

		if (balance > 1){ //Pende para a esquerda
			if (calculateBalance((BSTNode<T>) node.getLeft()) > 0){ //Rotaciona LL
				novaRoot = Util.rightRotation(node);
			} else { //Rotaciona LR
				Util.leftRotation((BSTNode<T>) node.getLeft());
				novaRoot = Util.rightRotation(node);
			}

			if (novaRoot.getParent() == null){
				this.root = novaRoot;
			}

		} else if (balance < -1){ //pende para a direita
			if (calculateBalance((BSTNode<T>) node.getRight()) < 0){ //Rotaciona RR
				novaRoot = Util.leftRotation(node);
			} else { //Rotaciona RL
				Util.rightRotation((BSTNode<T>) node.getRight());
				novaRoot = Util.leftRotation(node);
			}

			if (novaRoot.getParent() == null){
				this.root = novaRoot;
			}
		}
	}

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		BSTNode<T> parent = (BSTNode<T>) node.getParent();

		while(parent != null){
			rebalance(parent);
			parent = (BSTNode<T>) node.getParent();
		}
	}
}

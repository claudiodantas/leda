package adt.bst;

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
		return height(this.root);
	}

	public int height (BSTNode<T> node) {
		int result = -1;
		if(!node.isEmpty()) {
			int left = height((BSTNode<T>) node.getLeft());
			int right = height((BSTNode<T>) node.getRight());
			if(left >= right){
				result = 1 + left;
			} else {
				result =  1 + right;
			}
		}
		return result;
	}

	@Override
	public BSTNode<T> search(T element) {
		return search(element, this.root);
	}

	private BSTNode<T> search(T element, BSTNode<T> node) {
		BSTNode<T> result = new BSTNode<>();
		if(element != null && !node.isEmpty()){
			if(element.equals(node.getData())){
				result = node;
			} else if (element.compareTo(node.getData()) > 0){
				result = search(element, (BSTNode<T>) node.getRight());
			} else {
				result = search(element, (BSTNode<T>) node.getLeft());
			}
		}
		return result;
	}


	@Override
	public void insert(T element) {
		if(element != null){
			insert(element, this.root);
		}
	}

	private void insert(T element, BSTNode<T> node) {
		if(node.isEmpty()){
			node.setData(element);
			node.setLeft(new BSTNode.Builder<T>().data(null).left(null).right(null).parent(node).build());
			node.setRight(new BSTNode.Builder<T>().data(null).left(null).right(null).parent(node).build());
		}else{
			if (element.compareTo(node.getData()) < 0){
				insert(element, (BSTNode<T>) node.getLeft());
			} else if (element.compareTo(node.getData()) > 0){
				insert(element, (BSTNode<T>) node.getRight());
			}
		}
	}


	@Override
	public BSTNode<T> maximum() {
		return maximum(this.root);
	}

	private BSTNode<T> maximum(BSTNode<T> node) {
		BSTNode<T> result = null;
		if(!node.isEmpty()){
			while(!node.getRight().isEmpty()){
				node = (BSTNode<T>) node.getRight();
			}
			result = node;
		}
		return result;
	}


	@Override
	public BSTNode<T> minimum() {
		return minimum(this.root);
	}

	private BSTNode<T> minimum(BSTNode<T> node) {
		BSTNode<T> result = null;
		if(!node.isEmpty()){
			while(!node.getLeft().isEmpty()){
				node = (BSTNode<T>) node.getLeft();
			}
			result = node;
		}
		return result;
	}


	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> node = this.search(element);
		BSTNode<T> result = null;
		if(!node.isEmpty()){
			if(!node.getRight().isEmpty()){
				result = minimum((BSTNode<T>) node.getRight());
			} else {
				BSTNode<T> pai = (BSTNode<T>) node.getParent();
				while (pai != null && !pai.isEmpty() && node.equals(pai.getRight())){
					node = pai;
					pai = (BSTNode<T>) pai.getParent();
				}
				result = pai;
			}
		}
		return result;
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> node = this.search(element);
		BSTNode<T> result = null;
		if(!node.isEmpty()){
			if(!node.getLeft().isEmpty()){
				result = maximum((BSTNode<T>) node.getLeft());
			} else {
				BSTNode<T> pai = (BSTNode<T>) node.getParent();
				while (pai != null && !pai.isEmpty() && node.equals(pai.getLeft())){
					node = pai;
					pai = (BSTNode<T>) pai.getParent();
				}
				result = pai;
			}
		}
		return result;
	}

	@Override
	public void remove(T element) {
		BSTNode<T> node = search(element);
		if(!node.isEmpty()){
			if(node.isLeaf()){
				node.setData(null);
				node.setRight(null);
				node.setLeft(null);
			} else if(node.getRight().isEmpty() || node.getLeft().isEmpty()) {
				BSTNode<T> pai = (BSTNode<T>) node.getParent();
				if(pai != null) { //se nao for root
					if(node.equals(pai.getLeft())){
						if (!node.getLeft().isEmpty()) {
							pai.setLeft(node.getLeft());
							node.getLeft().setParent(pai);
						} else {
							pai.setLeft(node.getRight());
							node.getRight().setParent(pai);
						}
					} else {
						if(!node.getLeft().isEmpty()){
							pai.setRight(node.getLeft());
							node.getLeft().setParent(pai);
						} else {
							pai.setRight(node.getRight());
							node.getRight().setParent(pai);
						}
					}
				} else{
					if (node.getLeft().isEmpty()) {
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
		T[] result = (T[]) new Comparable[this.size()];
		preOrder(this.root, result, 0);
		return result;
	}

	private void preOrder(BSTNode<T> root, T[] result, int i) {
		if(root !=  null && !root.isEmpty()){
			result[i] = root.getData();
			preOrder((BSTNode<T>) root.getLeft(), result, i+1);
			preOrder((BSTNode<T>) root.getRight(), result, i + 1 + size((BSTNode<T>) root.getLeft()));
		}
	}

	@Override
	public T[] order() {
		T[] result = (T[]) new Comparable[this.size()];
		order(this.root, result, 0);
		return result;
	}

	private int order(BSTNode<T> root, T[] result, int i) {
		if(root !=  null && !root.isEmpty()){
			i = order((BSTNode<T>) root.getLeft(), result, i);
			result[i++] = (root.getData());
			i = order((BSTNode<T>) root.getRight(), result, i);
		}
		return i;
	}

	@Override
	public T[] postOrder() {
		T[] result = (T[]) new Comparable[this.size()];
		postOrder(this.root, result, 0);
		return result;
	}

	private int postOrder(BSTNode<T> root, T[] result, int i) {
		if(root !=  null && !root.isEmpty()){
			i = postOrder((BSTNode<T>) root.getLeft(), result, i);
			i = postOrder((BSTNode<T>) root.getRight(), result, i);
			result[i++] = (root.getData());
		}
		return i;
	}

	/**
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
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

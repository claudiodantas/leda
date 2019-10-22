package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends
		RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;

	public RecursiveDoubleLinkedListImpl() {
	}

	@Override
	public void insert(T element){
		if (element != null){
			if (this.isEmpty()){
				this.setData(element);
				this.setNext(new RecursiveDoubleLinkedListImpl<>());

				if (this.getPrevious() == null){
					this.setPrevious(new RecursiveDoubleLinkedListImpl<>());
				}
			} else {
				this.getNext().insert(element);
			}
		}
	}

	@Override
	public void remove(T element){
		if (element != null){
			if (!this.isEmpty()){
				if (this.getData().equals(element)){
					if (this.getPrevious().isEmpty() && this.getNext().isEmpty()){
						this.setPrevious(null);
						this.setData(null);
						this.setNext(null);
					} else {
						this.setData(this.getNext().getData());
						this.setNext(this.getNext().getNext());

						if (this.getNext() != null){
							((RecursiveDoubleLinkedListImpl<T>) this.getNext()).setPrevious(this);
						}
					}
				} else {
					this.getNext().remove(element);
				}
			}
		}

	}

	@Override
	public void insertFirst(T element) {
		if (element != null){
			if (this.isEmpty()){
				this.setData(element);
				this.setNext(new RecursiveDoubleLinkedListImpl<>());

				if (this.getPrevious() == null){
					this.setPrevious(new RecursiveDoubleLinkedListImpl<>());
				}
			} else {
				T node = this.getData();
				this.setData(element);
				((RecursiveDoubleLinkedListImpl<T>) this.getNext()).setPrevious(this);
				((RecursiveDoubleLinkedListImpl<T>) this.getNext()).insertFirst(node);
			}
		}
	}

	@Override
	public void removeFirst() {
		if (!this.isEmpty()){
			if (this.getPrevious().isEmpty() && this.getNext().isEmpty()){
				this.setPrevious(null);
				this.setData(null);
				this.setNext(null);
			} else {
				this.setData(this.getNext().getData());
				this.setNext(this.getNext().getNext());

				if (this.getNext() != null){
					((RecursiveDoubleLinkedListImpl<T>) this.getNext()).setPrevious(this);
				}
			}
		}
	}

	@Override
	public void removeLast() {
		if (!this.isEmpty()){
			if (this.getPrevious().isEmpty() && this.getNext().isEmpty()){
				this.setData(null);
				this.setPrevious(null);
				this.setNext(null);
			} else {
				if (this.getNext().isEmpty()){
					this.setData(null);
					this.setNext(null);
				} else {
					((RecursiveDoubleLinkedListImpl<T>) this.getNext()).removeLast();
				}
			}
		}
	}

	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}

}

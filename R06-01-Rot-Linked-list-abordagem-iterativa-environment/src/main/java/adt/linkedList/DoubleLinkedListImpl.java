package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements
		DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;

	public DoubleLinkedListImpl() {
		this.setHead(new DoubleLinkedListNode<>());
		this.setLast((DoubleLinkedListNode<T>) this.head);
	}

	@Override
	public void insert(T element){
		if (element != null){
			DoubleLinkedListNode<T> newLast = new DoubleLinkedListNode<>(element, new DoubleLinkedListNode<>(), this.last);
			this.last.next = newLast;

			if (last.isNIL()){
				this.head = newLast;
			}

			this.last = newLast;
		}
	}

	@Override
	public void insertFirst(T element) {
		if (element != null){
			DoubleLinkedListNode<T> nil = new DoubleLinkedListNode<>();
			DoubleLinkedListNode<T> newHead = new DoubleLinkedListNode<>(element, (DoubleLinkedListNode<T>) this.head, nil);

			((DoubleLinkedListNode<T>) this.head).setPrevious(newHead);

			if (!this.head.isNIL()){
				this.setLast(newHead);
			}

			this.setHead(newHead);
		}
	}

	@Override
	public void remove(T element){
		if (element != null || !this.isEmpty()){
			if (this.getHead().getData().equals(element)){
				removeFirst();
			} else {
				DoubleLinkedListNode<T> aux = (DoubleLinkedListNode<T>) this.getHead();

				while (!aux.isNIL() && !aux.getData().equals(element)){
					aux = (DoubleLinkedListNode<T>) aux.getNext();
				}

				if (!aux.isNIL()){
					aux.getPrevious().setNext(aux.getNext());
					((DoubleLinkedListNode<T>) aux.getNext()).setPrevious(aux.getPrevious());
				}
			}
		}

	}

	@Override
	public void removeFirst() {
		if (!this.isEmpty()){
			if (!this.getHead().isNIL()){
				this.setHead(this.getHead().getNext());

				if (this.getHead().isNIL()){
					this.setLast((DoubleLinkedListNode<T>) this.getHead());
				}

				((DoubleLinkedListNode<T>) this.getHead()).setPrevious(new DoubleLinkedListNode());

			}
		}

	}

	@Override
	public void removeLast() {
		if (!this.isEmpty()){
			if (!this.getLast().isNIL()){
				this.setLast(this.getLast().getPrevious());

				if (this.getLast().isNIL()){
					this.setHead(this.getLast());
				}

				this.getLast().setNext(new DoubleLinkedListNode<>());
			}
		}
	}

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}

}

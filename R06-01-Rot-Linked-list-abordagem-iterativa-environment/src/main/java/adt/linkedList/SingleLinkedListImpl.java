package adt.linkedList;

import java.util.ArrayList;
import java.util.List;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<>();
	}

	@Override
	public boolean isEmpty() {
		return this.getHead().isNIL();
	}

	@Override
	public int size() {
		int size = 0;
		SingleLinkedListNode<T> aux = this.head;

		while (!aux.isNIL()){
			size++;
			aux = aux.getNext();
		}

		return size;
	}

	@Override
	public T search(T element) {
		T retorno = null;
		if (element != null){
			SingleLinkedListNode<T> x = this.getHead();

			while (!x.isNIL() && !x.getData().equals(element)){
				x = x.getNext();
			}

			if (!x.isNIL()){
				retorno = x.getData();
			}
		}

		return retorno;

	}

	@Override
	public void insert(T element) {
		if (element != null){
			SingleLinkedListNode<T> auxHead = this.getHead();

			if (this.getHead().isNIL()){
				SingleLinkedListNode<T> newHead = new SingleLinkedListNode<>(element, this.getHead());
				this.setHead(newHead);
			} else {
				while (!auxHead.getNext().isNIL()){
					auxHead = auxHead.getNext();
				}

				SingleLinkedListNode<T> newNode = new SingleLinkedListNode<>(element, auxHead);
				newNode.setNext(auxHead.getNext());
				auxHead.setNext(newNode);
			}
		}

	}

	@Override
	public void remove(T element) {
		if (element != null){
			if (this.getHead().getData().equals(element)){
				this.setHead(this.head.getNext());
			} else {
				SingleLinkedListNode<T> aux = this.head;
				SingleLinkedListNode<T> previous = this.head;

				while (!aux.isNIL() && !aux.getData().equals(element)){
					previous = aux;
					aux = aux.getNext();
				}

				if (!aux.isNIL()){
					previous.setNext(aux.getNext());
				}
			}
		}
	}

	@Override
	public T[] toArray() {
		List<T> result = new ArrayList<>();
		SingleLinkedListNode<T> aux = this.head;

		while (!aux.isNIL()){
			result.add(aux.getData());
			aux = aux.getNext();
		}

		return (T[]) result.toArray();
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}

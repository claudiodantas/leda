package adt.linkedList.set;

import adt.linkedList.SingleLinkedListImpl;
import adt.linkedList.SingleLinkedListNode;

public class SetLinkedListImpl<T> extends SingleLinkedListImpl<T> implements SetLinkedList<T> {

	@Override
	public void removeDuplicates() {
		SetLinkedListImpl<T> newSLL = new SetLinkedListImpl<>();

		for (T element: this.toArray()){
			if (newSLL.search(element) == null){
				newSLL.insert(element);
			}
		}

		this.setHead(newSLL.getHead());
	}

	@Override
	public SetLinkedList<T> union(SetLinkedList<T> otherSet) {
		SetLinkedListImpl<T> newSLL = new SetLinkedListImpl<>();

		for (T element: this.toArray()){
			newSLL.insert(element);
		}

		for (T element: otherSet.toArray()){
			newSLL.insert(element);
		}

		newSLL.removeDuplicates();

		return newSLL;

	}

	@Override
	public SetLinkedList<T> intersection(SetLinkedList<T> otherSet) {
		SetLinkedListImpl<T> newSLL = new SetLinkedListImpl<>();

		for (T element: this.toArray()){
			if (this.search(element) != null && otherSet.search(element) != null){
				newSLL.insert(element);
			}
		}

		for (T element: otherSet.toArray()){
			if (this.search(element) != null && otherSet.search(element) != null){
				newSLL.insert(element);
			}
		}

		newSLL.removeDuplicates();

		return newSLL;
	}

	@Override
	public void concatenate(SetLinkedList<T> otherSet) {
		if (!this.isEmpty()){
			SingleLinkedListNode<T> auxHead = this.getHead();

			while (!auxHead.getNext().isNIL()){
				auxHead = auxHead.getNext();
			}

			if (!otherSet.isEmpty() && otherSet != null){
				auxHead.setNext(((SingleLinkedListImpl<T>) otherSet).getHead());

			}

		} else {
			if (!otherSet.isEmpty() && otherSet != null){
				this.setHead(((SingleLinkedListImpl<T>) otherSet).getHead());
			}
		}

		this.removeDuplicates();

	}

}

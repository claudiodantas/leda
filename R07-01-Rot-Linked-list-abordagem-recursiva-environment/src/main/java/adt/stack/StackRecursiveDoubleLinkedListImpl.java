package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.RecursiveDoubleLinkedListImpl;

public class StackRecursiveDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> top;
	protected int size;

	public StackRecursiveDoubleLinkedListImpl(int size) {
		this.size = size;
		this.top = new RecursiveDoubleLinkedListImpl<T>();
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (element != null){
			if (!this.isFull()){
				this.top.insertFirst(element);
			} else {
				throw new StackOverflowException();
			}
		}
	}

	@Override
	public T pop() throws StackUnderflowException {
		T result;
		if (!this.isEmpty()){
			result = ((RecursiveDoubleLinkedListImpl<T>) this.top).getData();
			this.top.removeFirst();
		} else {
			throw new StackUnderflowException();
		}

		return result;
	}

	@Override
	public T top() {
		T result = null;

		if (!this.isEmpty()){
			result = ((RecursiveDoubleLinkedListImpl<T>) this.top).getData();
		}

		return result;
	}

	@Override
	public boolean isEmpty() {
		return this.top.isEmpty();
	}

	@Override
	public boolean isFull() {
		return this.top.size() == this.size;
	}

	public DoubleLinkedList<T> getTop(){
		return this.top;

	}
}

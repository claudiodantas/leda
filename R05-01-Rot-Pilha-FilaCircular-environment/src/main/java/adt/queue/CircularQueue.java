package adt.queue;

public class CircularQueue<T> implements Queue<T> {

	private T[] array;
	private int tail;
	private int head;
	private int elements;

	public CircularQueue(int size) {
		array = (T[]) new Object[size];
		head = -1;
		tail = -1;
		elements = 0;
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (isFull()){
			throw new QueueOverflowException();
		} else {

			this.tail = (tail + 1) % this.array.length;

			if (this.head == -1){
				this.head = 0;
			}

			this.array[tail%(array.length)] = element;
			this.elements++;
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		T result = null;
		if (isEmpty()){
			throw new QueueUnderflowException();
		} else {
			result = this.array[this.head];
			this.head = (head + 1) % this.array.length;
		}

		this.elements--;

		return result;
	}

	@Override
	public T head() {
		T result = null;

		if (!isEmpty()){
			result = this.array[this.head];
		}

		return result;
	}

	@Override
	public boolean isEmpty() {
		return this.elements == 0;
	}

	@Override
	public boolean isFull() {
		return this.elements == this.array.length;
	}

}

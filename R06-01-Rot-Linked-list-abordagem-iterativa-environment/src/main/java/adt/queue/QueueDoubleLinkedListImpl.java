package adt.queue;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;

public class QueueDoubleLinkedListImpl<T> implements Queue<T> {

   protected DoubleLinkedList<T> list;
   protected int size;

   public QueueDoubleLinkedListImpl(int size) {
      this.size = size;
      this.list = new DoubleLinkedListImpl<T>();
   }

   @Override
   public void enqueue(T element) throws QueueOverflowException {
      if (!this.isFull()) {
         list.insert(element);
      } else {
         throw new QueueOverflowException();
      }
   }

   @Override
   public T dequeue() throws QueueUnderflowException {
      T result;
      if (!this.isEmpty()) {
         result = ((DoubleLinkedListImpl<T>) list).getHead().getData();
         list.removeFirst();
      } else {
         throw new QueueUnderflowException();
      }

      return result;
   }

   @Override
   public T head() {
      T result = null;
      if (!this.isEmpty()) {
         result = ((DoubleLinkedListImpl<T>) list).getHead().getData();
      }

      return result;
   }

   @Override
   public boolean isEmpty() {
      return this.list.isEmpty();
   }

   @Override
   public boolean isFull() {
      return this.list.size() == this.size;
   }

}

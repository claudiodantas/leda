package adt.stack;

public class StackImpl<T> implements Stack<T> {

   private T[] array;
   private int top;

   @SuppressWarnings("unchecked")
   public StackImpl(int size) {
      array = (T[]) new Object[size];
      top = -1;
   }

   @Override
   public T top() {
      T result = null;

      if (!isEmpty()) {
         result = this.array[top];
      }

      return result;

   }

   @Override
   public boolean isEmpty() {
      return this.top == -1;
   }

   @Override
   public boolean isFull() {
      return this.top == this.array.length - 1;
   }

   @Override
   public void push(T element) throws StackOverflowException {
      if (isFull()) {
         throw new StackOverflowException();
      } else {
         top++;
         this.array[top] = element;
      }
   }

   @Override
   public T pop() throws StackUnderflowException {
      if (isEmpty()) {
         throw new StackUnderflowException();
      } else {
         top--;
      }

      return this.array[top + 1];

   }

}

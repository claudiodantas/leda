package adt.linkedList;

import java.util.ArrayList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

   protected T data;
   protected RecursiveSingleLinkedListImpl<T> next;

   public RecursiveSingleLinkedListImpl() {

   }

   @Override
   public boolean isEmpty() {
	  if (this.data == null) {
		 return true;
	  } else {
		 return false;
	  }
   }

   @Override
   public int size() {
	  if (this.isEmpty()) {
		 return 0;
	  } else {
		 return 1 + this.getNext().size();
	  }

   }

   @Override
   public T search(T element) {
	  T result = null;
	  if (element != null){
		  if (this.isEmpty()) {
			  result = null;
		  } else if (this.data.equals(element)) {
			  result = this.getData();
		  } else {
			  result = this.getNext().search(element);
		  }
	  }

	  return result;
   }

   @Override
   public void insert(T element) {
	  if (element != null){
		  if (this.isEmpty()) {
			  this.setData(element);
			  this.setNext(new RecursiveSingleLinkedListImpl<>());
		  } else {
			  this.getNext().insert(element);
		  }
	  }

   }

   @Override
   public void remove(T element) {
	  if (element != null && !this.isEmpty()) {
		 if (this.getData().equals(element)) {
			this.setData(this.getNext().getData());
			this.setNext(this.getNext().getNext());
		 } else {
			this.getNext().remove(element);
		 }
	  }
   }

   @Override
   public T[] toArray() {
	  ArrayList<T> result = new ArrayList<>();

	  this.toArray(result, this);

	  return ((T[]) result.toArray());
   }

   public void toArray(ArrayList<T> array, RecursiveSingleLinkedListImpl node) {
	  if (!node.isEmpty()) {
		 array.add(((T) node.getData()));
		 toArray(array, node.getNext());
	  }
;
   }

   public T getData() {
	  return data;
   }

   public void setData(T data) {
	  this.data = data;
   }

   public RecursiveSingleLinkedListImpl<T> getNext() {
	  return next;
   }

   public void setNext(RecursiveSingleLinkedListImpl<T> next) {
	  this.next = next;
   }

   //------QUESTÕES DA LISTA-------
	//IndexOf(T element)
	public int indexOf(T element){
	   return this.indexOfRecursive(this, element, 0);
	}

	public int indexOfRecursive(RecursiveSingleLinkedListImpl<T> node, T element, int indice){
      int result;

      if (node.getData().equals(element)){
		   result = 0;
      }if (node.getNext().isEmpty() || indice > node.size()){
         return -1;
     } else {
         result = 1 + indexOfRecursive(node.getNext(), element, indice++);
     }

	   return result;
	}

}

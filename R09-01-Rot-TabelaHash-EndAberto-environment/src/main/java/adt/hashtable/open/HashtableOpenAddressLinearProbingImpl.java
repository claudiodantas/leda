package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionLinearProbing;

public class HashtableOpenAddressLinearProbingImpl<T extends Storable> extends
		AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressLinearProbingImpl(int size,
			HashFunctionClosedAddressMethod method) {
		super(size);
		hashFunction = new HashFunctionLinearProbing<T>(size, method);
		this.initiateInternalTable(size);
	}

	@Override
	public void insert(T element) {
		if (element != null && this.search(element) == null){

			if (super.isFull()){
				throw new HashtableOverflowException();
			}

			boolean troca = false;
			int indice = 0;

			while(!troca){
				int i = ((HashFunctionLinearProbing<T>) this.hashFunction).hash(element, indice);
				if (this.table[i] == null || this.table[i].equals(this.deletedElement)){
					this.table[i] = element;
					this.elements++;
					troca = true;
				} else {
					this.COLLISIONS++;
					indice++;
				}
			}
		}
	}

	@Override
	public void remove(T element) {
		if (!this.isEmpty() && this.search(element) != null){
			boolean achou = false;
			int indice = 0;

			while(!achou){
				int i = ((HashFunctionLinearProbing<T>) this.hashFunction).hash(element, indice);
				if (this.table[i].equals(element)){
					this.table[i] = this.deletedElement;
					this.COLLISIONS = this.COLLISIONS - indice;
					this.elements--;
					achou = true;
				} else {
					indice++;
				}
			}

		}
	}

	@Override
	public T search(T element) {
		T result = null;

		if (element != null && !this.isEmpty()){
			boolean achou = false;
			int indice = 0;

			while(!achou && indice < this.capacity()){
				int i = ((HashFunctionLinearProbing<T>) this.hashFunction).hash(element, indice);
				if (this.table[i] != null && this.table[i].equals(element)){
					result = element;
					achou = true;
				} else {
					indice++;
				}
			}
		}

		return result;
	}

	@Override
	public int indexOf(T element) {
		int result = -1;

		if (element != null && !this.isEmpty()){
			boolean achou = false;
			int indice = 0;

			while(!achou && indice < this.capacity()){
				int i = ((HashFunctionLinearProbing<T>) this.hashFunction).hash(element, indice);
				if (this.table[i].equals(element)){
					result = i;
					achou = true;
				} else {
					indice++;
				}
			}
		}

		return result;

	}

}

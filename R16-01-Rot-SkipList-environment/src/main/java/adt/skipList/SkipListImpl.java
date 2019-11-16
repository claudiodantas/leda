package adt.skipList;

import java.util.ArrayList;
import java.util.List;

public class SkipListImpl<T> implements SkipList<T> {

	protected SkipListNode<T> root;
	protected SkipListNode<T> NIL;

	protected int maxHeight;

	protected double PROBABILITY = 0.5;

	public SkipListImpl(int maxHeight) {
		this.maxHeight = maxHeight;
		root = new SkipListNode(Integer.MIN_VALUE, maxHeight, null);
		NIL = new SkipListNode(Integer.MAX_VALUE, maxHeight, null);
		connectRootToNil();
	}

	/**
	 * Faz a ligacao inicial entre os apontadores forward do ROOT e o NIL Caso
	 * esteja-se usando o level do ROOT igual ao maxLevel esse metodo deve
	 * conectar todos os forward. Senao o ROOT eh inicializado com level=1 e o
	 * metodo deve conectar apenas o forward[0].
	 */
	private void connectRootToNil() {
		for (int i = 0; i < maxHeight; i++) {
			root.forward[i] = NIL;
		}
	}

	
	@Override
	public void insert(int key, T newValue, int height) {
		if (newValue != null && height > 0){
			SkipListNode<T>[] update = new SkipListNode[this.maxHeight];
			SkipListNode<T> aux = this.root;

			//Pesquisa locais
			for (int i = this.maxHeight-1; i >= 0; i--){
				while (aux.getForward(i).getKey() < key){
					aux = aux.getForward(i);
				}

				update[i] = aux; //Guarda caminhos
			}

			aux = aux.getForward(0);

			if (aux.getKey() == key){
				aux.setValue(newValue);
			} else {
				aux = new SkipListNode(key, height, newValue);

				//Altera ponteiros
				for (int i = 0; i < aux.height(); i++){
					aux.getForward()[i] = update[i].getForward(i);
					update[i].getForward()[i] = aux;
				}
			}

		}
	}

	@Override
	public void remove(int key) {
		SkipListNode<T>[] update = new SkipListNode[this.maxHeight];
		SkipListNode<T> aux = this.root;

		//Pesquisa o local
		for (int i = this.maxHeight-1; i >= 0; i--){
			while(aux.getForward(i).getKey() < key){
				aux = aux.getForward(i);
			}
			update[i] = aux; //Guarda o caminho
		}

		aux = aux.getForward(0);

		if (aux.getKey() == key){
			for (int i = 0; i < this.maxHeight; i ++){
				if (update[i].getForward(i).equals(aux)){
					update[i].getForward()[i] = aux.getForward(i);
				}
			}
		}
	}

	@Override
	public int height() {
		return this.maxHeight;
	}

	@Override
	public SkipListNode<T> search(int key) {
		SkipListNode<T> result = this.root;

		for (int i = this.maxHeight - 1; i >= 0; i--){
			//Anda ate o oltimo no com mesma altura
			while (result.getForward(i).getKey() < key){
				result = result.getForward(i);
			}
		}

		result = result.getForward(0);

		if (result.getKey() != key){
			result = null;
		}

		return result;
	}

	@Override
	public int size() {
		int result = 0;
		SkipListNode<T> aux = this.root;

		while(aux.getForward(0).getKey() < Integer.MAX_VALUE){
			result++;
			aux = aux.getForward(0);
		}

		return result;
	}

	@Override
	public SkipListNode<T>[] toArray() {
		List<SkipListNode<T>> lista = new ArrayList<>();
		SkipListNode<T> aux = this.root;

		while(aux.getKey() < Integer.MAX_VALUE){
			lista.add(aux);
			aux = aux.getForward(0);
		}

		lista.add(aux);

		SkipListNode<T>[] result = new SkipListNode[lista.size()];

		for (int i = 0; i < lista.size(); i++){
			result[i] = lista.get(i);
		}

		return result;
	}

}

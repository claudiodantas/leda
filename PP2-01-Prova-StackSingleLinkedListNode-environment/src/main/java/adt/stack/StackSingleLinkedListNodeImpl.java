package adt.stack;

import adt.linkedList.SingleLinkedListNode;

/**
 * Classe que representa um apilha implementada usando-se um noh de uma lista
 * simplesmente ligada, como estrutura sobrejacente. 
 * 
 * Restricoes:
 * - voce DEVE obedecer a politica de acesso e complexidade dos metodos de pilha, ou seja, todos em O(1).
 * - voce NÃO pode usar memoria extra (estrutura auxiliar)
 * - qualquer metodo auxiliar que voce necessite DEVE ser implementado nesta classe
 * - voce NÃO pode modificar a classe SingleLinkedListNode
 * 
 * @author adalbertocajueiro
 *
 * @param <T>
 */
public class StackSingleLinkedListNodeImpl<T> implements Stack<T> {

	private SingleLinkedListNode<T> top;
	private int size;
	private int elementos;

	/**
	 * A pilha para ser criada precisa ter um tamanho maximo
	 * @param tamanhoMaximo
	 */
	public StackSingleLinkedListNodeImpl(int tamanhoMaximo) {
		this.top = new SingleLinkedListNode<T>();
		this.size = tamanhoMaximo;
		this.elementos = 0;
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (element != null){
			if (!isFull()){
				if (isEmpty()){
					this.top.setData(element);
					this.top.setNext(new SingleLinkedListNode<>());
					elementos++;
				} else {
					SingleLinkedListNode<T> novoNode = new SingleLinkedListNode<>(element, this.top);

					this.top = novoNode;
					elementos++;
				}

			} else {
				throw new StackOverflowException();
			}

		}
	}

	@Override
	public T pop() throws StackUnderflowException {
		T result = null;

		if (!isEmpty()){
			result = this.top.getData();
			this.top = this.top.getNext();
			elementos--;

		} else {
			throw new StackUnderflowException();
		}

		return result;
	}

	@Override
	public T top() {
		T result = null;

		if (!this.isEmpty()){
			result = top.getData();
		}

		return result;
	}

	@Override
	public boolean isEmpty() {
		return top.getData() == null;
	}

	@Override
	public boolean isFull() {
		return elementos == size;
	}

}

//Documento sin acentos

package estructuraDeDatos;

import java.util.Iterator;

import exceptions.ListaVaciaException;
import exceptions.TokenNoEncontradoException;

/**
 * La clase lista representa un conjunto de elementos (en esta caso nodos).
 * @author PauloLoaeza
 *
 * @param <E> representa un elemento de la lista, el cual debe ser un objeto
 * que implemente la interfaz comparable.
 * @see Comparable
 * @see Iterable
 */
public class Lista<E extends Comparable<E>> 
implements Iterable<E> {

	public Nodo<E> inicio;

	/**
	 * Construye una lista vacia.
	 */
	public Lista() {
		this.inicio = null;
	}

	/**
	 * Retorna true si la lista esta vacia, de otro modo retorna false.
	 * @return retorna true si la lista esta vacia, de otro modo retorna false.
	 */
	public boolean estaVacia() {
		return this.inicio == null;
	}

	/**
	 * Inserta un elemento al final de la lista.
	 * @param elemento representa el objeto que se va a almacenar en la lista.
	 */
	public void agregarAlFinal(E elemento) {
		Nodo<E> nuevo = new Nodo<E>(elemento);
		if (estaVacia()) {
			this.inicio = nuevo;
		}
		else {
			Nodo<E> aux = this.inicio;
			while (aux.getSiguiente() != null) {
				aux = aux.getSiguiente();
			}
			aux.setSiguiente(nuevo);
		}
	}

	/**
	 * Inserta un elemento al inicio de la lista
	 * @param elemento representa el objeto que se va a almacenar en la lista.
	 */
	public void agregarAlInicio(E elemento) {
		Nodo<E> nuevo = new Nodo<E>(elemento);

		if (!estaVacia()) {
			nuevo.setSiguiente(this.inicio);
		}

		this.inicio = nuevo;
	}

	/**
	 * Inserta un elemento justo antes que el token en la lista.
	 * 
	 * En caso de que hubiera un token repetido, el elemento de inserccion
	 * se almacenara antes del primer token que se encuentre primero en la
	 * lista de inicio a fin de la misma.
	 * 
	 * @param elemento representa el elemento que se va a insertar en la lista.
	 * @param token representa el elemento con el cual se va a comparar con el
	 * 				elemento de inserccion.
	 * @throws TokenNoEncontradoException lanza esta excepcion si el token no
	 * 									  se encuentra en la lista.
	 * @throws ListaVaciaException lanza esta excepcion si la lista esta vacia.
	 */
	public void insertarAntesQueToken(E elemento, E token) 
			throws TokenNoEncontradoException, ListaVaciaException {

		if (estaVacia()) {
			throw new ListaVaciaException("La lista está vacía");
		}
		Nodo<E> aux = this.inicio;
		Nodo<E> ant = null;

		while (aux != null && aux.getValor().compareTo(token) != 0) {
			ant = aux;
			aux = aux.getSiguiente();
		}

		if (aux == null) {
			throw new TokenNoEncontradoException("No se encontró el token");
		}

		Nodo<E> nuevo = new Nodo<E>(elemento);
		if (ant == null) {
			nuevo.setSiguiente(this.inicio);
			this.inicio = nuevo;
		}
		else {
			nuevo.setSiguiente(aux);
			ant.setSiguiente(nuevo);
		}
	}

	/**
	 * Inserta un elemento justo despues que el token en la lista.
	 * 
	 * En caso de que hubiera un token repetido, el elemento de inserccion
	 * se almacenara despues del primer token que se encuentre primero en la
	 * lista de inicio a fin de la misma.
	 * 
	 * @param elemento representa el elemento que se va a insertar en la lista.
	 * @param token representa el elemento con el cual se va a comparar con el
	 * 		elemento de inserccion.
	 * @throws TokenNoEncontradoException lanza esta excepcion si el token no
	 * 		se encuentra en la lista.
	 * @throws ListaVaciaException lanza esta excepcion si la lista esta vacia.
	 */
	public void insertarDespuesQueToken(E elemento, E token) 
			throws TokenNoEncontradoException, ListaVaciaException {

		if (estaVacia()) {
			throw new ListaVaciaException("La lista está vacía");
		}

		Nodo<E> aux = this.inicio;
		while (aux != null && aux.getValor().compareTo(token) != 0) {
			aux = aux.getSiguiente();
		}

		if (aux == null) {
			throw new TokenNoEncontradoException("Token no encontrado");
		}

		Nodo<E> nuevo = new Nodo<E>(elemento);
		if (aux.getSiguiente() != null) {
			nuevo.setSiguiente(aux.getSiguiente());
		}
		aux.setSiguiente(nuevo);
	}

	/**
	 * Elimina un elemento especifico de la lista.
	 * @param token elemento que sera borrado de la lista.
	 * @throws TokenNoEncontradoException lanza esta excepcion si el token no
	 * 		se encuentra en la lista.
	 * @throws ListaVaciaException lanza esta excepcion si la lista esta vacia.
	 */
	public void eliminarToken(E token) 
			throws TokenNoEncontradoException, ListaVaciaException {

		if (estaVacia()) {
			throw new ListaVaciaException("La lista está vacía");
		}

		Nodo<E> aux = this.inicio;
		Nodo<E> ant = null;
		while (aux != null && !aux.getValor().equals(token)) {
			ant = aux;
			aux = aux.getSiguiente();
		}

		if (aux == null) {
			throw new TokenNoEncontradoException("Token no encontrado");
		}

		if (ant == null) {
			this.inicio = aux.getSiguiente();
		}
		else {
			ant.setSiguiente(aux.getSiguiente() != null ? aux.getSiguiente() : null);
		}
	}

	/**
	 * Muestra los elementos de la lista en la consola.
	 */
	public void show() {
		if (estaVacia()) {
			System.out.println("Lista vacia");
		} 
		else {
			for (E e : this) {
				System.out.print("{" + e.toString() + "}-");
			}
		}
	}

	/**
	 * Retorna un objeto de tipo Iterator.
	 * @see java.util.Iterator
	 */
	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {

			private Nodo<E> aux = inicio;

			@Override
			public boolean hasNext() {
				return aux != null;
			}

			@Override
			public E next() {
				E valor = aux.getValor();
				aux = aux.getSiguiente();
				return valor;
			}
		};
	}

}
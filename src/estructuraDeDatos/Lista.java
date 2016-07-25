//Documento sin acentos

package estructuraDeDatos;

import java.util.Iterator;

import exceptions.IndiceInvalidoException;
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

	private Nodo<E> inicio;

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
	 * Inserta un nuevo elemento a la lista de manera ordenada de menor a
	 * mayor.
	 * 
	 * @param elemento elemento de inserccion.
	 */
	public void insertarDeMenorAMayor(E elemento) {
		Nodo<E> nuevo = new Nodo<E>(elemento);

		if (estaVacia()) {
			this.inicio = nuevo;
		}
		else {
			Nodo<E> aux = this.inicio;
			Nodo<E> ant = null;

			while (aux != null && nuevo.compareTo(aux) > 0) {
				ant = aux;
				aux = aux.getSiguiente();
			}

			if (ant == null) {
				nuevo.setSiguiente(this.inicio);
				this.inicio = nuevo;
			}
			else {
				ant.setSiguiente(nuevo);
				nuevo.setSiguiente(aux);
			}
		}
	}

	/**
	 * Inserta un nuevo elemento a la lista de manera ordenada de mayor
	 * a menor.
	 * @param elemento elemento de inserccion.
	 */
	public void insertarDeMayorAMenor(E elemento) {
		Nodo<E> nuevo = new Nodo<E>(elemento);

		if (estaVacia()) {
			this.inicio = nuevo;
		}
		else {
			Nodo<E> aux = this.inicio;
			Nodo<E> ant = null;

			while (aux != null && nuevo.compareTo(aux) < 0) {
				ant = aux;
				aux = aux.getSiguiente();
			}

			if (ant == null) {
				nuevo.setSiguiente(this.inicio);
				this.inicio = nuevo;
			}
			else {
				ant.setSiguiente(nuevo);
				nuevo.setSiguiente(aux);
			}
		}
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
	 * Determina el tamaño de la lista.
	 * @return numero de elementos de la lista.
	 */
	public int size() {
		int elementos = 0;

		Nodo<E> aux = this.inicio;
		while (aux != null) {
			elementos++;
			aux = aux.getSiguiente();
		}

		return elementos;
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
	 * Elimina todos los elementos iguales de una lista.
	 * @param token elemento a eliminar.
	 * @throws TokenNoEncontradoException lanzada cuando no existe algun elemento
	 * 		en la lista.
	 * @throws ListaVaciaException lanzada cuando la lista esta vacia.
	 */
	public void eliminarTokens(E token) 
			throws TokenNoEncontradoException, ListaVaciaException {

		boolean bandera = true;
		int intentos = 0;

		do {
			try {
				intentos++;
				eliminarToken(token);
			} 
			catch (TokenNoEncontradoException e) {
				if (intentos == 1) {
					throw new TokenNoEncontradoException("Token no existe en la"
							+ " lista");
				}
				bandera = false;
			}
		}
		while (bandera);

	}

	/**
	 * Verifica si la lista esta ordenada de menor a mayor.
	 * @return retorna true si la lista esta ordenada, de otro modo retorna false.
	 * @throws ListaVaciaException lanza esta excepcion si la lista esta vacia.
	 */
	public boolean estaOrdenada() throws ListaVaciaException {
		if (estaVacia()) {
			throw new ListaVaciaException("La Lista esta vacia");
		}

		Nodo<E> aux = this.inicio;
		Nodo<E> sig = aux.getSiguiente();

		while (aux != null && sig != null && aux.compareTo(sig) < 0) {
			aux = sig;
			sig = sig.getSiguiente();
		}

		return sig == null;
	}


	/**
	 * Verifica si la lista se encuentra ordenada de manera ascendente o
	 * descendente.
	 * @param menor indica la manera de evaluar el ordenamiento de la lista.
	 * true si se evaluara que la lista esta ordenada de menor a mayor, false
	 * si se evaluara de mayor a menor.
	 * @return true si la lista esta ordenada, false si no lo esta.
	 * @throws ListaVaciaException 
	 */
	public boolean estaOrdenada(boolean menor) throws ListaVaciaException {
		if (menor) {
			return estaOrdenada();
		}

		Nodo<E> aux = this.inicio;
		Nodo<E> sig = aux.getSiguiente();

		while (aux != null && sig != null && aux.compareTo(sig) > 0) {
			aux = sig;
			sig = sig.getSiguiente();
		}

		return sig == null;
	}

	/**
	 * Ordena la lista de menor a mayor por medio del metodo burbuja.
	 * @throws ListaVaciaException lanza esta excepcion si la lista esta vacia.
	 */
	public void ordenarDeMenorAMayor() throws ListaVaciaException {
		if (estaVacia()) {
			throw new ListaVaciaException("La Lista esta vacia");
		}

		Nodo<E> aux = this.inicio;
		Nodo<E> aux2 = null;

		while (aux != null) {
			aux2 = aux.getSiguiente();
			while (aux2 != null) {
				if (aux.compareTo(aux2) > 0) {
					intercambiar(aux, aux2);
				}
				aux2 = aux2.getSiguiente();
			}
			aux = aux.getSiguiente();
		}
	}

	/**
	 * Ordena la lista de mayor a menor por medio del metodo burbuja.
	 * @throws ListaVaciaException lanza esta excepcion si la lista esta vacia.
	 */
	public void ordenarDeMayorAMenor() throws ListaVaciaException {
		if (estaVacia()) {
			throw new ListaVaciaException("La Lista esta vacia");
		}

		Nodo<E> aux = this.inicio;
		Nodo<E> aux2 = null;

		while (aux != null) {
			aux2 = aux.getSiguiente();
			while (aux2 != null) {
				if (aux.compareTo(aux2) < 0) {
					intercambiar(aux, aux2);
				}
				aux2 = aux2.getSiguiente();
			}
			aux = aux.getSiguiente();
		}
	}

	/**
	 * Verifica la existencia de un elemento.
	 * @param elemento token a verificar.
	 * @return true si el elemento se encuentra en esta lista, de otro modo false.
	 */
	public boolean contiene(E elemento) {
		if (estaVacia()) {
			return false;
		}

		Nodo<E> aux = this.inicio;
		while (aux != null && aux.getValor().compareTo(elemento) != 0) {
			aux = aux.getSiguiente();
		}

		return aux != null;
	}

	/**
	 * Retorna el elemento que se encuentra en la posicion del indice de la lista.
	 * @param indice indice del elemento en la lista.
	 * @return retorna el elemento de la lista que se encuentra en la lista, si el
	 * indice es mayor al tamaño de la lista retorna null.
	 */
	public E get(int indice) throws IndiceInvalidoException {
		if (indice < 0) {
			throw new IndiceInvalidoException("No se aceptan indices negativos");
		}

		if (estaVacia()) {
			return null;
		}

		int pasos = 0;

		Nodo<E> aux = this.inicio;
		while (aux != null && pasos < indice) {
			pasos++;
			aux = aux.getSiguiente();
		}

		return aux.getValor();
	}

	/**
	 * Intercambia los valores que se encuentran en los nodos a y b.
	 * El valor de a pasa a ser el valor de b, y viceversa.
	 * @param a Nodo a
	 * @param b Nodo b
	 */
	private void intercambiar(Nodo<E> a, Nodo<E> b) {
		E valor = a.getValor();
		a.setValor(b.getValor());
		b.setValor(valor);
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
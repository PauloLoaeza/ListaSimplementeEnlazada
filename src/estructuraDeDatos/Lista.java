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
		/*
		 * Creacion de un nuevo nodo. El elemento sera su valor
		 */
		Nodo<E> nuevo = new Nodo<E>(elemento);
		
		/*
		 * Si la lista esta vacia el nuevo nodo sera el primer
		 * elemento de la lista, o mejor dicho el inicio
		 */
		
		if (estaVacia()) {
			this.inicio = nuevo;
		}
		else {
			//Nodo auxiliar para recorrer la lista
			Nodo<E> aux = this.inicio;
			
			//Colocamos el auxiliar al final de la lista
			while (aux.getSiguiente() != null) {
				aux = aux.getSiguiente();
			}
			
			//Se asigna el siguiente de auxiliar como el nuevo nodo
			aux.setSiguiente(nuevo);
		}
	}

	/**
	 * Inserta un elemento al inicio de la lista
	 * @param elemento representa el objeto que se va a almacenar en la lista.
	 */
	public void agregarAlInicio(E elemento) {
		/*
		 * Creacion de un nuevo nodo. El elemento sera su valor
		 */
		Nodo<E> nuevo = new Nodo<E>(elemento);

		/*
		 * Si la lista tiene al menos un elemento se le asignara
		 * el siguiente de nuevo como el inicio.
		 */
		if (!estaVacia()) {
			nuevo.setSiguiente(this.inicio);
		}

		// El nodo nuevo pasa a ser el nodo de inicio de la lista
		this.inicio = nuevo;
	}

	/**
	 * Inserta un nuevo elemento a la lista de manera ordenada de menor a
	 * mayor.
	 * 
	 * @param elemento elemento de inserccion.
	 */
	public void insertarDeMenorAMayor(E elemento) {
		/*
		 * Creacion de un nuevo nodo. El elemento sera su valor
		 */
		Nodo<E> nuevo = new Nodo<E>(elemento);

		//Si la lista esta vacia, el nodo de incio sera el nodo nuevo
		if (estaVacia()) {
			this.inicio = nuevo;
		}
		else {
			//Nodo auxiliar para recorrer la lista
			Nodo<E> aux = this.inicio;
			//Nodo anterior para estar siempre anterior al nodo auxiliar
			Nodo<E> ant = null;

			/*
			 * El nodo auxiliar ira recorriendo la lista siempre y cuando sea
			 * diferente de null y el valor del nodo nuevo sea mayor al valor
			 * del nodo auxliliar
			 */
			while (aux != null && nuevo.compareTo(aux) > 0) {
				ant = aux;
				aux = aux.getSiguiente();
			}

			// si en nodo auxiliar es igual al nodo inicial
			if (ant == null) {
				//se agrega el nodo al inicio de la lista
				nuevo.setSiguiente(this.inicio);
				this.inicio = nuevo;
			}
			else {
				/*
				 * El nodo nuevo debe estar entre el nodo anterior
				 * y el nodo auxiliar
				 */
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
		/*
		 * Creacion de un nuevo nodo. El elemento sera su valor
		 */
		Nodo<E> nuevo = new Nodo<E>(elemento);

		//si la lista esta vacia, nuevo sera el nuevo inicial
		if (estaVacia()) {
			this.inicio = nuevo;
		}
		else {
			//Nodo auxiliar para recorrer la lista
			Nodo<E> aux = this.inicio;
			//Nodo anterior que siempre estara antes del auxiliar
			Nodo<E> ant = null;
			
			/*
			 * El nodo auxiliar recorrera la lista siempre y cuando 
			 * sea diferente de null y el valor del nodo nuevo sea
			 * menor al valor del nodo auxiliar
			 */
			while (aux != null && nuevo.compareTo(aux) < 0) {
				ant = aux;
				aux = aux.getSiguiente();
			}

			//si auxiliar es igual al nodo inicial
			if (ant == null) {
				//se inserta el nuevo nodo al inicio
				nuevo.setSiguiente(this.inicio);
				this.inicio = nuevo;
			}
			else {
				/*
				 * El nodo nuevo debera estar entre el nodo 
				 * anterior y el nodo auxiliar
				 */
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

		//Si la lista esta vacia lanza la excepcion
		if (estaVacia()) {
			throw new ListaVaciaException("La lista está vacía");
		}
		
		//Nodo auxiliar para recorrer la lista
		Nodo<E> aux = this.inicio;
		//Nodo anterior que se encontrara antes del nodo auxiliar
		Nodo<E> ant = null;

		/*
		 * El nodo auxiliar estara recorriendo la lista siempre y cuando
		 * no sea un nodo nulo y que su valor sea diferente al token
		 */
		while (aux != null && aux.getValor().compareTo(token) != 0) {
			ant = aux;
			aux = aux.getSiguiente();
		}

		//Si el nodo auxiliar paso por todos los elementos de la lista
		if (aux == null) {
			throw new TokenNoEncontradoException("No se encontró el token");
		}

		//Creacion de un nuevo nodo. El elemento sera su valor
		Nodo<E> nuevo = new Nodo<E>(elemento);
		
		//si el nodo auxiliar es el nodo inicial
		if (ant == null) {
			//se inserta el nodo nuevo al inicio de la lista
			nuevo.setSiguiente(this.inicio);
			this.inicio = nuevo;
		}
		else {
			//El nodo nuevo estara entre el nodo auxiliar y anterior
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

		//Si la lista esta vacia lanza una excepcion
		if (estaVacia()) {
			throw new ListaVaciaException("La lista está vacía");
		}

		//Nodo auxiliar para recorrer la lista
		Nodo<E> aux = this.inicio;
		/*
		 * mientras el nodo auxiliar no sea nulo y su valor sea diferente 
		 * de token 
		 */
		while (aux != null && aux.getValor().compareTo(token) != 0) {
			//avanza
			aux = aux.getSiguiente();
		}

		//si ya recorrio toda la lista lanza una excepcion
		if (aux == null) {
			throw new TokenNoEncontradoException("Token no encontrado");
		}

		//Se crea un nodo nuevo con su valor = elemento
		Nodo<E> nuevo = new Nodo<E>(elemento);
		/*
		 * si el nodo donde se encuentra auxiliar no es el
		 * ultimo elemento de la lista
		 */
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

		/*
		 * Nodo auxiliar para recorrer la lista, avanza mientras
		 * no sea nulo, y cada vez que esto suceda aumentan los 
		 * elementos una unidad
		 */
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

		//Si se intenta eliminar un elemento nulo lanza una excepcion
		if (token == null) {
			throw new TokenNoEncontradoException("Token nulo");
		}

		//Si la lista esta vacia lanza una excepcion
		if (estaVacia()) {
			throw new ListaVaciaException("La lista está vacía");
		}

		/*
		 * Nodo auxiliar para recorrer la lista,
		 * Nodo anterior que siempre ira detras del auxiliar
		 */
		Nodo<E> aux = this.inicio;
		Nodo<E> ant = null;
		/*
		 * Avanzan los nodos aux y ant siempre y cuando aux no sea 
		 * nulo y su valor sea diferente de token
		 */
		while (aux != null && !aux.getValor().equals(token)) {
			ant = aux;
			aux = aux.getSiguiente();
		}

		//si aux ya recorrio toda la lista 
		if (aux == null) {
			throw new TokenNoEncontradoException("Token no encontrado");
		}

		//si aux == inicio
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

		/*
		 * Ejecutara el metodo eliminarToken(E token) de esta clase todas
		 * las veces que sea necesaria hasta que lanze una excepcion.
		 * 
		 */
		do {
			try {
				intentos++;
				eliminarToken(token);
			} 
			catch (TokenNoEncontradoException e) {
				/*
				 * Si el metodo eliminatToken(E token) lanza una excepcion
				 * la primera vez que se ejecuta significa que el token 
				 * nunca exitio en la lista y eso lo damos a conocer 
				 * lanzando una excepcion con un mensaje.
				 */
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

		/*
		 * Nodo auxiliar para recorrer la lista
		 * Nodo siguiente que se encontrara siguiente al nodo aux
		 */
		Nodo<E> aux = this.inicio;
		Nodo<E> sig = aux.getSiguiente();

		/*
		 * Avanza los punteros mientras aux no sea nulo y su valor
		 * sea menor al valor del nodo sig
		 */
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
	 * @throws ListaVaciaException se lanza cuando la lista esta vacia.
	 */
	public boolean estaOrdenada(boolean menor) throws ListaVaciaException {
		//Si menor == true significa que ordena la lista de menor a mayor
		if (menor) {
			return estaOrdenada();
		}
		
		/*
		 * Nodo auxiliar para recorrer la lista
		 * Nodo siguiente que se encontrara siguiente al nodo aux
		 */
		Nodo<E> aux = this.inicio;
		Nodo<E> sig = aux.getSiguiente();

		/*
		 * Avanza los punteros mientras aux no sea nulo y su valor
		 * sea mayor al valor del nodo sig
		 */
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

		/*
		 * Nodo auxiliar para recorrer la lista
		 * Nodo siguiente que se encontrara siguiente al nodo aux
		 */
		Nodo<E> aux = this.inicio;
		Nodo<E> sig = null;

		//Metodo burbuja
		while (aux != null) {
			sig = aux.getSiguiente();
			while (sig != null) {
				if (aux.compareTo(sig) > 0) {
					intercambiar(aux, sig);
				}
				sig = sig.getSiguiente();
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

		/*
		 * Nodo auxiliar para recorrer la lista
		 * Nodo siguiente que se encontrara siguiente al nodo aux
		 */
		Nodo<E> aux = this.inicio;
		Nodo<E> sig = null;

		//Metodo burbuja
		while (aux != null) {
			sig = aux.getSiguiente();
			while (sig != null) {
				if (aux.compareTo(sig) < 0) {
					intercambiar(aux, sig);
				}
				sig = sig.getSiguiente();
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

		/*
		 * Nodo auxiliar para recorrer la lista
		 * aux recorre la lista siempre y cuando no sea nulo y su valor
		 * sea diferente del parametro elemento.
		 */
		Nodo<E> aux = this.inicio;
		while (aux != null && aux.getValor().compareTo(elemento) != 0) {
			aux = aux.getSiguiente();
		}

		//si aux no es nulo significa que el nodo con valor = elemento existe
		return aux != null;
	}

	/**
	 * Retorna el elemento que se encuentra en la posicion del indice de la lista.
	 * @param indice indice del elemento en la lista.
	 * @return retorna el elemento de la lista que se encuentra en la lista, si el
	 * indice es mayor al tamaño de la lista retorna null.
	 * @throws IndiceInvalidoException se lanza cuando el indice es negativo.
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
	 * Elimina un elemento en la lista con base en la posicion del elemento
	 * @param indice posicion del elemento a eliminar.
	 * @throws IndiceInvalidoException se lanza cuando el indice es negativo o
	 * 		cuando sobrepasa el tamano de la lista.
	 * @throws ListaVaciaException se lanza cuando la lista esta vacia.
	 */
	public void eliminar(int indice)
			throws IndiceInvalidoException, ListaVaciaException {
		if (indice < 0 || indice > size()) {
			throw new IndiceInvalidoException("Indice negativo o mayor"
					+ "que el tamaño de la lista");
		}
		
		if (estaVacia()) {
			throw new ListaVaciaException("Lista vacia");
		}
		
		int pasos = 0;
		
		Nodo<E> aux = this.inicio;
		while (aux != null && pasos != indice) {
			pasos++;
			aux = aux.getSiguiente();
		}
		
		try {
			eliminarToken(aux.getValor());
		} catch (TokenNoEncontradoException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Deja la lista sin elementos.
	 */
	public void limpiar() {
		this.inicio.setSiguiente(null);
		this.inicio = null;
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
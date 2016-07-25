// Documento sin acentos

package estructuraDeDatos;

/**
 * La clase nodo representa un elemento de la lista, en el cual contiene como
 * dato un valor de tipo generico y este valor debe implementar Comparable para que
 * pueda ser un elemento valido en el nodo.
 * @author PauloLoaeza
 *
 * @param T representa un objeto, el cual debe implementar Comparable.
 */
public class Nodo<T extends Comparable<T>> 
implements Comparable<Nodo<T>> {

	private T valor;
	private Nodo<T> siguiente;

	/**
	 * Construye un nodo vacio.
	 */
	public Nodo() {
		this.valor = null;
		this.siguiente = null;
	}

	/**
	 * Construye un nodo con un valor dentro del mismo.
	 * @param valor representa el valor que contendra el nodo.
	 */
	public Nodo(T valor) {
		this.valor = valor;
		this.siguiente = null;
	}

	/**
	 * Retorna el valor que se encuentra almacenado en el nodo.
	 * @return retorna el valor del nodo. retorna null si el valor es nulo.
	 */
	public T getValor() {
		return valor;
	}

	/**
	 * Asigna el valor dentro del nodo.
	 * @param valor representa el objeto que se almacenara dentro del nodo.
	 */
	public void setValor(T valor) {
		this.valor = valor;
	}

	/**
	 * Retorna el nodo que se encuentra despues de este nodo.
	 * @return retorna el nodo que se encuentra despues de este nodo.
	 */
	public Nodo<T> getSiguiente() {
		return siguiente;
	}

	/**
	 * Asocia el nodo que sera el nodo que se encuentre despues de este nodo.
	 * @param siguiente nodo que estara despues de este nodo.
	 */
	public void setSiguiente(Nodo<T> siguiente) {
		this.siguiente = siguiente;
	}


	/**
	 * Compara el valor que se encuentra en este nodo con el valor que se
	 * encuentra en el nodo o.
	 * 
	 * @return retorna un numero negativo, 0 o numero positivo si el valor 
	 * de este nodo es menor, igual o mayor que el valor del nodo o.
	 */
	@Override
	public int compareTo(Nodo<T> o) {
		return this.valor.compareTo(o.valor);
	}

}
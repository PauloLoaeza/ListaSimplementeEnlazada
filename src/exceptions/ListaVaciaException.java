package exceptions;

/**
 * La excepcion ListaVaciaException es lanzada cuando una lista de elementos
 * no conserva ningun elemento en su interior, y se requiere realizar
 * operaciones con dichos elementos
 * @author PauloLoaeza
 *
 */
public class ListaVaciaException extends Exception {

	private static final long serialVersionUID = -3290998074339513158L;

	/**
	 * Construye una excepcion de lista vacia.
	 */
	public ListaVaciaException() {
		super();
	}

	/**
	 * Construye una excepcion de lista vacia con su respectivo mensaje.
	 * @param mensaje mensaje de la excepcion.
	 */
	public ListaVaciaException(String mensaje) {
		super(mensaje);
	}

}
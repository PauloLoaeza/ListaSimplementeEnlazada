package exceptions;

/**
 * La excepcion IndiceInvalidoException es lanzada cuando se busca realizar
 * operaciones con una lista desde un indice negativo.
 * @author PauloLoaeza
 *
 */
public class IndiceInvalidoException extends Exception {

	private static final long serialVersionUID = -4926409947603300437L;

	/**
	 * Construye un IndiceInvalidoException vacio.
	 */
	public IndiceInvalidoException() {
		super();
	}

	/**
	 * Construye un IndiceInvalidoException con su respectivo mensaje.
	 */
	public IndiceInvalidoException(String mensaje) {
		super(mensaje);
	}

}
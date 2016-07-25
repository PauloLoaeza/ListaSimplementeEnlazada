package exceptions;

/**
 * La excepcion TokenNoEncontradoException es lanzada cuando se requiere
 * buscar un elemento en un conjuto de elemento y este elemento no se 
 * encuentra en el mismo.
 * @author PauloLoaeza
 *
 */
public class TokenNoEncontradoException extends Exception {

	private static final long serialVersionUID = 7222181155371237635L;

	/**
	 * Construye una excepcion de token no encontrado.
	 */
	public TokenNoEncontradoException() {
		super();
	}

	/**
	 * Construye una excepcion de token no encontrado con su respectivo
	 * mensaje.
	 * @param mensaje mensaje de la excepcion.
	 */
	public TokenNoEncontradoException(String mensaje) {
		super(mensaje);
	}

}
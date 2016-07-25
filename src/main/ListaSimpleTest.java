package main;

import estructuraDeDatos.Lista;
import exceptions.IndiceInvalidoException;
import exceptions.ListaVaciaException;
import exceptions.TokenNoEncontradoException;

/**
 * Clase para hacer pruebas con la clase Lista.
 * @author PauloLoaeza
 *
 */
public class ListaSimpleTest {

	public static void main(String ... args) {

		/*
		 * Test 1: crear una nueva lista e imprimir si esta vacia o no. 
		 */
		Lista<Integer> lista1 = new Lista<Integer>();
		System.out.println(lista1.estaVacia() ? "Test 1.- Lista vacía.\n" : 
				"Test 1.- Lista con elementos\n");

		/*
		 * Test 2: agregar 5 elementos al final de la lista1 e imprimirlos 
		 * 		   en consola. Imprimir si esta vacia o con elementos.
		 */

		for (int i = 0; i < 5; i++) {
			lista1.agregarAlFinal(i);
		}
		System.out.println(lista1.estaVacia() ? "Test 2.- Lista vacía." : 
				"Test 2.- Lista contiene elementos");
		System.out.println("Test 2.- Primeros 5 elementos: ");
		lista1.show();
		System.out.println();

		/*
		 * Test 3: ocupar la lista1 para agregar 5 elementos al inicio de
		 * la lista y mostrarlo en la consola.
		 */

		for (int i = 5; i < 10; i++) {
			lista1.agregarAlInicio(i);
		}
		System.out.println("\nTest 3.- Últimos 5 elementos: ");
		lista1.show();
		System.out.println();

		/*
		 * Test 4.- Insertar un elemento antes que un numero y mostrarlo.
		 */
		try {
			lista1.insertarAntesQueToken(11, 5);
		} catch (TokenNoEncontradoException | ListaVaciaException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("\nTest 4.- Número 11 agregado antes que el 5");
		lista1.show();
		System.out.println();

		/*
		 * Test 5.- Insertar un elemento despues que un numero y mostrarlo.
		 */

		try {
			lista1.insertarDespuesQueToken(12, 3);
		} catch (TokenNoEncontradoException | ListaVaciaException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("\nTest 5.- Número 12 agregado después que el 3");
		lista1.show();
		System.out.println();

		/*
		 * Test 6.- Eliminiar un elemento de la lista y mostrarlo.
		 */

		try {
			lista1.eliminarToken(0);
		} catch (TokenNoEncontradoException | ListaVaciaException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("\nTest 6.- Se eliminó el elemento 0");
		lista1.show();

		/*
		 * Test 7.- Verificar si la lista esta ordenada e imprimir el resultado.
		 */

		try {
			System.out.println("\n\nTest 7.- " + (lista1.estaOrdenada() ? 
					"La lista esta ordenada" : "La lista esta desordenada"));
		} catch (ListaVaciaException e) {
			System.out.println(e.getMessage());
		}

		/*
		 * Test 8.- Ordenar la lista de menor a mayor, preguntar si la lista esta 
		 * ordenada y mostrarla.
		 */

		try {
			lista1.ordenarDeMenorAMayor();
			System.out.println("\n\nTest 8.- " + (lista1.estaOrdenada() ? 
					"La lista esta ordenada" : "La lista esta desordenada"));
			lista1.show();
		} catch (ListaVaciaException e) {
			System.out.println(e.getMessage());
		}

		/*
		 * Test 9.- Ordenar la lista de mayor a menor, proguntar si esta ordenada 
		 * y mosrarla.
		 */

		try {
			lista1.ordenarDeMayorAMenor();
			System.out.println("\n\nTest 9.- " + (lista1.estaOrdenada(false) ? 
					"La lista esta ordenada de mayor a menor" : 
					"La lista esta desordenada"));
			lista1.show();
		} catch (ListaVaciaException e) {
			System.out.println(e.getMessage());
		}

		/*
		 * Test 10.- Insertar 3 datos de manera ordenada de menor a mayor y mostrarlos.
		 */

		lista1.insertarDeMenorAMayor(3);
		lista1.insertarDeMenorAMayor(7);
		lista1.insertarDeMenorAMayor(1);

		System.out.println("\n\nTest 10-.- Se insertaron 3 elementos: 3, 7 y 1");
		lista1.show();

		/*
		 * Test 11.- Insertar 3 datos de manera ordenada de mayor a menor y mostrarlos.
		 */
		lista1.insertarDeMayorAMenor(12);
		lista1.insertarDeMayorAMenor(0);
		lista1.insertarDeMayorAMenor(6);

		System.out.println("\n\nTest 11-.- Se insertaron 3 elementos: 12, 0 y 6");
		lista1.show();

		/*
		 * Test 12.- Ordenar la lista de mayor a menor y eliminar el elemento 
		 * 			repetido y mostrarlo
		 */
		System.out.println("\n\nTest 12.- Lista ordenada de mayor a menor");
		try {
			lista1.ordenarDeMayorAMenor();
		} catch (ListaVaciaException e) {
			System.out.println(e.getMessage());
		}
		lista1.show();

		try {
			System.out.println("\nTest 12.- Se eliminaron los elementos 12, 7, 6, 3 y 1");
			lista1.eliminarTokens(12);
			lista1.eliminarTokens(7);
			lista1.eliminarTokens(6);
			lista1.eliminarTokens(3);
			lista1.eliminarTokens(1);
		} catch (TokenNoEncontradoException | ListaVaciaException e) {
			System.out.println(e.getMessage());
		}
		lista1.show();

		/*
		 * Test 13.- Determina el numero de elementos que existe en la lista.
		 */
		System.out.println("\n\nTest 13.- La lista cuenta con " + lista1.size()
		+ " elementos");

		/*
		 * Test 14.- Determina si la lista contiene dos elementos.
		 */
		boolean contiene2 = lista1.contiene(2);
		boolean contiene7 = lista1.contiene(7);
		System.out.println("\nTest 14.- La lista " + (contiene2 ? "contiene 2" :
				"no contiene 2") );
		System.out.println("Test 14.- La lista " + (contiene7 ? "contiene 7" :
				"no contiene 7") );

		/*
		 * Test 15.- Obtener un elemento en base a un indice
		 */
		try {
			System.out.println("\nTest 15.- \nElemento en la posicion 0: " + lista1.get(0));
			System.out.println("Elemento en la posicion 3: " + lista1.get(3));
			System.out.println("Elemento en la posicion 6: " + lista1.get(6));
			lista1.show();
		} catch (IndiceInvalidoException e) {
			System.out.println(e.getMessage());
		}
	}
} 
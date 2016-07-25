package main;

import estructuraDeDatos.Lista;
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
		System.out.println(lista1.estaVacia() ? "Test 1.- Lista vacía.\n" : "Test 1.- Lista con elementos\n");

		/*
		 * Test 2: agregar 5 elementos al final de la lista1 e imprimirlos 
		 * 		   en consola. Imprimir si esta vacia o con elementos.
		 */

		for (int i = 0; i < 5; i++) {
			lista1.agregarAlFinal(i);
		}
		System.out.println(lista1.estaVacia() ? "Test 2.- Lista vacía." : "Test 2.- Lista contiene elementos");
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
	}
}
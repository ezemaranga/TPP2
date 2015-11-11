package Interfaces;

import TDA.ConjuntoTDA;

public interface DiccionarioMultipleTDA {
	
	void InicializarDiccionario(); // PC: -
	
	void Agregar(int clave, int valor); // PC: Diccionario Inicializado
	
	void Eliminar(int clave); // PC: Diccionario Inicializado
	
	void EliminarValor(int clave, int valor); // PC: Diccionario Inicializado
	
	ConjuntoTDA Recuperar(int clave); // PC: Diccionario Inicializado
	
	ConjuntoTDA Claves(); // PC: Diccionario Inicializado
	
}
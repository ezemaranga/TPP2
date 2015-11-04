package Interfaces;

import TDA.ConjuntoTDA;

public interface DiccionarioSimpleTDA {
	void InicializarDiccionario(); // PC: -
	void Agregar(int clave, int valor); // PC: Diccionario Inicializado
	void Eliminar(int clave); // PC: Diccionario Inicializado
	int Recuperar(int clave); // PC: Diccionario Inicializado y Clave Existente
	ConjuntoTDA Claves(); // PC: Diccionario Inicializado
}

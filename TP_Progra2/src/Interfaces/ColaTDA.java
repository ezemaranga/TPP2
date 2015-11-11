package Interfaces;

import tp.Tiempo;

public interface ColaTDA {
	
	void inicializarCola();
	
	//siemre que la cola este inicializada
	void acolar(Tiempo tiempo);
	
	//siempre que la cola este inicializada y no este vacia 
	void desacolar();
	
	//siempre que la cola este inicializada
	boolean colaVacia();
	
	//siempre que la cola este inicializada y no este vacia
	Tiempo primero();

}

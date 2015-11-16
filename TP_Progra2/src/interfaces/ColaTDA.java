package interfaces;

import model.Cita;

public interface ColaTDA {
	
	void inicializarCola();
	
	//siemre que la cola este inicializada
	void acolar(Cita tiempo);
	
	//siempre que la cola este inicializada y no este vacia 
	void desacolar();
	
	//siempre que la cola este inicializada
	boolean colaVacia();
	
	//siempre que la cola este inicializada y no este vacia
	Cita primero();

}

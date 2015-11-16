package impl;

import interfaces.ColaTDA;
import model.Cita;

public class ColaEstatica implements ColaTDA {
	
	Cita[] cola;
	int indice;

	public void inicializarCola() {
		cola = new Cita[100];
		indice = 0;
	}

	public void acolar(Cita cita) {
		cola[indice] = cita;
		indice++;
	}

	public void desacolar() {
		for (int i = 0; i < indice - 1; i++) {
			cola[i] = cola[i + 1];
		}
		indice--;
	}

	public boolean colaVacia() {
		return indice == 0;
	}

	public Cita primero() {
		return cola[0];
	}

}

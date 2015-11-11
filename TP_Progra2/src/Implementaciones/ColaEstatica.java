package Implementaciones;

import tp.Tiempo;
import Interfaces.ColaTDA;

public class ColaEstatica implements ColaTDA {
	
	Tiempo[] cola;
	int indice;

	public void inicializarCola() {
		cola = new Tiempo[100];
		indice = 0;
	}

	public void acolar(Tiempo tiempo) {
		cola[indice] = tiempo;
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

	public Tiempo primero() {
		return cola[0];
	}

}

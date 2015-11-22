package impl;

import interfaces.ABBTDA;
import interfaces.CalendarioTDA;
import model.Cita;
import model.Tiempo;
import Implementaciones.Vector;
import TDA.VectorTDA;

public class Calendario implements CalendarioTDA {
	
	VectorTDA<ABBTDA> dias;

	public void inicializar() throws Exception {
		dias = new Vector<ABBTDA>();
		dias.inicializarVector(5);
		for(int i = 0; i < dias.capacidadVector(); i ++) {
			dias.agregarElemento(i, new ABB());
		}
	}

	public void agregar(int dia, Tiempo inicio, Tiempo duracion) throws Exception {
		ABBTDA citas = dias.recuperarElemento(dia);
		
		Cita cita = new Cita();
		cita.setInicio(inicio);
		cita.setDuracion(duracion);
		
		citas.agregarElemento(cita);
	}

	public void eliminar(int dia) throws Exception {
		dias.recuperarElemento(dia).inicializarArbol();
	}

	public void eliminar(int dia, Tiempo inicio) throws Exception {
		ABBTDA citas = dias.recuperarElemento(dia);
		citas.eliminarElemento(inicio);
	}

	public ABBTDA recuperar(int dia) throws Exception {
		return dias.recuperarElemento(dia);
	}

}

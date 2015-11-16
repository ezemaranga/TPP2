package impl;
import interfaces.ABBTDA;
import interfaces.CalendarioTDA;
import interfaces.ColaTDA;
import model.Cita;
import model.Tiempo;
import Implementaciones.Vector;
import TDA.VectorTDA;

public class Calendario implements CalendarioTDA {
	
	VectorTDA<ABBTDA> dias;

	public void inicializar() throws Exception {
		dias = new Vector<ABBTDA>();
		dias.inicializarVector(7);
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
	
	public ColaTDA obtenerDisponibilidad (int dia) throws Exception {
		ABBTDA citas = dias.recuperarElemento(dia);
		
		ColaTDA colaCitas = generarColaCitas(citas);
		
		ColaTDA resultado = new ColaEstatica();
		resultado.inicializarCola();
		
		while (!colaCitas.colaVacia()) {
			Cita tiempo = colaCitas.primero();
			
			Cita citaDisponible = new Cita();
			
			//analizo si es primera o ultima
			//genero los bloques disponibles de citas
			
			resultado.acolar(citaDisponible);
			colaCitas.desacolar();
		}
		
		return resultado;
	}
	
	public int mayorOcupacion() throws Exception {
		
		float tiempoOcupado = 0;
		int diaOcupado = 0;
		
		for (int i = 0; i < dias.capacidadVector(); i++) {
			ABBTDA citas = dias.recuperarElemento(i);
			
			float tiempoOcupadoDia = tiempoOcupado(citas);
			if (tiempoOcupadoDia > tiempoOcupado) {
				diaOcupado = i;
				tiempoOcupado = tiempoOcupadoDia;
			}
			
		}
		
		return diaOcupado;
	}
	
	public int menorOcupacion() throws Exception {
		
		float tiempoOcupado = 100;
		int diaOcupado = 0;
		
		for (int i = 0; i < dias.capacidadVector(); i++) {
			ABBTDA citas = dias.recuperarElemento(i);
			
			float tiempoOcupadoDia = tiempoOcupado(citas);
			if (tiempoOcupadoDia < tiempoOcupado) {
				diaOcupado = i;
				tiempoOcupado = tiempoOcupadoDia;
			}
			
		}
		
		return diaOcupado;
	}
	
	/**
	 * Genera la cola de citas para un dia particular 
	 * @param citas
	 * @return
	 */
	private ColaTDA generarColaCitas(ABBTDA citas) {
		
		ColaTDA resultado = new ColaEstatica();
		resultado.inicializarCola();
		
		if (!citas.arbolVacio()) {
			
			ColaTDA colaI = generarColaCitas(citas.hijoIzq());
			
			colaI.acolar(citas.raiz());
			
			ColaTDA colaD = generarColaCitas(citas.hijoDer());
			
			while (!colaI.colaVacia()) {
				Cita cita = colaI.primero();
				resultado.acolar(cita);
				colaI.desacolar();
			}
			
			while (!colaD.colaVacia()) {
				Cita cita = colaD.primero();
				resultado.acolar(cita);
				colaD.desacolar();
			}
			
		}
		
		return resultado;

	}
	
	/**
	 * Calcula el tiempo total ocupado para un dia en particular
	 * @param citas
	 * @return
	 */
	private float tiempoOcupado (ABBTDA citas) {
		
		if (citas.arbolVacio()) {
			return 0;
		} else {
			return citas.raiz().getDuracion().getTiempoEnHoras() + 
						tiempoOcupado(citas.hijoIzq()) + 
						tiempoOcupado(citas.hijoDer());
		}
		
	}

}

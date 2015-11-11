package tp;
import Implementaciones.ColaEstatica;
import Interfaces.ABBTDA;
import Interfaces.ColaTDA;
import TDA.VectorTDA;

public class Calendario implements CalendarioTDA {
	
	VectorTDA<ABBTDA> dias;

	public void inicializar() {
		dias.inicializarVector(7);
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
		ColaTDA toReturn = new ColaEstatica();
		
		ABBTDA citas = dias.recuperarElemento(dia);
		
		return toReturn;
	}
	
	public int mayorOcupacion () throws Exception {
		
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
	 * Calcula el tiempo ocupado para un dia en particular
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

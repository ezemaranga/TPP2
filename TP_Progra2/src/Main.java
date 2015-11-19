import impl.Calendario;
import impl.ColaEstatica;
import interfaces.ABBTDA;
import interfaces.CalendarioTDA;
import interfaces.ColaTDA;
import model.Cita;
import model.Tiempo;


public class Main {
	
	public static void main(String[] args) throws Exception {
		
		CalendarioTDA calendario = new Calendario();
		calendario.inicializar();
		
		//Dia 0 (Lunes) Cita de 9:00 a 10:30
		Tiempo t1 = new Tiempo();
		t1.setHoras(9);
		t1.setMinutos(0);
		
		Tiempo d1 = new Tiempo();
		d1.setHoras(1);
		d1.setMinutos(30);
		
		calendario.agregar(0, t1, d1);
		
		//Dia 0 (Lunes) Cita de 11:00 a 11:30
		Tiempo t2 = new Tiempo();
		t2.setHoras(11);
		t2.setMinutos(0);
				
		Tiempo d2 = new Tiempo();
		d2.setHoras(0);
		d2.setMinutos(30);
				
		calendario.agregar(0, t2, d2);
				
		//Dia 0 (Lunes) Cita de 14:15 a 14:45
		Tiempo t3 = new Tiempo();
		t3.setHoras(14);
		t3.setMinutos(15);
				
		Tiempo d3 = new Tiempo();
		d3.setHoras(0);
		d3.setMinutos(30);
				
		calendario.agregar(0, t3, d3);
		
		
		
	}
	
	public int mayorOcupacion(CalendarioTDA calendario) throws Exception {
		
		float tiempoOcupado = 0;
		int diaOcupado = 0;
		
		for (int i = 0; i < 7; i++) {
			ABBTDA citas = calendario.recuperar(i);
			
			float tiempoOcupadoDia = tiempoOcupado(citas);
			if (tiempoOcupadoDia > tiempoOcupado) {
				diaOcupado = i;
				tiempoOcupado = tiempoOcupadoDia;
			}
			
		}
		
		return diaOcupado;
	}
	
	public int menorOcupacion(CalendarioTDA calendario) throws Exception {
		
		float tiempoOcupado = 100;
		int diaOcupado = 0;
		
		for (int i = 0; i < 7; i++) {
			ABBTDA citas = calendario.recuperar(i);
			
			float tiempoOcupadoDia = tiempoOcupado(citas);
			if (tiempoOcupadoDia < tiempoOcupado) {
				diaOcupado = i;
				tiempoOcupado = tiempoOcupadoDia;
			}
			
		}
		
		return diaOcupado;
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
	
	public ColaTDA obtenerDisponibilidad (CalendarioTDA calendario, int dia) throws Exception {
		ABBTDA citas = calendario.recuperar(dia);
		
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

}

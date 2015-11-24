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
		
		cargarDiaCero(calendario);
		cargarDiaUno(calendario);
		cargarDiaDos(calendario);
		cargarDiaTres(calendario);
		cargarDiaCuatro(calendario);
		
		int diaMasOcupado = mayorOcupacion(calendario);
		System.out.println("*********************************");
		System.out.println("Dia mas ocupado: " + diaMasOcupado);
		System.out.println("*********************************");
		
		int diaMenosOcupado = menorOcupacion(calendario);
		System.out.println("*********************************");
		System.out.println("Dia menos ocupado: " + diaMenosOcupado);
		System.out.println("*********************************");
		
		ColaTDA colaDisponiblesDiaCero = obtenerDisponibilidad(calendario, 0);
		System.out.println("*******Disponibilidad dia cero***************");
		imprimirCola(colaDisponiblesDiaCero);
		
		ColaTDA colaDisponiblesDiaUno = obtenerDisponibilidad(calendario, 1);
		System.out.println("*******Disponibilidad dia uno***************");
		imprimirCola(colaDisponiblesDiaUno);
		
		ColaTDA colaDisponiblesDiaDos = obtenerDisponibilidad(calendario, 2);
		System.out.println("*******Disponibilidad dia dos***************");
		imprimirCola(colaDisponiblesDiaDos);
		
		ColaTDA colaDisponiblesDiaTres = obtenerDisponibilidad(calendario, 3);
		System.out.println("*******Disponibilidad dia tres***************");
		imprimirCola(colaDisponiblesDiaTres);
		
		ColaTDA colaDisponiblesDiaCuatro = obtenerDisponibilidad(calendario, 4);
		System.out.println("*******Disponibilidad dia cuatro***************");
		imprimirCola(colaDisponiblesDiaCuatro);
		
		
		
		//Borro la unica cita que existia para el dia cuatro...
		Tiempo t1 = new Tiempo();
		t1.setHoras(15);
		t1.setMinutos(0);
		calendario.eliminar(4, t1);
		
		//Deberia cambiar el dia menos ocupado al dia numero 4 (quedo sin citas)
		diaMenosOcupado = menorOcupacion(calendario);
		System.out.println("*********************************");
		System.out.println("Dia menos ocupado: " + diaMenosOcupado);
		System.out.println("*********************************");
			
	}
	
	public static int mayorOcupacion(CalendarioTDA calendario) throws Exception {
		
		float tiempoOcupado = 0;
		int diaOcupado = 0;
		
		for (int i = 0; i < 5; i++) {
			ABBTDA citas = calendario.recuperar(i);
			
			float tiempoOcupadoDia = tiempoOcupado(citas);
			if (tiempoOcupadoDia > tiempoOcupado) {
				diaOcupado = i;
				tiempoOcupado = tiempoOcupadoDia;
			}
			
		}
		
		return diaOcupado;
	}
	
	public static int menorOcupacion(CalendarioTDA calendario) throws Exception {
		
		float tiempoOcupado = 100;
		int diaOcupado = 0;
		
		for (int i = 0; i < 5; i++) {
			ABBTDA citas = calendario.recuperar(i);
			
			float tiempoOcupadoDia = tiempoOcupado(citas);
			//System.out.println("Tiempo ocupado del dia " + i + " es " + tiempoOcupadoDia);
			if (tiempoOcupadoDia < tiempoOcupado) {
				diaOcupado = i;
				tiempoOcupado = tiempoOcupadoDia;
			}
			
		}
		
		return diaOcupado;
	}
	
	public static ColaTDA obtenerDisponibilidad(CalendarioTDA calendario, int dia) throws Exception {
		ABBTDA citas = calendario.recuperar(dia);
		
		ColaTDA colaCitas = generarColaCitas(citas);
		
		ColaTDA resultado = new ColaEstatica();
		resultado.inicializarCola();
		
		Cita previa = null;
		
		while (!colaCitas.colaVacia()) {
			Cita cita = colaCitas.primero();
			
			if (previa == null) {
				
				if (!cita.esAPrimeraHora()) {
					
					Cita citaDisponible = new Cita();
					
					Tiempo inicio = new Tiempo();
					inicio.setHoras(9);
					inicio.setMinutos(0);
					
					citaDisponible.setInicio(inicio);
					
					Tiempo duracion = new Tiempo();
					duracion.setHoras(cita.getInicio().getHoras() - 9);
					duracion.setMinutos(cita.getInicio().getMinutos());
					
					citaDisponible.setDuracion(duracion);
					
					resultado.acolar(citaDisponible);
					
				} 
				
			} else {
				
				Tiempo inicio = previa.horaFinalizacion();
				
				if (inicio.getHoras() != cita.getInicio().getHoras() || inicio.getMinutos() != cita.getInicio().getMinutos()) {
					
					Cita citaDisponible = new Cita();
					citaDisponible.setInicio(inicio);
					
					Tiempo duracion = new Tiempo();
					duracion.setHoras(cita.getInicio().getHoras() - inicio.getHoras());
					duracion.setMinutos(cita.getInicio().getMinutos() - inicio.getMinutos());
					
					citaDisponible.setDuracion(duracion);
					
					resultado.acolar(citaDisponible);
					
				}
				
			}
			
			previa = cita;
			colaCitas.desacolar();
		}
		
		if (!previa.esAUltimaHora()) {
			
			Cita citaDisponible = new Cita();
			
			Tiempo inicio = previa.horaFinalizacion();				
			citaDisponible.setInicio(inicio);
			
			Tiempo duracion = new Tiempo();
			duracion.setHoras(18 - inicio.getHoras());
			duracion.setMinutos(0 - inicio.getMinutos());
			
			citaDisponible.setDuracion(duracion);
			
			resultado.acolar(citaDisponible);
			
		}
		
		return resultado;
	}
	
	private static void imprimirCola(ColaTDA cola) {
		while (!cola.colaVacia()) {
			Cita cita = cola.primero();
			System.out.println("Disponible de: " + cita.getInicio().getHoras() + ":" + cita.getInicio().getMinutos() + 
								" a " + cita.horaFinalizacion().getHoras() + ":" + cita.horaFinalizacion().getMinutos());
			cola.desacolar();
		}
	}
	
	/**
	 * Calcula el tiempo total ocupado para un dia en particular
	 * @param citas
	 * @return
	 */
	private static float tiempoOcupado (ABBTDA citas) {
		
		if (citas.arbolVacio()) {
			return 0;
		} else {
			return citas.raiz().getDuracion().getTiempoEnHoras() + 
						tiempoOcupado(citas.hijoIzq()) + 
						tiempoOcupado(citas.hijoDer());
		}
		
	}
	
	/**
	 * Genera una cola de citas para un dia particular 
	 * @param citas
	 * @return
	 */
	private static ColaTDA generarColaCitas(ABBTDA citas) {
		
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
	 * El dia cero tiene ocupadas dos horas y media
	 * @param calendario
	 * @throws Exception
	 */
	private static void cargarDiaCero(CalendarioTDA calendario) throws Exception {
		
		Tiempo t1 = new Tiempo();
		t1.setHoras(9);
		t1.setMinutos(0);
				
		Tiempo d1 = new Tiempo();
		d1.setHoras(1);
		d1.setMinutos(30);
				
		calendario.agregar(0, t1, d1);
				
		Tiempo t2 = new Tiempo();
		t2.setHoras(11);
		t2.setMinutos(0);
						
		Tiempo d2 = new Tiempo();
		d2.setHoras(0);
		d2.setMinutos(30);
						
		calendario.agregar(0, t2, d2);
						
		Tiempo t3 = new Tiempo();
		t3.setHoras(14);
		t3.setMinutos(15);
						
		Tiempo d3 = new Tiempo();
		d3.setHoras(0);
		d3.setMinutos(30);
						
		calendario.agregar(0, t3, d3);
	}
	
	/**
	 * El dia uno tiene ocupadas cinco horas
	 * @param calendario
	 * @throws Exception
	 */
	private static void cargarDiaUno(CalendarioTDA calendario) throws Exception {
		Tiempo t1 = new Tiempo();
		t1.setHoras(9);
		t1.setMinutos(45);
				
		Tiempo d1 = new Tiempo();
		d1.setHoras(1);
		d1.setMinutos(15);
				
		calendario.agregar(1, t1, d1);
				
		Tiempo t2 = new Tiempo();
		t2.setHoras(11);
		t2.setMinutos(0);
						
		Tiempo d2 = new Tiempo();
		d2.setHoras(3);
		d2.setMinutos(0);
						
		calendario.agregar(1, t2, d2);
						
		Tiempo t3 = new Tiempo();
		t3.setHoras(16);
		t3.setMinutos(15);
						
		Tiempo d3 = new Tiempo();
		d3.setHoras(0);
		d3.setMinutos(45);
						
		calendario.agregar(1, t3, d3);
	}
	
	/**
	 * El dia dos tiene ocupadas 45 minutos
	 * @param calendario
	 * @throws Exception
	 */
	private static void cargarDiaDos(CalendarioTDA calendario) throws Exception {
		
		Tiempo t1 = new Tiempo();
		t1.setHoras(16);
		t1.setMinutos(15);
				
		Tiempo d1 = new Tiempo();
		d1.setHoras(0);
		d1.setMinutos(45);
				
		calendario.agregar(2, t1, d1);
	
	}
	
	/**
	 * El dia tres tiene ocupada una hora 45.
	 * @param calendario
	 * @throws Exception
	 */
	private static void cargarDiaTres(CalendarioTDA calendario) throws Exception {
		
		Tiempo t1 = new Tiempo();
		t1.setHoras(11);
		t1.setMinutos(15);
				
		Tiempo d1 = new Tiempo();
		d1.setHoras(1);
		d1.setMinutos(15);
				
		calendario.agregar(3, t1, d1);
				
		Tiempo t2 = new Tiempo();
		t2.setHoras(12);
		t2.setMinutos(45);
						
		Tiempo d2 = new Tiempo();
		d2.setHoras(0);
		d2.setMinutos(30);
						
		calendario.agregar(3, t2, d2);
	
	}
	
	/**
	 * El dia cuatro tiene ocupadas tres horas
	 * @param calendario
	 * @throws Exception
	 */
	private static void cargarDiaCuatro(CalendarioTDA calendario) throws Exception {
		
		Tiempo t1 = new Tiempo();
		t1.setHoras(15);
		t1.setMinutos(0);
				
		Tiempo d1 = new Tiempo();
		d1.setHoras(3);
		d1.setMinutos(0);
				
		calendario.agregar(4, t1, d1);
		
	}

}

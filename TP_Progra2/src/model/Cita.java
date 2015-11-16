package model;

public class Cita {
	
	private Tiempo inicio;
	private Tiempo duracion;
	
	public Tiempo getInicio() {
		return inicio;
	}
	
	public void setInicio(Tiempo inicio) {
		this.inicio = inicio;
	}
	
	public Tiempo getDuracion() {
		return duracion;
	}
	
	public void setDuracion(Tiempo duracion) {
		this.duracion = duracion;
	}
	
	public boolean esAPrimeraHora() {
		return inicio.getHoras() == 9 && inicio.getMinutos() == 0; 
	}
	
	public boolean esAUltimaHora() {
		return horaFinalizacion().getTiempoEnHoras() > 18;
	}
	
	private Tiempo horaFinalizacion() {
		Tiempo tiempoFin = new Tiempo();
		
		int horaFin = inicio.getHoras() + duracion.getHoras();
		int minutosFin = inicio.getMinutos() + duracion.getMinutos();
		
		if (minutosFin >= 60) {
			horaFin++;
			minutosFin -= 60;
		}
		
		tiempoFin.setHoras(horaFin);
		tiempoFin.setMinutos(minutosFin);
		
		return tiempoFin;
	}

}

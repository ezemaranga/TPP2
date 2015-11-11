package tp;
public class Tiempo {
	
	private int horas;
	private int minutos;
	
	public void setHoras(int horas) {
		this.horas = horas;
	}
	
	public int getHoras() {
		return horas;
	}
	
	public int getMinutos() {
		return minutos;
	}
	
	public void setMinutos(int minutos) {
		this.minutos = minutos;
	}
	
	public float getTiempoEnHoras() {
		return horas + minutos / 60;
	}

}

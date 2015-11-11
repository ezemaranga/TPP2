package tp;
public class Tiempo {
	
	int horas;
	int minutos;
	
	public float getTiempoEnHoras() {
		return horas + minutos / 60;
	}

}

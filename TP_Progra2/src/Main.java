import impl.Calendario;
import interfaces.CalendarioTDA;
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

}

package tp;
import Interfaces.ABBTDA;


public interface CalendarioTDA {
	
	void inicializar();
	
	void agregar(int dia, Tiempo inicio, Tiempo duracion) throws Exception;
	
	void eliminar(int dia) throws Exception;
	
	void eliminar(int dia, Tiempo inicio) throws Exception;
	
	ABBTDA recuperar(int dia) throws Exception;

}

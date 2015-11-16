package interfaces;
import model.Tiempo;

public interface CalendarioTDA {
	
	void inicializar() throws Exception;
	
	void agregar(int dia, Tiempo inicio, Tiempo duracion) throws Exception;
	
	void eliminar(int dia) throws Exception;
	
	void eliminar(int dia, Tiempo inicio) throws Exception;
	
	ABBTDA recuperar(int dia) throws Exception;

	int mayorOcupacion() throws Exception;
	
	int menorOcupacion() throws Exception;
	
}

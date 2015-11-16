package interfaces;
import model.Cita;
import model.Tiempo;

public interface ABBTDA {
	
	Cita raiz();
	
	ABBTDA hijoIzq();
	
	ABBTDA hijoDer();
	
	boolean arbolVacio() ;
	
	void inicializarArbol();
	
	void agregarElemento(Cita cita);
	
	void eliminarElemento(Tiempo inicio);
	
}

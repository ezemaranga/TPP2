
public interface CalendarioTDA {
	
	void inicializar();
	
	void agregar(String dia, Tiempo inicio, Tiempo duracion);
	
	void eliminar(String dia);
	
	void eliminar(String dia, Tiempo inicio);
	
	ABBTDA recuperar(String dia);

}

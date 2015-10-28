public interface ABBTDA {
	
	int raiz();
	
	ABBTDA hijoIzq();
	
	ABBTDA hijoDer();
	
	boolean arbolVacio() ;
	
	void inicializarArbol();
	
	void agregarElemento(int x);
	
	void eliminarElemento(int x);
	
}

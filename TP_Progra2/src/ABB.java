
public class ABB implements ABBTDA {
	
	class NodoABB {
		Cita info;
		ABBTDA hijoIzq;
		ABBTDA hijoDer;
	}
	
	NodoABB raiz;

	public Cita raiz() {
		return raiz.info;
	}

	public ABBTDA hijoIzq() {
		return raiz.hijoIzq;
	}

	public ABBTDA hijoDer() {
		return raiz.hijoDer;
	}

	public boolean arbolVacio() {
		return raiz == null;
	}

	public void inicializarArbol() {
		raiz = null;
	}

	public void agregarElemento(Cita cita) {
		// TODO Auto-generated method stub
		
	}

	public void eliminarElemento(Tiempo inicio) {
		// TODO Auto-generated method stub
		
	}

}

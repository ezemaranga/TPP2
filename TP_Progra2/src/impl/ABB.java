package impl;
import interfaces.ABBTDA;
import model.Cita;
import model.Tiempo;

public class ABB implements ABBTDA {
	
	class NodoABB {
		Cita info;
		ABBTDA hijoIzq;
		ABBTDA hijoDer;
	}
	
	private NodoABB raiz;

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
		
		if (raiz == null) {
			raiz = new NodoABB();
			raiz.info = cita;
			raiz.hijoIzq = new ABB();
			raiz.hijoIzq.inicializarArbol();
			raiz.hijoDer = new ABB();
			raiz.hijoDer.inicializarArbol();
		} else {
			Tiempo inicioRaiz = raiz.info.getInicio();
			if (inicioRaiz.getTiempoEnHoras() > cita.getInicio().getTiempoEnHoras()) {
				raiz.hijoIzq.agregarElemento(cita);
			} else if (inicioRaiz.getTiempoEnHoras() < cita.getInicio().getTiempoEnHoras()) { 
				raiz.hijoDer.agregarElemento(cita);
			}
		}
		
	}

	public void eliminarElemento(Tiempo inicio) {
		
		if (raiz != null) {
			
			if (raiz.info.getInicio().getTiempoEnHoras() == inicio.getTiempoEnHoras() && raiz.hijoIzq.arbolVacio() && raiz.hijoDer.arbolVacio()) {
				raiz = null;
			} else if (raiz.info.getInicio().getTiempoEnHoras() == inicio.getTiempoEnHoras() && !raiz.hijoIzq.arbolVacio()) {
				raiz.info = this.mayor(raiz.hijoIzq);
				raiz.hijoIzq.eliminarElemento(raiz.info.getInicio());
			} else if (raiz.info.getInicio().getTiempoEnHoras() == inicio.getTiempoEnHoras() && raiz.hijoIzq.arbolVacio()) {
				raiz.info = this.menor(raiz.hijoDer);
				raiz.hijoDer.eliminarElemento(raiz.info.getInicio());
			} else if (raiz.info.getInicio().getTiempoEnHoras() < inicio.getTiempoEnHoras()) {
				raiz.hijoDer.eliminarElemento(inicio);
			} else {
				raiz.hijoIzq.eliminarElemento(inicio);
			}
			
		}
		
	}
	
	private Cita mayor(ABBTDA a) {
		if (a.hijoDer().arbolVacio()) {
			return a.raiz();
		} else {
			return mayor(a.hijoDer());
		}
	}
	
	private Cita menor(ABBTDA a) {
		if (a.hijoIzq().arbolVacio()) {
			return a.raiz();
		} else {
			return menor(a.hijoIzq());
		}
	}

}

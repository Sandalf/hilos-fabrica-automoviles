package tortuga_liebre;

public class Puente {
	private int posicion;
	private int ancho;
	private boolean disponible = true;
	
	public Puente(int posicion, int ancho) {
		this.posicion = posicion;
		this.ancho = ancho;
	}

	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public boolean estaDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
	
	
}

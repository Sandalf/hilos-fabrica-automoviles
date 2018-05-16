package tortuga_liebre;

import java.awt.image.BufferedImage;

public class Corredor extends Thread {
	
	private int corredorID;
	private BufferedImage imagenCorredor;
	private boolean corriendo = true;
	private int distanciaRecorrida = 50;
	private Semaforo[] semaforos;
	private Puente[] puentes;

	public Corredor(int corredorID, Semaforo[] semaforos, Puente[] puentes) {
		this.corredorID = corredorID;
		this.semaforos = semaforos;
		this.puentes = puentes;
	}

	public int getCorredorID() {
		return corredorID;
	}

	public void setCorredorID(int corredorID) {
		this.corredorID = corredorID;
	}

	public Semaforo[] getSemaforos() {
		return semaforos;
	}

	public void setSemaforos(Semaforo[] semaforos) {
		this.semaforos = semaforos;
	}

	public Puente[] getPuentes() {
		return puentes;
	}

	public void setPuentes(Puente[] puentes) {
		this.puentes = puentes;
	}

	public BufferedImage getImagenCorredor() {
		return imagenCorredor;
	}

	public void setImagenCorredor(BufferedImage imagenCorredor) {
		this.imagenCorredor = imagenCorredor;
	}

	public int getDistanciaRecorrida() {
		return distanciaRecorrida;
	}

	public void setDistanciaRecorrida(int distanciaRecorrida) {
		this.distanciaRecorrida = distanciaRecorrida;
	}
	
	public boolean estaCorriendo() {
		return corriendo;
	}

	public void setCorriendo(boolean corriendo) {
		this.corriendo = corriendo;
	}

	public int enPuente(int posicion) {
		for(int i = 0; i < puentes.length; i++) {
			if (posicion >= puentes[i].getPosicion() && 
				posicion <= (puentes[i].getPosicion()+10)) {
				return i;
			}
		}
		return -1;	
	}
	
}

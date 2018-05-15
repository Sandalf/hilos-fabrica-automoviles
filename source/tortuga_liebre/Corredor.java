package tortuga_liebre;

import java.awt.image.BufferedImage;

public class Corredor extends Thread {
	
	private int corredorID;
	private BufferedImage imagenCorredor;
	private boolean corriendo = true;
	private int distanciaRecorrida = 0;
	private Semaforo[] semaforos;
	private boolean[] puentes;

	public Corredor(int corredorID, Semaforo[] semaforos, boolean[] puentes) {
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

	public boolean[] getPuentes() {
		return puentes;
	}

	public void setPuentes(boolean[] puentes) {
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

	public boolean enPuente(int posicion) {
		if(posicion >= 30 && posicion <= 40) {
			return true;
		}
		return false;	
	}
	
}

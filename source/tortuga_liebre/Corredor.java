package tortuga_liebre;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Corredor extends Thread {
	
	private int corredorID;
	private BufferedImage imagenCorredor;
	private boolean corriendo = true;
	private int distanciaRecorrida = 50;
	private Semaforo[] semaforos;
	private Puente[] puentes;
	private int minPasos;
	private int maxPasos;
	public Rutinas rutinas = new Rutinas();
	public static boolean ingresandoGanador = false;
	private ArrayList<Corredor> ganadores;
	private Semaforo semGanadores;

	public Corredor(int corredorID, Semaforo[] semaforos, Puente[] puentes) {
		this.corredorID = corredorID;
		this.semaforos = semaforos;
		this.puentes = puentes;
//		this.ganadores = ganadores;
//		this.semGanadores = semGanadores;
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
	
	public void run() {
		try {
			while(estaCorriendo()) {
				int pasos = rutinas.nextInt(getMinPasos(),getMaxPasos());

				int numPuente = enPuente(getDistanciaRecorrida());
				if(numPuente > -1) {
					getSemaforos()[numPuente].espera();
					Puente puente = getPuentes()[numPuente];
					if(getPuentes()[numPuente].estaDisponible()) {					
						atravesarPuente(numPuente,
								puente.getPosicion(),
								puente.getPosicion()+puente.getAncho());
					}
					getSemaforos()[numPuente].libera();
				} else {
					setDistanciaRecorrida(getDistanciaRecorrida()+pasos);
				}			
				sleep(100);			
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void atravesarPuente(int numPuente, int inicioPuente, int finPuente) {
		System.out.println("Inicio: " + inicioPuente + ", Fin: " + finPuente);
		try {
			getPuentes()[numPuente].setDisponible(false);
			while(getDistanciaRecorrida() >= inicioPuente && 
				getDistanciaRecorrida() <= (finPuente+this.getImagenCorredor().getWidth())) {
				int pasos = rutinas.nextInt(getMinPasos(),getMaxPasos());
				setDistanciaRecorrida(getDistanciaRecorrida()+pasos);
				sleep(100);
			}
			getPuentes()[numPuente].setDisponible(true);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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

	public int getMinPasos() {
		return minPasos;
	}

	public void setMinPasos(int minPasos) {
		this.minPasos = minPasos;
	}

	public int getMaxPasos() {
		return maxPasos;
	}

	public void setMaxPasos(int maxPasos) {
		this.maxPasos = maxPasos;
	}
	
}

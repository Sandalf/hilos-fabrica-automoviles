package tortuga_liebre;

import java.awt.image.BufferedImage;

public class Liebre extends Corredor {
	
	private BufferedImage imagenCorredor;
	private Rutinas rutinas = new Rutinas();
	private boolean corriendo = true;
	private int distanciaRecorrida = 0;
	private Semaforo[] semaforos;
	private boolean[] puentes;

	public Liebre(int corredorID, Semaforo[] semaforos, boolean[] puentes) {
		super(corredorID);	
		this.imagenCorredor = rutinas.obtenerImagen("./liebre1.png");
		this.semaforos = semaforos;
		this.puentes = puentes;
	}
	
	public void run() {
		try {
			while(corriendo) {
				int pasos = rutinas.nextInt(3,10);
							
				if(distanciaRecorrida >= 30 && distanciaRecorrida <= 90) {
					semaforos[0].espera();
					if(!puentes[0]) {
						puentes[0] = true;
						while(distanciaRecorrida >= 30 && distanciaRecorrida <= 200) {
							pasos = rutinas.nextInt(3,10);
							distanciaRecorrida += pasos;
							sleep(100);	
						}
						puentes[0] = false;
					}
					semaforos[0].libera();
				} else {
					distanciaRecorrida += pasos;
				}
				
				if (distanciaRecorrida >= 500) {
					corriendo = false;
				}				
				sleep(100);			
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public int getDistanciaRecorrida() {
		return distanciaRecorrida;
	}

	public void setDistanciaRecorrida(int distanciaRecorrida) {
		this.distanciaRecorrida = distanciaRecorrida;
	}

	public BufferedImage getImagenCorredor() {
		return imagenCorredor;
	}

	public void setImagenCorredor(BufferedImage imagenCorredor) {
		this.imagenCorredor = imagenCorredor;
	}
	
}

package fabrica_2;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import fabrica_1.Rutinas;

public class Estacion4 extends Estacion {

	public Estacion4(Graphics g, int linea, int estacion, Semaforo semaforo, Semaforo semNumCarros, int limiteCarros,
			int carrosFabricados, Estacion siguienteEstacion) {
		super(g, linea, estacion, semaforo, semNumCarros, limiteCarros, carrosFabricados, siguienteEstacion);
		this.imagenCarro = rutinas.obtenerImagen("./chMoTrans.png");
	}

	public void run() {
		try {
			while(!limiteFabricacionAlcanzada()) {
				semNumCarros.libera();
				if(activo) {				
					semaforo.espera();
					pintarEstacionCarro(3,linea);
					sleep(1000);	
					pintarEstacionVacia(3,linea);
					semaforo.libera();
					setActivo(false);
					siguienteEstacion.setActivo(true);
				}			
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
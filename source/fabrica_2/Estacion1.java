package fabrica_2;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import fabrica_1.Rutinas;

public class Estacion1 extends Estacion {

	public Estacion1(Graphics g, int linea, int estacion, Semaforo semaforo, Semaforo semNumCarros, int limiteCarros,
			int carrosFabricados, Estacion siguienteEstacion) {
		super(g, linea, estacion, semaforo, semNumCarros, limiteCarros, carrosFabricados, siguienteEstacion);
	}

	public void run() {
		try {
			while(!limiteFabricacionAlcanzada()) {
				semNumCarros.libera();
				semaforo.espera();
				pintarEstacionCarro(0,linea);
				sleep(1000);		
				pintarEstacionVacia(0,linea);
				semaforo.libera();
				siguienteEstacion.setActivo(true);
				
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

package fabrica_2;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import fabrica_1.Rutinas;

public class Estacion5 extends Estacion {

	public Estacion5(Graphics g, int linea, int estacion, Semaforo semaforo, Semaforo semNumCarros, int limiteCarros,
			int carrosFabricados, Estacion siguienteEstacion) {
		super(g, linea, estacion, semaforo, semNumCarros, limiteCarros, carrosFabricados, siguienteEstacion);
		this.imagenCarro = rutinas.obtenerImagen("./completo.png");
	}

	public void run() {
		try {
			while(!limiteFabricacionAlcanzada()) {
				semNumCarros.libera();
				if(activo) {				
					semaforo.espera();
					pintarEstacionCarro(4,linea);
					sleep(1000);		
					pintarEstacionVacia(4,linea);
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
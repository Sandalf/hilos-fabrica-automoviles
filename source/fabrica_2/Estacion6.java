package fabrica_2;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import fabrica_1.Rutinas;

public class Estacion6 extends Estacion {

	public Estacion6(Graphics g, int linea, int estacion, Semaforo semaforo, Semaforo semNumCarros, int limiteCarros,
			int carrosFabricados, Estacion siguienteEstacion) {
		super(g, linea, estacion, semaforo, semNumCarros, limiteCarros, carrosFabricados, siguienteEstacion);
		this.imagenCarro = rutinas.obtenerImagen("./carrocompleto.png");
	}

	public void run() {
		try {
			while(!limiteFabricacionAlcanzada()) {
				semNumCarros.libera();
				if(activo) {				
					semaforo.espera();
					pintarEstacionCarro(5,linea);
					sleep(1000);		
					pintarEstacionVacia(5,linea);
					semaforo.libera();
					setActivo(false);
					incrementarNumCarros();
				}			
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void incrementarNumCarros() {
		semNumCarros.espera();
		carrosFabricados++;
		ultimoCarroFabricado = carrosFabricados;
		System.out.println("Carros Fabricados: " + carrosFabricados);
		semNumCarros.libera();
	}
	
}

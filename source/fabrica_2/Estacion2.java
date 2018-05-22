package fabrica_2;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import fabrica_1.Rutinas;

public class Estacion2 extends Estacion {

	private BufferedImage imagenRobotTrans;

	static Semaforo semaMotor;
	static Semaforo semTransmision = new Semaforo(2);
	
	public Estacion2(Graphics g, int linea, int estacion, Semaforo semaforo, Semaforo semNumCarros, int limiteCarros,
			int carrosFabricados, Estacion siguienteEstacion) {
		super(g, linea, estacion, semaforo, semNumCarros, limiteCarros, carrosFabricados, siguienteEstacion);
		this.imagenCarro = rutinas.obtenerImagen("./chMotor.png");
		this.imagenRobotTrans = rutinas.obtenerImagen("./robot-trans.png");
		this.semaMotor = semaforo;
	}

	public void run() {
		try {
			while(!limiteFabricacionAlcanzada()) {
				semNumCarros.libera();
				if(activo) {				
					semaMotor.espera();
					pintarEstacionCarro(1,linea);		
					sleep(1000);
					semTransmision.espera();
					semaMotor.libera();		
					pintarEstacionCarroTransmision(1,linea);									
					sleep(1000);	
					pintarEstacionVacia(1,linea);
					semTransmision.libera();
					setActivo(false);
					siguienteEstacion.setActivo(true);
				}			
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void pintarEstacionCarroTransmision(int estacion, int fila) {
		graphics.drawImage(imagenDefault, estacion*80, (fila*80)+50, null); 
		graphics.drawImage(imagenRobotTrans, estacion*80, (fila*80)+50, null);
		graphics.drawImage(imagenCarro, estacion*80, (fila*80)+50, null);
	}

}

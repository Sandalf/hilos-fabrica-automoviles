package fabrica_2;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import fabrica_1.Rutinas;

public class Estacion extends Thread {
	
	int linea;
	int estacion;
	boolean activo;
	Estacion siguienteEstacion;
	Graphics graphics;
	BufferedImage imagenRobot;
	BufferedImage imagenDefault;
	BufferedImage imagenCarro;
	Rutinas rutinas = new Rutinas();

	static Semaforo semaforo;
	static Semaforo semNumCarros;
	static int limiteCarros;
	static int carrosFabricados;
	
	int ultimoCarroFabricado = 0;

	public Estacion(Graphics g, int linea, int estacion, Semaforo semaforo, Semaforo semNumCarros, int limiteCarros,int carrosFabricados, Estacion siguienteEstacion) {
		this.graphics = g;
		this.linea = linea;
		this.estacion = estacion;
		this.siguienteEstacion = siguienteEstacion;
		this.imagenCarro = rutinas.obtenerImagen("./chasis.png");
		this.imagenRobot = rutinas.obtenerImagen("./robot.png");
		this.imagenDefault = rutinas.obtenerImagen("./cinta.png");
		this.semaforo = semaforo;
		this.semNumCarros = semNumCarros;
		this.limiteCarros = limiteCarros;
		this.carrosFabricados = carrosFabricados;
		pintarEstacion(estacion,linea);
	}

	public boolean limiteFabricacionAlcanzada() {
		semNumCarros.espera();
		return carrosFabricados >= limiteCarros ? true : false;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public void pintarEstacionCarro(int estacion, int fila) {
		graphics.drawImage(imagenDefault, estacion*80, (fila*80)+50, null); 
		graphics.drawImage(imagenRobot, estacion*80, (fila*80)+50, null);
		graphics.drawImage(imagenCarro, estacion*80, (fila*80)+50, null);
	}

	public void pintarEstacionVacia(int estacion, int fila) {
		graphics.drawImage(imagenDefault, estacion*80, (fila*80)+50, null);
	}

	public void pintarEstacionRobot(int estacion, int fila) {
		graphics.drawImage(imagenDefault, estacion*80, (fila*80)+50, null);
		graphics.drawImage(imagenRobot, estacion*80, (fila*80)+50, null);
	}

	public void pintarEstacion(int estacion, int fila) {	
		graphics.drawImage(imagenDefault, estacion*80, (fila*80)+50, null);
	}
	
	public String getNoCarros() {
		return ultimoCarroFabricado+"";
	}

}

package fabrica_1;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;

public class Fila extends Thread {

	private int filaID;
	private BufferedImage imagenRobot;
	private BufferedImage imagenDefault;
	private BufferedImage imagenRobotTrans;
	private BufferedImage[] etapasCarro;
	private String[] nomImg = { "chasis.png", "chMotor.png", "chTransmision.png", "chMoTrans.png", "completo.png",
	"carrocompleto.png" };
	private Graphics graphics;	
	private boolean estaFabricando = true;
	private int[] segundosPorEstacion;
	private Rutinas rutinas = new Rutinas();
	private int numCarroFabricando = 0;

	public static int noCarros = 0;
	public static int[][] robots;
	public static Semaforo[] semaforos;
	public static int estacionVacia = 0;
	public static int robotLibre = 1;
	public static int robotOcupado = 2;
	public static int robotTransmisionLibre = 3;
	public static int robotTransmisionOcupado = 4;
	public static Semaforo semNumCarros = new Semaforo(1);

	public Fila(int id, Graphics g, int[][] robots, Semaforo[] semaforos, int[] segundosPorEstacion) {
		this.graphics = g;
		this.filaID = id;
		this.robots = robots;
		this.semaforos = semaforos;
		this.imagenRobot = rutinas.obtenerImagen("./robot.png");
		this.imagenDefault = rutinas.obtenerImagen("./cinta.png");
		this.imagenRobotTrans = rutinas.obtenerImagen("./robot-trans.png");
		this.etapasCarro = inicializarEtapasCarro();
		this.segundosPorEstacion = segundosPorEstacion; 
		pintarFila();
	}

	public static Semaforo sem = new Semaforo(1);
	public static Semaforo sem2 = new Semaforo(2);
	public static Semaforo wh = new Semaforo(1);

	public BufferedImage[] inicializarEtapasCarro() {
		BufferedImage[] etapas = new BufferedImage[nomImg.length];	
		for (int i = 0; i < etapas.length; i++) {
			etapas[i] = rutinas.obtenerImagen("./"+nomImg[i]);
		}	
		return etapas;
	}

	public void run() {
		try {
			while(!limiteFabricacionAlcanzada()){
				semNumCarros.libera();

				estacion1(0,filaID);				
				estacion2(1,filaID);				
				estacion3(2,filaID);
				estacion4(3,filaID);
				estacion5(4,filaID);
				estacion6(5,filaID);				

				semNumCarros.espera();
				noCarros++;
				numCarroFabricando=noCarros;
				semNumCarros.libera();
		
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void pintarNoCarros(JLabel [] etiquetas){
		for(int i = 0 ; i < etiquetas.length ; i++){
			etiquetas[i].setBounds(620, i*90, 100, 100);
			etiquetas[i].update(etiquetas[i].getGraphics());
		}
	}

	public void pintarFila() {
		for(int i = 0; i < robots.length; i++) {
			for(int j = 0 ; j < robots[0].length; j++){		
				graphics.drawImage(imagenDefault, i*80, (j*80)+50, null);
			}
		}
	}


	public String getNoCarro(){
		return numCarroFabricando+"";
	}

	public void pintarEstacionCarro(int estacion, int fila) {
		graphics.drawImage(imagenDefault, estacion*80, (fila*80)+50, null); 
		graphics.drawImage(imagenRobot, estacion*80, (fila*80)+50, null);
		graphics.drawImage(etapasCarro[estacion], estacion*80, (fila*80)+50, null);
	}

	public void pintarEstacionVacia(int estacion, int fila) {
		graphics.drawImage(imagenDefault, estacion*80, (fila*80)+50, null);
	}

	public void pintarEstacionRobot(int estacion, int fila) {
		graphics.drawImage(imagenDefault, estacion*80, (fila*80)+50, null);
		graphics.drawImage(imagenRobot, estacion*80, (fila*80)+50, null);
	}

	public void pintarEstacionRobotTransmision(int estacion, int fila) {
		graphics.drawImage(imagenDefault, estacion*80, (fila*80)+50, null);
		graphics.drawImage(imagenRobotTrans, estacion*80, (fila*80)+50, null);
	}

	public void pintarEstacionCarroTransmision(int estacion, int fila) {
		graphics.drawImage(imagenDefault, estacion*80, (fila*80)+50, null); 
		graphics.drawImage(imagenRobotTrans, estacion*80, (fila*80)+50, null);
		graphics.drawImage(etapasCarro[estacion], estacion*80, (fila*80)+50, null);
	}

	public void estacion1(int estacion , int fila) throws InterruptedException{//creo que se puede quitar la fila
		semaforos[0].espera();
		pintarEstacionCarro(estacion,fila);
		sleep(segundosPorEstacion[estacion]*1000);
		pintarEstacionVacia(estacion,fila);
		semaforos[0].libera();
	}

	public void estacion2(int estacion , int fila) throws InterruptedException {
		semaforos[1].espera();
		pintarEstacionCarro(estacion,fila);
		sleep(segundosPorEstacion[estacion]*1000);
		pintarEstacionVacia(estacion,fila);
		semaforos[2].espera();
		semaforos[1].libera();
		pintarEstacionCarroTransmision(estacion,fila);
		sleep(segundosPorEstacion[estacion]*1000);
		pintarEstacionVacia(estacion,fila);	
	}

	public void estacion3(int estacion, int fila) throws InterruptedException {
		semaforos[3].espera();
		semaforos[2].libera();
		pintarEstacionCarro(estacion,fila);
		sleep(segundosPorEstacion[estacion]*1000);
		pintarEstacionVacia(estacion,fila);
		semaforos[3].libera();
	}

	public void estacion4(int estacion, int fila) throws InterruptedException {
		semaforos[4].espera();
		pintarEstacionCarro(estacion,fila);
		sleep(segundosPorEstacion[estacion]*1000);
		pintarEstacionVacia(estacion,fila);
		semaforos[4].libera();
	}

	public void estacion5(int estacion , int fila) throws InterruptedException { 
		semaforos[5].espera();
		pintarEstacionCarro(estacion,fila);
		sleep(segundosPorEstacion[estacion]*1000);
		pintarEstacionVacia(estacion,fila);
		semaforos[5].libera();
	}

	public void estacion6(int estacion , int fila) throws InterruptedException{
		semaforos[6].espera();
		pintarEstacionCarro(estacion,fila);
		sleep(segundosPorEstacion[estacion]*1000);
		pintarEstacionVacia(estacion,fila);
		semaforos[6].libera();
	}

	private boolean limiteFabricacionAlcanzada() {
		semNumCarros.espera();
		return noCarros >= 100 ? true : false;
	}

}

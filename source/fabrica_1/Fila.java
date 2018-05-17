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
	private static int[][] robots;
	private static Semaforo[] semaforos;
	private boolean estaFabricando = true;
	private static int[] segundosPorEstacion = {1,2,1,2,1,2};
	private static int [] NoCarros;
	private Rutinas rutinas = new Rutinas();
	private int estacionVacia = 0;
	private int robotLibre = 1;
	private int robotOcupado = 2;
	private int robotTransmisionLibre = 3;
	private int robotTransmisionOcupado = 4;

	public Fila(int id, Graphics g, int[][] robots, Semaforo[] semaforos, int TamNoCarros) {
		this.graphics = g;
		this.filaID = id;
		this.robots = robots;
		this.semaforos = semaforos;
		this.imagenRobot = rutinas.obtenerImagen("./robot.png");
		this.imagenDefault = rutinas.obtenerImagen("./cinta.jpg");
		this.imagenRobotTrans = rutinas.obtenerImagen("./robot-trans.png");
		this.etapasCarro = inicializarEtapasCarro();
		this.NoCarros = new int [TamNoCarros];
		pintarFila();
	}

	public static Semaforo sem = new Semaforo(1);

	public BufferedImage[] inicializarEtapasCarro() {
		BufferedImage[] etapas = new BufferedImage[nomImg.length];	
		for (int i = 0; i < etapas.length; i++) {
			etapas[i] = rutinas.obtenerImagen("./"+nomImg[i]);
		}	
		return etapas;
	}

	public void run() {
		try {
			int estacion = 0;
			while(estaFabricando){
				semaforos[estacion].espera();
				int filaRobotDisponible = obtenerFilaRobotDisponible(estacion);
				if(robots[estacion][filaID] == robotLibre) {
					robots[estacion][filaID] = robotOcupado;				
					pintarEstacionCarro(estacion,filaID);
					sleep(segundosPorEstacion[estacion]*1000);
					pintarEstacionRobot(estacion, filaID);
					robots[estacion][filaID] = robotLibre;
					/* Estacion Moto-Transmision */
					if(estacion == 1) {
						int filaRobotTransDisponible = obtenerFilaRobotTransmisionDisponible(estacion);
						if(robots[estacion][filaID] == robotTransmisionLibre) {
							robots[estacion][filaID] = robotTransmisionOcupado;
							pintarEstacionCarroTransmision(estacion,filaID);
							sleep(segundosPorEstacion[estacion]*1000);
							pintarEstacionRobotTransmision(estacion,filaID);
						} else if (filaRobotTransDisponible > -1) {
							robots[estacion][filaRobotTransDisponible] = estacionVacia;
							robots[estacion][filaID] = robotTransmisionOcupado;
							pintarEstacionVacia(estacion,filaRobotTransDisponible);
							pintarEstacionCarroTransmision(estacion,filaID);
							sleep(segundosPorEstacion[estacion]*1000);
							pintarEstacionRobotTransmision(estacion,filaID);
						}
					}					
				} else if (filaRobotDisponible > -1) {
					robots[estacion][filaRobotDisponible] = estacionVacia;
					robots[estacion][filaID] = robotOcupado;
					pintarEstacionVacia(estacion,filaRobotDisponible);
					pintarEstacionCarro(estacion,filaID);
					sleep(segundosPorEstacion[estacion]*1000);
					pintarEstacionRobot(estacion, filaID);
					robots[estacion][filaID] = robotLibre;
				} else {
					semaforos[estacion].libera();
					continue;
				}
				semaforos[estacion].libera();

				if(estacion == 5) {
					estaFabricando = false;
					NoCarros(estacion,filaID);
				}
				estacion++;
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
		for(int i = 0; i < NoCarros.length; i++) {
			for(int j = 0 ; j < 6 ; j++){		
				graphics.drawImage(imagenDefault, j*100, i*100, null);
			}
		}
	}

	public int obtenerFilaRobotDisponible(int estacion) {
		for(int j = 0; j < robots[0].length; j++) {
			if(robots[estacion][j] == robotLibre) {
				return j;
			}
		}
		return -1;	
	}
	
	public int obtenerFilaRobotTransmisionDisponible(int estacion) {
		for(int j = 0; j < robots[0].length; j++) {
			if(robots[estacion][j] == robotTransmisionLibre) {
				return j;
			}
		}
		return -1;	
	}

	public synchronized void NoCarros(int estacion,int pos) {
		if(estacion == 5){
			NoCarros[pos]++;
		}
	}

	public String getNoCarros(int pos){
		return NoCarros[pos]+"";
	}

	public void pintarEstacionCarro(int estacion, int fila) {
		graphics.drawImage(imagenDefault, estacion*100, fila*100, null); 
		graphics.drawImage(imagenRobot, estacion*100, (fila*100)+10, null);
		graphics.drawImage(etapasCarro[estacion], estacion*100, fila*100, null);
	}

	public void pintarEstacionVacia(int estacion, int fila) {
		graphics.drawImage(imagenDefault, estacion*100, fila*100, null);
	}

	public void pintarEstacionRobot(int estacion, int fila) {
		graphics.drawImage(imagenDefault, estacion*100, fila*100, null);
		graphics.drawImage(imagenRobot, estacion*100, (fila*100)+10, null);
	}
	
	public void pintarEstacionRobotTransmision(int estacion, int fila) {
		graphics.drawImage(imagenDefault, estacion*100, fila*100, null);
		graphics.drawImage(imagenRobotTrans, estacion*100, (fila*100)+10, null);
	}
	
	public void pintarEstacionCarroTransmision(int estacion, int fila) {
		graphics.drawImage(imagenDefault, estacion*100, fila*100, null); 
		graphics.drawImage(imagenRobotTrans, estacion*100, (fila*100)+10, null);
		graphics.drawImage(etapasCarro[estacion], estacion*100, fila*100, null);
	}

}

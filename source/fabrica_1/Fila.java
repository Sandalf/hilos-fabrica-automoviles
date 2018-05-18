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
						agregarTransmision(estacion,filaID);
					}					
				} else if (filaRobotDisponible > -1) {
					if(estacion == 1 && robots[estacion][filaID] == robotTransmisionLibre) {
						agregarTransmision(estacion,filaID);
						robots[estacion][filaRobotDisponible] = robotTransmisionLibre;
						robots[estacion][filaID] = robotOcupado;
						pintarEstacionCarroTransmision(estacion,filaRobotDisponible);	
						pintarEstacionCarro(estacion,filaID);
						sleep(segundosPorEstacion[estacion]*1000);
						pintarEstacionRobot(estacion, filaRobotDisponible);
						robots[estacion][filaID] = robotLibre;
					} else {
						robots[estacion][filaRobotDisponible] = estacionVacia;
						robots[estacion][filaID] = robotOcupado;
						pintarEstacionVacia(estacion,filaRobotDisponible);
						pintarEstacionCarro(estacion,filaID);
						sleep(segundosPorEstacion[estacion]*1000);
						pintarEstacionRobot(estacion, filaID);					
						robots[estacion][filaID] = robotLibre;						
					}
					/* Estacion Moto-Transmision */
					if(estacion == 1) {
						agregarTransmision(estacion,filaID);
					}
				} else {
					semaforos[estacion].libera();
					continue;
				}
				semaforos[estacion].libera();

				if(estacion == 5) {
					estacion = 0;
					semNumCarros.espera();
					noCarros++;
					numCarroFabricando=noCarros;
					semNumCarros.libera();
				} else {
					estacion++;
				}			
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
				graphics.drawImage(imagenDefault, i*80, j*80, null);
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

	public String getNoCarro(){
		return numCarroFabricando+"";
	}

	public void pintarEstacionCarro(int estacion, int fila) {
		graphics.drawImage(imagenDefault, estacion*80, fila*80, null); 
		graphics.drawImage(imagenRobot, estacion*80, fila*80, null);
		graphics.drawImage(etapasCarro[estacion], estacion*80, fila*80, null);
	}

	public void pintarEstacionVacia(int estacion, int fila) {
		graphics.drawImage(imagenDefault, estacion*80, fila*80, null);
	}

	public void pintarEstacionRobot(int estacion, int fila) {
		graphics.drawImage(imagenDefault, estacion*80, fila*80, null);
		graphics.drawImage(imagenRobot, estacion*80, (fila*80), null);
	}
	
	public void pintarEstacionRobotTransmision(int estacion, int fila) {
		graphics.drawImage(imagenDefault, estacion*80, fila*80, null);
		graphics.drawImage(imagenRobotTrans, estacion*80, fila*80, null);
	}
	
	public void pintarEstacionCarroTransmision(int estacion, int fila) {
		graphics.drawImage(imagenDefault, estacion*80, fila*80, null); 
		graphics.drawImage(imagenRobotTrans, estacion*80, fila*80, null);
		graphics.drawImage(etapasCarro[estacion], estacion*80, fila*80, null);
	}
	
	public void agregarTransmision(int estacion, int fila) throws InterruptedException {
		int filaRobotTransDisponible = obtenerFilaRobotTransmisionDisponible(estacion);
		if(robots[estacion][fila] == robotTransmisionLibre) {
			robots[estacion][fila] = robotTransmisionOcupado;
			pintarEstacionCarroTransmision(estacion,fila);
			sleep(segundosPorEstacion[estacion]*1000);
			pintarEstacionRobotTransmision(estacion,fila);
		} else if (filaRobotTransDisponible > -1) {
			/* Validar cuando el carro este en una estacion con robot normal libre */
			if (robots[estacion][fila] == robotLibre) {
				robots[estacion][filaRobotTransDisponible] = robotLibre;
				pintarEstacionRobot(estacion, filaRobotTransDisponible);
				robots[estacion][fila] = robotTransmisionOcupado;
				pintarEstacionCarroTransmision(estacion,fila);
				sleep(segundosPorEstacion[estacion]*1000);
				pintarEstacionRobotTransmision(estacion,fila);
				robots[estacion][fila] = robotTransmisionLibre;
			} else {
				robots[estacion][filaRobotTransDisponible] = estacionVacia;
				robots[estacion][fila] = robotTransmisionOcupado;
				pintarEstacionVacia(estacion,filaRobotTransDisponible);
				pintarEstacionCarroTransmision(estacion,fila);
				sleep(segundosPorEstacion[estacion]*1000);
				pintarEstacionRobotTransmision(estacion,fila);
				robots[estacion][fila] = robotTransmisionLibre;
			}
		}
	}

	public boolean isEstaFabricando() {
		return estaFabricando;
	}

	public void setEstaFabricando(boolean estaFabricando) {
		this.estaFabricando = estaFabricando;
	}

}

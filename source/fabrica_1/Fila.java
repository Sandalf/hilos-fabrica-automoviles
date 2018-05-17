package fabrica_1;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.sound.midi.Synthesizer;
import javax.swing.JLabel;

public class Fila extends Thread {

	private int filaID;
	private BufferedImage imagenRobot;
	private BufferedImage imagenDefault;
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
	
	public Fila(int id, Graphics g, int[][] robots, Semaforo[] semaforos, int TamNoCarros) {
		this.graphics = g;
		this.filaID = id;
		this.robots = robots;
		this.semaforos = semaforos;
		this.imagenRobot = rutinas.obtenerImagen("./robot.png");
		this.imagenDefault = rutinas.obtenerImagen("./cinta.jpg");
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
				if(robots[estacion][filaID] == 1) {
					robots[estacion][filaID] = 2;
					graphics.drawImage(imagenDefault, estacion*100, filaID*100, null); 
					graphics.drawImage(imagenRobot, estacion*100, (filaID*100)+10, null);
					graphics.drawImage(etapasCarro[estacion], estacion*100, filaID*100, null);
					sleep(segundosPorEstacion[estacion]*1000);
					graphics.drawImage(imagenDefault, estacion*100, filaID*100, null);
					graphics.drawImage(imagenRobot, estacion*100, (filaID*100)+10, null);
					robots[estacion][filaID] = 1;
				} else if (filaRobotDisponible > -1) {
					robots[estacion][filaRobotDisponible] = 0;
					graphics.drawImage(imagenDefault, estacion*100, filaRobotDisponible*100, null);
					robots[estacion][filaID] = 2;
					graphics.drawImage(imagenRobot, estacion*100, (filaID*100)+10, null);
					graphics.drawImage(etapasCarro[estacion], estacion*100, filaID*100, null);
					sleep(segundosPorEstacion[estacion]*1000);
					graphics.drawImage(imagenDefault, estacion*100, filaID*100, null);
					graphics.drawImage(imagenRobot, estacion*100, (filaID*100)+10, null);
					robots[estacion][filaID] = 1;
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
			if(robots[estacion][j] == 1) {
				return j;
			}
		}
		return -1;	
	}
	
	public synchronized void NoCarros(int estacion,int pos) {
		if(estacion == 5){
			this.NoCarros[pos]++;
		}
	}
	
	public String getNoCarros(int pos){
		return this.NoCarros[pos]+"";
	}

}

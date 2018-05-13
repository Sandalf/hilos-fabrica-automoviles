package fabrica_1;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

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

	public Fila(int id, Graphics g, int[][] robots, Semaforo[] semaforos) throws IOException {
		this.graphics = g;
		this.filaID = id;
		this.robots = robots;
		this.semaforos = semaforos;
		this.imagenRobot = inicializarRobot();
		this.imagenDefault = inicializarImagenDefault();
		this.etapasCarro = inicializarEtapasCarro();
		pintarFila();
	}

	public BufferedImage inicializarRobot() throws IOException {
		InputStream is = this.getClass().getResourceAsStream("./robot.png");
		BufferedImage bi = (BufferedImage) ImageIO.read(is);
		return bi;
	}

	public BufferedImage[] inicializarEtapasCarro() throws IOException {
		BufferedImage[] etapas = new BufferedImage[nomImg.length];	
		for (int i = 0; i < etapas.length; i++) {
			InputStream is = this.getClass().getResourceAsStream("./"+nomImg[i]);
			BufferedImage bi = (BufferedImage) ImageIO.read(is);
			etapas[i] = bi;
		}	
		return etapas;
	}

	public ImageIcon[] inicializarImagenes() throws IOException {
		ImageIcon[] imagenes = new ImageIcon[6];
		InputStream is = this.getClass().getResourceAsStream("./cinta.jpg");
		Image img = ImageIO.read(is);

		for (int i = 0; i < imagenes.length; i++) {
			imagenes[i] = new ImageIcon(img);
		}

		return imagenes;
	}

	public BufferedImage inicializarImagenDefault() throws IOException {
		InputStream is = this.getClass().getResourceAsStream("./cinta.jpg");
		BufferedImage bi = (BufferedImage) ImageIO.read(is);
		return bi;
	}

	public void run() {
		try {
			int estacion = 0;
			while(estaFabricando){
				System.out.println("Estacion: " + estacion + ", Fila: " + filaID);

				semaforos[estacion].espera();
				int filaRobotDisponible = obtenerFilaRobotDisponible(estacion);
				if(robots[estacion][filaID] == 1) {
					robots[estacion][filaID] = 2;
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
				}

				estacion++;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void pintarFila() {
		int x = 0;
		int y = 0;

		for(int i = 0; i < 6; i++) {
			graphics.drawImage(imagenDefault, x, y, null);
			x += 100;
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

}

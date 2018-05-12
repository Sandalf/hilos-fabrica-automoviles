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
	private BufferedImage imagenDefault;
	private BufferedImage[] etapasCarro;
	private int carrosFabricados = 0;
	private String[] nomImg = { "chasis.png", "chMotor.png", "chTransmision.png", "chMoTrans.png", "completo.png",
			"carrocompleto.png" };
	private Graphics graphics;
	private static boolean[][] robots;
	private static Semaforo[] semaforos;
	private boolean estaFabricando = true;

	public Fila(int id, Graphics g, boolean[][] robots, Semaforo[] semaforos) throws IOException {
		this.graphics = g;
		this.filaID = id;
		this.robots = robots;
		this.semaforos = semaforos;
		imagenDefault = inicializarImagenDefault();
		etapasCarro = inicializarEtapasCarro();
		pintarFila();
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
			int fila = 0;
			while(estaFabricando){
				System.out.println("Estacion " + estacion);
				graphics.drawImage(etapasCarro[estacion], estacion*100, filaID*100, null);
				sleep(100);
				graphics.drawImage(imagenDefault, estacion*100, filaID*100, null);
				
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
	
	public boolean hayRobotsDisponibles(int estacion, int fila) {
		if(estacion == 0) {
			int robotsOcupados = 0;
			for(int j = 0; j < robots[0].length; j++) {
				if(robots[estacion][j]) {
					robotsOcupados++;
				}
			}
			
			if(robotsOcupados < 2 && robots[estacion][fila]) {
				return true;
			} else {
				return false;
			}
		}
		return false;		
	}

}

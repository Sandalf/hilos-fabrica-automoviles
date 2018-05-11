package fabrica_1;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Fila extends Thread {

	private BufferedImage imagenDefault;
	private BufferedImage[] etapasCarro;
	private int carrosFabricados = 0;
	private String[] nomImg = { "chasis.png", "chMotor.png", "chTransmision.png", "chMoTrans.png", "completo.png",
			"carrocompleto.png" };
	private Graphics graphics;

	public Fila(Graphics g) throws IOException {
		this.graphics = g;
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
			System.out.println("Estacion 1");
			System.out.println("Preparando chasis y cableado");
			graphics.drawImage(etapasCarro[0], 0, 0, null);
			sleep(100);
			graphics.drawImage(imagenDefault, 0, 0, null);
			System.out.println("Estacion 2");
			System.out.println("Preparando motor y transmision");
			graphics.drawImage(etapasCarro[1], 100, 0, null);
			sleep(100);
			graphics.drawImage(imagenDefault, 100, 0, null);
			System.out.println("Estacion 3");
			System.out.println("Preparando carroceria");
			graphics.drawImage(etapasCarro[2], 200, 0, null);
			sleep(100);
			graphics.drawImage(imagenDefault, 200, 0, null);
			System.out.println("Estacion 4");
			System.out.println("Preparando interiores");
			graphics.drawImage(etapasCarro[3], 300, 0, null);
			sleep(100);
			graphics.drawImage(imagenDefault, 300, 0, null);
			System.out.println("Estacion 5");
			System.out.println("Preparando llantas");
			graphics.drawImage(etapasCarro[4], 400, 0, null);
			sleep(100);
			graphics.drawImage(imagenDefault, 400, 0, null);
			System.out.println("Estacion 6");
			System.out.println("Realizando pruebas");
			graphics.drawImage(etapasCarro[5], 500, 0, null);
			sleep(100);
			carrosFabricados++;
			System.out.println("Listo!!");
			System.out.println("Carros fabricados: " + carrosFabricados);
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

}

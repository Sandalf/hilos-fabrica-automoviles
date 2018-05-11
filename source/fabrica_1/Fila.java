package fabrica_1;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Fila extends Thread {

	private ImageIcon[] imagenes;
	private BufferedImage[] etapasCarro;
	private int carrosFabricados = 0;
	private String[] nomImg = { "chasis.png", "chMotor.png", "chTransmision.png", "chMoTrans.png", "completo.png",
			"carrocompleto.png" };
	private Graphics graphics;

	public Fila(Graphics g) throws IOException {
		this.graphics = g;
		imagenes = inicializarImagenes();
		etapasCarro = inicializarEtapasCarro();
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

	public ImageIcon[] getImagenes() {
		return imagenes;
	}

	public void setImagenes(ImageIcon[] imagenes) {
		this.imagenes = imagenes;
	}

	public void run() {
		try {
			System.out.println("Estacion 1");
			System.out.println("Preparando chasis y cableado");
			graphics.drawImage(etapasCarro[0], 0, 0, null);
			sleep(100);
			System.out.println("Estacion 2");
			System.out.println("Preparando motor y transmision");
			sleep(100);
			System.out.println("Estacion 3");
			System.out.println("Preparando carroceria");
			sleep(100);
			System.out.println("Estacion 4");
			System.out.println("Preparando interiores");
			sleep(100);
			System.out.println("Estacion 5");
			System.out.println("Preparando llantas");
			sleep(100);
			System.out.println("Estacion 6");
			System.out.println("Realizando pruebas");
			sleep(100);
			carrosFabricados++;
			System.out.println("Listo!!");
			System.out.println("Carros fabricados: " + carrosFabricados);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

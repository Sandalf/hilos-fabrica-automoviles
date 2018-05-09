package fabrica_1;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Fila extends Thread {
	
	private ImageIcon[] imagenes; 
	private int carrosFabricados = 0;
	private int estacion = 0;
	
	public Fila() throws IOException {
		imagenes = inicializarImagenes();
	}
	
	public ImageIcon[] inicializarImagenes() throws IOException {
		ImageIcon[] imagenes = new ImageIcon[6];
		InputStream is = this.getClass().getResourceAsStream("./cinta.jpg");
		Image img = ImageIO.read(is);
		
		for(int i = 0; i < imagenes.length; i++) {
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

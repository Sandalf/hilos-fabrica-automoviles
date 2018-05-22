package fabrica_2;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import javax.imageio.ImageIO;

public class Rutinas {
	static Random R = new Random();
	
	public Rutinas() {
		
	}

	public int nextInt(int Valor) {
		return R.nextInt(Valor);
	}

	public int nextInt(int Ini, int Fin) {
		return R.nextInt(Fin - Ini + 1) + Ini;
	}

	public BufferedImage obtenerImagen(String ruta) {
		BufferedImage imagen = null;	
		try {
			InputStream is = this.getClass().getResourceAsStream(ruta);		
			imagen = (BufferedImage) ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return imagen;
	}
}

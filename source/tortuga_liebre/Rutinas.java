package tortuga_liebre;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

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
	
	 public  ImageIcon AjustarImagen(String ico,int Ancho,int Alto)
	    {
	        ImageIcon tmpIconAux = new ImageIcon(ico);
	        //Escalar Imagen
	        ImageIcon tmpIcon = new ImageIcon(tmpIconAux.getImage().getScaledInstance(Ancho,
	        		Alto, Image.SCALE_SMOOTH));//SCALE_DEFAULT
	        return tmpIcon;
	    }
}

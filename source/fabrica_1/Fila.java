package fabrica_1;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Fila {
	
	private ImageIcon[] imagenes; 
	
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
	
}

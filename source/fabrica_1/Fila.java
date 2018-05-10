package fabrica_1;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Fila {
	
	private ImageIcon[] imagenes; 
	private Image [] imgCarro;
	private String [] NomImg ={"chasis.png","chMotor.png","chTransmision.png","chMoTrans.png","completo.png","carrocompleto.png"};
	
	public Fila() throws IOException {
		imagenes = inicializarImagenes();
	}
	
	public Image[] inicializarImagenesCarro(Image[] imgCarro) throws IOException {
		imgCarro = new Image[6];
		for(int i = 0 ; i < imgCarro.length ; i++){
			imgCarro[i] = Rutinas.AjustarImagen(NomImg[i],50,60).getImage();
		}
		return imgCarro;
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

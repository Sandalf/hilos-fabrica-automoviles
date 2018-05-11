package fabrica_1;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Fabrica extends JFrame {

	Graphics graphics;
	Image imageBuffer;
	Image [] img;

	public Fabrica() {
		CrearInterfaz();
	}

	public void CrearInterfaz() {
		setSize(600,100);
		setLayout(null);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		imageBuffer = createImage(getWidth(),getHeight());
		graphics = imageBuffer.getGraphics();

		crearFilas();
		
		
	}

	public void paint(Graphics g) {
		g.drawImage(imageBuffer, 0, 0, getWidth(), getHeight(), this);
		for(int i = 0 ; i < img.length ; i++)
		g.drawImage(img[i],0,310,50,30,this);
	}

	public void pintarFila(Fila fila) throws IOException {
		int x = 0;
		int y = 0;

		for(ImageIcon image : fila.getImagenes()) {
			BufferedImage bi = (BufferedImage) image.getImage();
			graphics.drawImage(bi, x, y, null);
			image.paintIcon(null, graphics, x, y);
			x += 100;
		}

		repaint();
	}

	public void crearFilas() {
		try {
			Fila fila = new Fila();
			pintarFila(fila);
			fila.inicializarImagenesCarro(img);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

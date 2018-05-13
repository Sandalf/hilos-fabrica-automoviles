package tortuga_liebre;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.Timer;

public class Carrera extends JFrame implements ActionListener {
	
	Graphics graphics;
	Image imageBuffer;
	
	public Carrera() {
		try {
			CrearInterfaz();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void CrearInterfaz() throws IOException {
		setSize(600,122);
		setLayout(null);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		imageBuffer = createImage(getWidth(),getHeight());
		graphics = imageBuffer.getGraphics();
		
		pintarPista();
		
		Timer t = new Timer(1, this);
        t.setRepeats(true);
        t.setInitialDelay(0);
        t.start();
	}
	
	public void paint(Graphics g) {
		g.drawImage(imageBuffer, 0, 0, getWidth(), getHeight(), this);
	}
	
	public void pintarPista() throws IOException {
		BufferedImage imagenPista = null;
		BufferedImage imagenPuente = null;
		
		InputStream isPista = this.getClass().getResourceAsStream("./camino.png");
		InputStream isPuente = this.getClass().getResourceAsStream("./puente.png");
		
		imagenPista = (BufferedImage) ImageIO.read(isPista);
		imagenPuente = (BufferedImage) ImageIO.read(isPuente);
		
		for(int j = 0; j < 6; j++) {
			// Dibujar Puente
			if (j == 1 ||  j == 3) {
				graphics.drawImage(imagenPuente, j*100, 22, null);
			} else {
				// Dibujar Pista
				graphics.drawImage(imagenPista, j*100, 22, null);
			}
		}
		
		repaint();
	}

	public void actionPerformed(ActionEvent e) {
		repaint();
	}
}

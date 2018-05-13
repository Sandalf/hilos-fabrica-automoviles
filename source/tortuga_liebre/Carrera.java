package tortuga_liebre;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.Timer;

@SuppressWarnings("serial")
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
		crearCorredores();
		
		Timer t = new Timer(1, this);
        t.setRepeats(true);
        t.setInitialDelay(0);
        t.start();
	}
	
	public void paint(Graphics g) {
		g.drawImage(imageBuffer, 0, 0, getWidth(), getHeight(), this);
	}
	
	public void pintarPista() {
		Rutinas rutinas = new Rutinas();
		BufferedImage imagenPista = rutinas.obtenerImagen("./camino.png");
		BufferedImage imagenPuente = rutinas.obtenerImagen("./puente.png");
		
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

	public void crearCorredores() {
		Liebre liebre = new Liebre(1,graphics);
		liebre.start();
	}
	
	public void actionPerformed(ActionEvent e) {
		repaint();
	}
}

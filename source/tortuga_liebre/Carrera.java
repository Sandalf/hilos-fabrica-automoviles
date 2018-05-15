package tortuga_liebre;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Carrera extends JFrame implements ActionListener {
	
	Graphics graphics;
	Image imageBuffer;
	Liebre[] liebres;
	Semaforo[] semaforos;
	boolean[] puentes = { false, false };
	
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
		inicializarSemaforos();
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
		liebres = new Liebre[2];
		for(int i = 0; i < 2; i++) {
			liebres[i] = new Liebre(i, semaforos, puentes);
		}
		
		for(int i = 0; i < 2; i++) {
			liebres[i].start();
		}
	}
	
	public void pintarCorredores() {
		pintarPista();
		for(int i = 0; i < liebres.length; i++) {
			BufferedImage imagenCorredor = liebres[i].getImagenCorredor();
			int distanciaRecorrida = liebres[i].getDistanciaRecorrida();
			pintarCorredor(imagenCorredor,distanciaRecorrida);
		}
	}
	
	public void pintarCorredor(BufferedImage imagen, int distanciaRecorrida) {
		graphics.drawImage(imagen, distanciaRecorrida, 0, null);
	}
	
	public void inicializarSemaforos() {
		semaforos = new Semaforo[2];
		for(int i = 0; i < 2; i++) {
			semaforos[i] = new Semaforo(1);
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		pintarCorredores();
		repaint();
	}
}

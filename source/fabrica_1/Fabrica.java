package fabrica_1;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Fabrica extends JFrame implements ActionListener {

	Graphics graphics;
	Image imageBuffer;
	Image [] img;
	Fila[] filas;
	boolean[][] robots = {
			{false,false},{false,false},{false,false},
			{false,false},{false,false},{false,false}};
	private Semaforo[] semaforos;

	public Fabrica() {
		CrearInterfaz();
	}

	public void CrearInterfaz() {
		setSize(600,200);
		setLayout(null);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		imageBuffer = createImage(getWidth(),getHeight());
		graphics = imageBuffer.getGraphics();

		inicialzarSemaforos();
		crearFilas();
		
		Timer t = new Timer(1, this);
        t.setRepeats(true);
        t.setInitialDelay(0);
        t.start();
	}

	public void paint(Graphics g) {
		g.drawImage(imageBuffer, 0, 0, getWidth(), getHeight(), this);
	}

	public void crearFilas() {
		try {
			filas = new Fila[2];
			
			for(int i = 0; i < filas.length; i++) {
				filas[i] = new Fila(i,graphics,robots,semaforos);
			}
			
			for(int i = 0; i < filas.length; i++) {
				filas[i].start();
			}	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void actionPerformed(ActionEvent e) {
		repaint();
	}
	
	public void inicialzarSemaforos() {
		this.semaforos = new Semaforo[robots.length];
		for(int i = 0; i < robots.length; i++) {
			semaforos[i] = new Semaforo(1);
		}
	}

}

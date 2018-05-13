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
	// Estatus Robots:
	// 0 - no hay robot en estacion
	// 1 - el robot esta disponible
	// 2 - el robot esta ocupado
	int[][] robots = {
			{1,0,1,0},{1,0,0,0},{1,0,1,0},
			{1,0,0,0},{1,0,1,1},{1,0,0,0}};
	Semaforo[] semaforos;
	int carrosFabricados = 0;

	public Fabrica() {
		CrearInterfaz();
	}

	public void CrearInterfaz() {
		setSize(600,robots[0].length*100);
		setLayout(null);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		imageBuffer = createImage(getWidth(),getHeight());
		graphics = imageBuffer.getGraphics();

		inicializarSemaforos();
		crearFilas();
		
		Timer t = new Timer(1, this);
        t.setRepeats(true);
        t.setInitialDelay(0);
        t.start();
        
        while(filas[0].isAlive() ||
        		filas[1].isAlive() ||
        		filas[2].isAlive() ||
        		filas[3].isAlive());
        
        for(int i = 0; i < robots.length; i++) {
        		for(int j = 0; j < robots[0].length; j++) {
        			System.out.print(robots[i][j] + " ");
            }
        		System.out.println();
        }
	}

	public void paint(Graphics g) {
		g.drawImage(imageBuffer, 0, 0, getWidth(), getHeight(), this);
	}

	public void crearFilas() {
		try {
			filas = new Fila[4];
			
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
	
	public void inicializarSemaforos() {
		this.semaforos = new Semaforo[robots.length];
		for(int i = 0; i < robots.length; i++) {
			semaforos[i] = new Semaforo(1);
		}
	}

}

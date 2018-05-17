package fabrica_1;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Fabrica extends JFrame implements ActionListener {

	// Estatus Robots:
	// 0 - no hay robot en estacion
	// 1 - el robot esta disponible
	// 2 - el robot esta ocupado
	int[][] robots;
	Semaforo[] semaforos;
	Rutinas rutinas = new Rutinas();
	Graphics graphics;
	Image imageBuffer;
	Image [] img;
	Fila[] filas;
	JLabel [] etiquetas;
	//int tamano = rutinas.nextInt(5,8);
	int tamano = 5;

	public Fabrica() {
		CrearInterfaz();
	}

	public void CrearInterfaz() {
		setSize(650,tamano*100);
		setAlwaysOnTop(true);
		setLayout(null);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		imageBuffer = createImage(getWidth(),getHeight());
		graphics = imageBuffer.getGraphics();

		crearRobots();
		inicializarSemaforos();
		crearFilas();
		crearEtiquetas();

		Timer t = new Timer(1, this);
		t.setRepeats(true);
		t.setInitialDelay(0);
		t.start();
	}

	public void paint(Graphics g) {
		g.drawImage(imageBuffer, 0, 0, getWidth(), getHeight(), this);
	}

	public void crearFilas() {
		filas = new Fila[tamano];

		for(int i = 0; i < filas.length; i++) {
			filas[i] = new Fila(i,graphics,robots,semaforos,filas.length);
		}

		pintarRobots();

		for(int i = 0; i < filas.length; i++) {
			filas[i].start();
		}
	}

	public void actionPerformed(ActionEvent e) {
		actualizaEtiquetas();
		repaint();
	}

	public void crearEtiquetas(){
		etiquetas = new JLabel [filas.length];
		for(int i = 0 ; i < filas.length ; i++){
			etiquetas[i] = new JLabel();
			etiquetas[i].setBounds(getWidth()-20, 20, 20, 20);
			etiquetas[i].setVisible(true);
			add(etiquetas[i]);
		}
	}

	public void inicializarSemaforos() {
		this.semaforos = new Semaforo[robots.length];
		for(int i = 0; i < robots.length; i++) {
			semaforos[i] = new Semaforo(1);
		}
	}

	public void actualizaEtiquetas(){
		for(int i = 0 ; i < etiquetas.length ; i++){
			etiquetas[i].setText(filas[i].getNoCarros(i));
			filas[i].pintarNoCarros(etiquetas);
			etiquetas[i].update(etiquetas[i].getGraphics());
		}
	}

	public void pintarRobots() {
		BufferedImage imagenRobot = rutinas.obtenerImagen("./robot.png");
		for(int i = 0; i < robots.length; i++) {
			for(int j = 0; j < robots[i].length; j++) {
				if(robots[i][j] == 1) {
					graphics.drawImage(imagenRobot, (i*100), (j*100)+10, null);
				}
			}
		}
	}

	public void crearRobots() {
		robots = new int[6][tamano];
		int robotsEstacion = rutinas.nextInt(1,tamano);
		for(int i = 0; i < robots.length; i++) {
			for(int j = 0; j < robots[i].length; j++) {
				if(i == 0 && j < 5) {
					robots[i][j] = 1;
				} else if (i == 1 && j < 6) {
					robots[i][j] = 1;
				} else if ((i == 2 || i == 3 || i == 4) && j < 3) {
					robots[i][j] = 1;
				} else if (i == 5 && j < robotsEstacion) {
					robots[i][j] = 1;
				} else {
					robots[i][j] = 0;
				}
			}
		}
	}

}

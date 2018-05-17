package fabrica_1;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Fabrica extends JFrame implements ActionListener {

	Graphics graphics;
	Image imageBuffer;
	Image [] img;
	Fila[] filas;
	JLabel [] etiquetas;
	//int tama�o = Rutinas.nextInt(8,15);
	int tamano = 4 ;
	// Estatus Robots:
	// 0 - no hay robot en estacion
	// 1 - el robot esta disponible
	// 2 - el robot esta ocupado
	int[][] robots = {
			{1,0,1,0},{1,0,0,0},{1,0,1,0},
			{1,0,0,0},{1,0,1,1},{1,0,0,0}};
	Semaforo[] semaforos;
	Rutinas rutinas = new Rutinas();

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

		inicializarSemaforos();
		crearFilas();
		crearEtiquetas();
		agregarEtiquetas();

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
			filas = new Fila[tamano];

			for(int i = 0; i < filas.length; i++) {
				filas[i] = new Fila(i,graphics,robots,semaforos,filas.length);
			}
			pintarRobots();
			for(int i = 0; i < filas.length; i++) {
				filas[i].start();
			}	
		} catch (IOException e) {
			e.printStackTrace();
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
		}
	}

	public void inicializarSemaforos() {
		this.semaforos = new Semaforo[robots.length];
		for(int i = 0; i < robots.length; i++) {
			semaforos[i] = new Semaforo(1);
		}
	}

	public void agregarEtiquetas(){
		for(int i = 0 ; i < etiquetas.length ; i++){
			this.add(etiquetas[i]);
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

}

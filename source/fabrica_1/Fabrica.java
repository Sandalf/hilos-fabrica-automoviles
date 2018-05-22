package fabrica_1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Fabrica extends JFrame implements ActionListener {

	// Estatus Robots:
	// 0 - no hay robot en estacion
	// 1 - el robot esta disponible
	// 2 - el robot esta ocupado
	// 3 - el robot de transmision esta disponible
	// 4 - el robot de transmision esta ocupado
	int[][] robots;
	Semaforo[] semaforos;
	Rutinas rutinas = new Rutinas();
	Graphics graphics;
	Image imageBuffer;
	BufferedImage imagenEstaciones;
	Timer t;
	Image [] img;
	Fila[] filas;
	JLabel [] etiquetas;
	int[] segundosPorEstacion = {1,1,1,1,1,1};
	int tamano = 7;//rutinas.nextInt(7,9);
	int limiteCarros = 10;

	public Fabrica() {
		CrearInterfaz();
	}

	public void CrearInterfaz() {
		setSize(600,(tamano*80)+50);
		setAlwaysOnTop(true);
		setLayout(null);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		imageBuffer = createImage(480,getHeight());
		graphics = imageBuffer.getGraphics();
		imagenEstaciones = rutinas.obtenerImagen("./estaciones.png");
		graphics.drawImage(imagenEstaciones, 0, 20, 480, 50, this);

		crearRobots();
		inicializarSemaforos();
		crearFilas();
		crearEtiquetas();
		getContentPane().setBackground(Color.WHITE);

		t = new Timer(0, this);
		t.setRepeats(true);
		t.start();
	}

	public void paint(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(550, 0, 10, getHeight());
		
		/* Detener fabrica */
		if (Fila.noCarros >= limiteCarros) {
			//detenerFilas();
			t.stop();
			actualizaEtiquetas();
			dispose();
			JOptionPane.showMessageDialog(null, "Jornada finalizada con " + Fila.noCarros + " carros");
		}
		
		g.drawImage(imageBuffer, 0, 0, 480, getHeight(), this);
		
	}

	public void crearFilas() {
		filas = new Fila[tamano];

		for(int i = 0; i < filas.length; i++) {
			filas[i] = new Fila(i,graphics,robots,semaforos,segundosPorEstacion);
		}
		//pintarRobots();
		for(int i = 0; i < filas.length; i++) {
			filas[i].start();
		}
	}

	public void actionPerformed(ActionEvent e) {
		actualizaEtiquetas();
		repaint();
	}

	public void crearEtiquetas() {
		etiquetas = new JLabel [filas.length];
		for(int i = 0 ; i < filas.length ; i++){
			etiquetas[i] = new JLabel("0");
			etiquetas[i].setBounds(550, (i*80)+30, 100, 100);
			etiquetas[i].setVisible(true);
			add(etiquetas[i]);
		}
	}

	public void inicializarSemaforos() {
		this.semaforos = new Semaforo[7];
		semaforos[0] = new Semaforo(5);
		semaforos[1] = new Semaforo(4);
		semaforos[2] = new Semaforo(2);
		for(int i = 3 ; i < semaforos.length;i++){
			semaforos[i] = new Semaforo(3);
			if(i <= 5 ){
				semaforos[5] = new Semaforo(tamano);
				semaforos[6] = new Semaforo(tamano);
			}	
		}
	}

	public void actualizaEtiquetas(){	
		for(int i = 0 ; i < etiquetas.length ; i++) {
			etiquetas[i].setText(filas[i].getNoCarro());
			for(int j = 0 ; j < 4 ; j++) {
				etiquetas[i].update(etiquetas[i].getGraphics());
			}
		}
	}

	public void pintarRobots() {
		BufferedImage imagenRobot = rutinas.obtenerImagen("./robot.png");
		BufferedImage imagenRobotTrans = rutinas.obtenerImagen("./robot-trans.png");
		for(int i = 0; i < robots.length; i++) {
			for(int j = 0; j < robots[i].length; j++) {
				if(robots[i][j] == 1) {
					graphics.drawImage(imagenRobot, i*80, (j*80)+50, null);
				} else if(robots[i][j] == 3) {
					graphics.drawImage(imagenRobotTrans, i*80, (j*80)+50, null);
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
					if(j < 4) {
						robots[i][j] = 1;
					} else {
						robots[i][j] = 3;
					}				
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
	
	
	
//	public void detenerFilas() {
//		for(int i = 0; i < filas.length; i++) {
//			filas[i].setEstaFabricando(false);
//		}
//	}

}

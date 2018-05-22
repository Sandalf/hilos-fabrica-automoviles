package fabrica_2;

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

import fabrica_1.Fila;

@SuppressWarnings("serial")
public class Fabrica extends JFrame implements ActionListener {

	Rutinas rutinas = new Rutinas();
	Graphics graphics;
	Image imageBuffer;
	BufferedImage imagenEstaciones;
	Timer t;
	Image [] img;
	JLabel [] etiquetas;
	int[] segundosPorEstacion = {1,1,1,1,1,1};
	int tamano = rutinas.nextInt(7,9);
	static Estacion1 estacion1;
	static Estacion2 estacion2;
	static Estacion3 estacion3;
	static Estacion4 estacion4;
	static Estacion5 estacion5;
	static Estacion6 estacion6;
	static Semaforo[] semaforos;
	static Semaforo semNumCarros;
	static int limiteCarros = 1;
	static int carrosFabricados = 0;
	static Estacion[][] hilos;

	public Fabrica() {
		CrearInterfaz();
	}

	public void CrearInterfaz() {
		hilos = new Estacion[tamano][6];
		setSize(500,(tamano*80)+50);
		setAlwaysOnTop(true);
		setLayout(null);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		imageBuffer = createImage(480,getHeight());
		graphics = imageBuffer.getGraphics();
		imagenEstaciones = rutinas.obtenerImagen("./estaciones.png");
		graphics.drawImage(imagenEstaciones, 0, 20, 480, 50, this);

		getContentPane().setBackground(Color.WHITE);

		semaforos = new Semaforo[6];
		semaforos[0] = new Semaforo(5);
		semaforos[1] = new Semaforo(4);
		semaforos[2] = new Semaforo(3);
		semaforos[3] = new Semaforo(3);
		semaforos[4] = new Semaforo(3);
		semaforos[5] = new Semaforo(3);

		semNumCarros = new Semaforo(1);

		for(int i = 0; i < hilos.length; i++) {
			estacion6 = new Estacion6(graphics,i,5,semaforos[5],semNumCarros,limiteCarros,carrosFabricados,null);
			estacion5 = new Estacion5(graphics,i,4,semaforos[4],semNumCarros,limiteCarros,carrosFabricados,estacion6);
			estacion4 = new Estacion4(graphics,i,3,semaforos[3],semNumCarros,limiteCarros,carrosFabricados,estacion5);
			estacion3 = new Estacion3(graphics,i,2,semaforos[2],semNumCarros,limiteCarros,carrosFabricados,estacion4);
			estacion2 = new Estacion2(graphics,i,1,semaforos[1],semNumCarros,limiteCarros,carrosFabricados,estacion3);
			estacion1 = new Estacion1(graphics,i,0,semaforos[0],semNumCarros,limiteCarros,carrosFabricados,estacion2);
			hilos[i][0] = estacion1;
			hilos[i][1] = estacion2;
			hilos[i][2] = estacion3;
			hilos[i][3] = estacion4;
			hilos[i][4] = estacion5;
			hilos[i][5] = estacion6;
		}

		for(int i = 0; i < hilos.length; i++) {
			for(int j = 0; j < hilos[0].length; j++) {
				hilos[i][j].start();
			}
		}

		t = new Timer(0, this);
		t.setRepeats(true);
		t.start();	
	}

	public void paint(Graphics g) {	
		g.drawImage(imageBuffer, 0, 0, 480, getHeight(), this);
	}

	public void actionPerformed(ActionEvent e) {
		repaint();
	}

}

package fabrica_1;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	//int tamaño = Rutinas.nextInt(8,15);
	int tamaño = 4 ;
	// Estatus Robots:
	// 0 - no hay robot en estacion
	// 1 - el robot esta disponible
	// 2 - el robot esta ocupado
	int[][] robots = {
			{1,0,1,0},{1,0,0,0},{1,0,1,0},
			{1,0,0,0},{1,0,1,1},{1,0,0,0}};
	Semaforo[] semaforos;
	
	// TODO:
	// - Contar carros fabricados por fila // falta ver por que pinta las etiquetas y el contador lo hice static
	// - Pintar cintas desde el inicio //pinto todas la cintas antes de empezar 
	// - Pintar robots desde el inicio // ya agregue los robots por estacion falta aumentar el numero de filas 
	// son aleatorias entre 8 y 15 cale una mamada pero no cabe en la pantalla por que son muchos pixeles

	public Fabrica() {
		CrearInterfaz();
	}

	public void CrearInterfaz() {
		//setSize(650,robots[0].length*100);
		setSize(650,tamaño*100);
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
		ActualizaEtiquetas();
		
		
		
		
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
			filas = new Fila[tamaño];
			
			for(int i = 0; i < filas.length; i++) {
				filas[i] = new Fila(i,graphics,robots,semaforos,filas.length);
			}
			
			for(int i = 0; i < filas.length; i++) {
				filas[i].start();
			}	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void actionPerformed(ActionEvent e) {
//		removeAll();
//		ActualizaEtiquetas();
		repaint();
		setVisible(true);
	}
	public void crearEtiquetas(){
		etiquetas = new JLabel [filas.length];
		for(int i = 0 ; i < filas.length ; i++){
			etiquetas[i] = new JLabel("0");
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
	public void ActualizaEtiquetas(){
		for(int i = 0 ; i < etiquetas.length ; i++){
			etiquetas[i].setText(filas[i].getNoCarros(i));
			filas[i].pintarNoCarros(etiquetas);	
			etiquetas[i].grabFocus();
		}
	}

}

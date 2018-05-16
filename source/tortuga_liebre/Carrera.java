package tortuga_liebre;

import java.awt.Color;
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
	Corredor[] corredores;
	Semaforo[] semaforos;
	Puente[]  puentes = { new Puente(100,100), new Puente(300,100) };
	
	public Carrera() {
		try {
			CrearInterfaz();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void CrearInterfaz() throws IOException {
		setSize(630,122);
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
		BufferedImage imagenMeta = rutinas.obtenerImagen("./meta.png");
			
		for(int j = 0; j < 6; j++) {
			graphics.drawImage(imagenPista, (j*100)+60, 22, null);
		}
		
		/* Dibujar meta */
		graphics.drawImage(imagenMeta, 0, 22, null);
		graphics.drawImage(imagenMeta, imagenMeta.getWidth(), 22, null);
		graphics.drawImage(imagenMeta, getWidth()-imagenMeta.getWidth(), 22, null);
		graphics.drawImage(imagenMeta, getWidth()-(imagenMeta.getWidth()*2), 22, null);
		
		pintarPuentes();
		
		repaint();
	}

	public void crearCorredores() {
		corredores = new Corredor[6];
		for(int i = 0; i < corredores.length; i++) {
			if(i % 2 == 0) {
				corredores[i] = new Liebre(i, semaforos, puentes);
			} else {
				corredores[i] = new Tortuga(i, semaforos, puentes);
			}			
		}
		
		for(int i = 0; i < corredores.length; i++) {
			corredores[i].start();
		}
	}
	
	public void pintarCorredores() {
		pintarPista();
		for(int i = 0; i < corredores.length; i++) {
			BufferedImage imagenCorredor = corredores[i].getImagenCorredor();
			int distanciaRecorrida = corredores[i].getDistanciaRecorrida();
			pintarCorredor(imagenCorredor,distanciaRecorrida-imagenCorredor.getWidth());
		}
	}
	
	public void pintarCorredor(BufferedImage imagen, int distanciaRecorrida) {
		graphics.drawImage(imagen, distanciaRecorrida, 50, null);
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
	
	public void pintarPuentes() {
		Color colorDefault = graphics.getColor();
		
		for(Puente puente : puentes) {
			graphics.setColor(new Color(153,204,255));
			graphics.fillRect(puente.getPosicion(), 0, puente.getAncho(), 122);
			graphics.setColor(new Color(219,219,219));
			graphics.fillRect(puente.getPosicion(), 50, puente.getAncho(), 48);
		}
		
		graphics.setColor(colorDefault);
	}
}

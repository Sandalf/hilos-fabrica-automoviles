package tortuga_liebre;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Liebre extends Corredor {
	
	private Graphics graphics;
	private BufferedImage imagenCorredor;
	private Rutinas rutinas = new Rutinas();
	private boolean corriendo = true;
	private int distanciaRecorrida = 0;

	public Liebre(int corredorID, Graphics graphics) {
		super(corredorID);
		this.graphics = graphics;	
		this.imagenCorredor = rutinas.obtenerImagen("./liebre1.png");
		pintarCorredor();
	}
	
	public void pintarCorredor() {
		graphics.drawImage(imagenCorredor, distanciaRecorrida, 0, null);
	}
	
	public void run() {
		while(corriendo) {
			int pasos = rutinas.nextInt(3,10);
			distanciaRecorrida += pasos;
			pintarCorredor();
			
			if (distanciaRecorrida >= 500) {
				corriendo = false;
			}
		}
	}
	
}

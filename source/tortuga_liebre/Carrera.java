package tortuga_liebre;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;

public class Carrera extends JFrame {
	
	Graphics graphics;
	Image imageBuffer;
	
	public Carrera() {
		CrearInterfaz();
	}
	
	public void CrearInterfaz() {
		setSize(600,100);
		setLayout(null);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void paint(Graphics g) {
		g.drawImage(imageBuffer, 0, 0, getWidth(), getHeight(), this);
	}
	
}

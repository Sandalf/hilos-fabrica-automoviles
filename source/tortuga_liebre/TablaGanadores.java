package tortuga_liebre;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class TablaGanadores extends JFrame {
	
	Graphics graphics;
	Image imageBuffer;
	ArrayList<Corredor> ganadores;
	JButton btnAnt;
	JButton btnSig;
	Corredor corredorActual;
	JLabel lblPosicion;

	public TablaGanadores(ArrayList<Corredor> ganadores) {
		this.ganadores = ganadores;
		CrearInterfaz();
	}
	
	public void CrearInterfaz() {
		setSize(300,300);
		setLayout(null);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		corredorActual = ganadores.get(0);
		
		btnAnt = new JButton("<");
		btnAnt.setBounds(20, 140, 20, 20);
		btnAnt.setVisible(true);
		add(btnAnt);
		
		
		btnSig = new JButton(">");
		btnSig.setBounds(260, 140, 20, 20);
		btnSig.setVisible(true);
		add(btnSig);
		
		
		setVisible(true);
			
		imageBuffer = createImage(getWidth(),getHeight());
		graphics = imageBuffer.getGraphics();
		
		pintarCorredor();
	}
	
	public void paint(Graphics g) {
		g.drawImage(imageBuffer, 0, 0, getWidth(), getHeight(), this);
	}
	
	public void pintarCorredor() {
		graphics.drawImage(corredorActual.getImagenCorredor(), 125, 125, null);
		repaint();
	}
}

package tortuga_liebre;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class TablaGanadores extends JPanel {
	
	Graphics graphics;
	Image imageBuffer;
	ArrayList<Corredor> ganadores;
	JButton btnAnt;
	JButton btnSig;
	Corredor corredorActual;
	JLabel lblPosicion;
	Rutinas rutinas = new Rutinas();

	public TablaGanadores(ArrayList<Corredor> ganadores) {
		this.ganadores = ganadores;
		CrearInterfaz();
	}
	
	public void CrearInterfaz() {
		setSize(150,800);
		setLayout(null);
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
	}
	
	public void paint(Graphics g) {
		g.drawImage(rutinas.obtenerImagen("./fondo.png"), 0, 0, null);	
		g.drawString("Ganador!!", 50, 20);	
		g.drawImage(corredorActual.getImagenCorredor(), 45, 40, null);
		for(int i = 0; i < 8; i++) {
			Image imagenReducida = ganadores.get(i).getImagenCorredor().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
			g.drawString("#"+(i+1), 10, 135+(i*30));
			g.drawImage(imagenReducida,30,120+(i*30),null);
		}
		if(ganadores.size()>8){
			for(int i = 8 ; i < ganadores.size() ; i++) {
				Image imagenReducida = ganadores.get(i).getImagenCorredor().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
				g.drawString("#"+(i+1), 70, 135+((i-8)*30));
				g.drawImage(imagenReducida,90, 120+((i-8)*30),null);
			}
		}

	}
	
}

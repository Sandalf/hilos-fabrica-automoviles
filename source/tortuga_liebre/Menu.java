package tortuga_liebre;

import java.awt.Graphics;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Menu extends JFrame {
	
	/*
	 *Falta ajustar el foco de los componentes
	 *y creo que seria mejor usar el LeEntero en las cajas de texto
	 */
	
	
	private JLabel lblPuente1,lblPuente2,lblPuente3;
	private JTextField posPuente1, posPuente2,posPuente3;
	private JComboBox tortugas,liebres;
	private Rutinas rutinas = new Rutinas();
	
	public Menu() {
		CrearInterfaz();
	}
	
	public void CrearInterfaz() {
		setSize(300,300);
		setLayout(null);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//CAJAS DE TEXTO
		posPuente1 = new JTextField();
		posPuente1.setBounds(70,40,100,25);
		add(posPuente1);
		posPuente1.setVisible(true);
		
		
		posPuente2 = new JTextField();
		posPuente2.setBounds(70, 70, 100, 25);
		add(posPuente2);
		posPuente2.setVisible(true);
		
		posPuente3 = new JTextField();
		posPuente3.setBounds(70, 100, 100, 25);
		add(posPuente3);
		//posPuente3.setVisible(true);
		
		//ETIQUETAS
		
		lblPuente1 = new JLabel("Puente 1:");
		lblPuente1.setBounds(10,40,100,30);
		add(lblPuente1);
		lblPuente1.setVisible(true);
		
		lblPuente2 = new JLabel("Puente 2:");
		lblPuente2.setBounds(10,70,100,30);
		add(lblPuente2);
		
		lblPuente3 = new JLabel("Puente 3:");
		lblPuente3.setBounds(10,100,100,30);
		add(lblPuente3);
		
		//COMBOS
		tortugas = new JComboBox();
		liebres  = new JComboBox();
		
		tortugas.setBounds(35, 160, 100, 20);
		liebres.setBounds(145, 160, 100, 20);
		tortugas.addItem("Tortugas");
		liebres.addItem("Liebres");
		for(int i = 1 ; i < 5 ; i++ ) {
			tortugas.addItem(i+4+"");
			liebres.addItem(i+2+"");
		}
		liebres.removeItem("6");
			
		add(tortugas);
		add(liebres);
		
		setVisible(true);
	}
	
	public void paint(Graphics g) {
		g.drawImage(rutinas.obtenerImagen("./fondoM.jpg"), 0, 0, null);
		//repaint();
	}
}

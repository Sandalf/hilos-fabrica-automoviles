package tortuga_liebre;

import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Menu extends JPanel {
	
	private JLabel lblPuente1;
	private JTextField posPunte1;

	public Menu() {
		CrearInterfaz();
	}
	
	public void CrearInterfaz() {
		setSize(300,300);
		setLayout(null);
		
		lblPuente1 = new JLabel("Puente 1:");
		lblPuente1.setBounds(10,40,100,30);
		lblPuente1.setVisible(true);
		add(lblPuente1);
		
		posPunte1 = new JTextField();
		posPunte1.setBounds(70,40,100,30);
		posPunte1.setVisible(true);
		add(posPunte1);
		
		setVisible(true);
	}
	
	public void paint(Graphics g) {
		g.drawString("Hello to JavaTutorial.net", 10, 10);
	}
}

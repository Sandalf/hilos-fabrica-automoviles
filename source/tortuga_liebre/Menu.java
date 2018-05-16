package tortuga_liebre;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Menu extends JFrame {
	
	private JLabel lblPuente1;
	private JTextField minPuente1;
	private JTextField minPuente2;
	private JTextField minPuente3;

	public Menu() {
		CrearInterfaz();
	}
	
	public void CrearInterfaz() {
		setSize(300,300);
		setLayout(null);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		lblPuente1 = new JLabel("Puente 1:");
		lblPuente1.setBounds(10,40,100,30);
		lblPuente1.setVisible(true);
		add(lblPuente1);
		
		minPuente1 = new JTextField();
		minPuente1.setBounds(70,40,100,30);
		minPuente1.setVisible(true);
		add(minPuente1);
		
		setVisible(true);
	}
}

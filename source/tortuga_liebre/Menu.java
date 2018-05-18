package tortuga_liebre;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Menu extends JFrame implements ActionListener {
	
	private JLabel lblPuente1,lblPuente2,lblPuente3;
	private JLeeEntero [] cajas;
	private JComboBox tortugas,liebres;
	private Rutinas rutinas = new Rutinas();
	private JButton empezar;
	
	public Menu() {
		CrearInterfaz();
	}
	
	public void CrearInterfaz() {
		setSize(300,300);
		setLayout(null);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		creaCajas();
		//CAJAS DE TEXTO
		int cont = 0;
		for(int i = 0 ; i < cajas.length ;i++){
			cajas[i].setBounds(90, 30+cont, 100, 25);
			add(cajas[i]);
			cont += 30;
		}
		
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
		empezar = new JButton("Start");
		empezar.setBounds(90,220,100,20);
		add(empezar);
		escuchadores();	
		setVisible(true);
	}
	public void escuchadores(){
		empezar.addActionListener(this);
		tortugas.addActionListener(this);
		liebres.addActionListener(this);
	}
	public void paint(Graphics g) {
		
		Graphics2D gra = (Graphics2D) g;
		g.drawImage(rutinas.obtenerImagen("./fondoMenu.png"), 0, 0, null);	
		
		g.fillRoundRect (15, 65, 70, 20, 1, 1);
		g.setColor(Color.WHITE);
		gra.drawString("PUENTE 1:", 18, 80);
		gra.setColor(Color.BLACK);
		
		g.fillRoundRect (15, 95, 70, 20, 1, 1); 
		g.setColor(Color.WHITE);
		gra.drawString("PUENTE 2:", 18, 110);
		gra.setColor(Color.BLACK);
		
		g.fillRoundRect (15, 125, 70, 20, 1, 1);
		g.setColor(Color.WHITE);
		gra.drawString("PUENTE 3:", 18, 140);
		gra.setColor(Color.BLACK);
		refrescar();
	}
	public void refrescar() {
		tortugas.update(tortugas.getGraphics());
		liebres.update(liebres.getGraphics());
		empezar.update(empezar.getGraphics());
		for(int i = 0 ; i < cajas.length ;i++ ){
			cajas[i].update(cajas[i].getGraphics());
		}
		
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == empezar){
			if( !(cajas[0].getText().length() == 0 || cajas[1].getText().length() == 0 ||cajas[2].getText().length() == 0 
					|| tortugas.getSelectedIndex() < 0 || liebres.getSelectedIndex() < 0) ) {
				int puentes [] = getContenido();
				int NoTortugas = Integer.parseInt(""+tortugas.getSelectedItem());
				int NoLiebres  = Integer.parseInt(""+liebres.getSelectedItem());
				new Carrera(puentes,NoTortugas,NoLiebres);
				return;
			}else return;
		}
		if(e.getSource() == liebres || e.getSource() == tortugas){
			repaint();
			return;
		}
	}
	public void creaCajas(){
		cajas = new JLeeEntero[3];
		for(int i = 0 ; i < 3 ; i++){
			cajas[i] = new JLeeEntero();
		}
	}
	public int [] getContenido(){
		int puentes [] = new int [3];
		for(int i = 0 ; i < cajas.length ; i++){
			puentes[i] = Integer.parseInt(cajas[i].getText());
		}
		return puentes;
	}
}

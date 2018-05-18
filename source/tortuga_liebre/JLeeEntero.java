package tortuga_liebre;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
public class JLeeEntero extends JTextField implements KeyListener,FocusListener{
	private int Tama�o;
	private Color c;
	public JLeeEntero(){
		this(5);
	}
	public JLeeEntero(int Tama�o){
		super(Tama�o);
		this.Tama�o=Tama�o;
		c=getBackground();
		addKeyListener(this);
		addFocusListener(this);
		getDropTarget().setActive(false); //Evita que se arrastre informaci�n a la caja de texto
	}
	public long getValor(){
		long v;
		try{
			if(getText().length()==0)
				return 0;
			v=Long.parseLong(getText());
		} catch (Exception e){v=0;}
		return v;
	}
	public void focusGained(FocusEvent Evt){
		setFocusTraversalKeysEnabled(false);
		selectAll();
		setBorder(new LineBorder(Color.RED));
		Font Fuente=new Font("TimesRoman",Font.BOLD,22);
		setFont(Fuente);
		JTextField Tra=(JTextField) Evt.getSource();
		Tra.setBackground(Color.LIGHT_GRAY);
	}

	public void focusLost(FocusEvent Evt){
		setBorder(new LineBorder(Color.LIGHT_GRAY));
		JTextField Tra=(JTextField) Evt.getSource();
		Font Fuente=new Font("TimesRoman",0,18);
		Tra.setFont(Fuente);
		Tra.setBackground(c);
	}
	public void keyPressed(KeyEvent Evt){
		if(Evt.isControlDown()){
			Evt.consume();
			return;
		}
		//consume la tecla Inicio,flecha izq o flecha der
		if(Evt.getKeyCode()==36 ||Evt.getKeyCode()==37 || Evt.getKeyCode()==39){
			Evt.consume();
			return;
		}
	}
	public void keyReleased(KeyEvent Evt){}
	public void keyTyped(KeyEvent Evt){
		if(getText().length()==Tama�o){
			Evt.consume();
			Toolkit.getDefaultToolkit().beep();
			return;
		}
		if(!(Evt.getKeyChar()>='0' && Evt.getKeyChar()<='9')){
			Evt.consume();
			return;
		}
	}
}
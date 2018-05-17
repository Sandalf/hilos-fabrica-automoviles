package fabrica_1;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Rutinas {
	static Random R = new Random();
	static String[] VN = { "Alicia", "Maria", "Sofia", "Antonio", "Nereida", "Carolina", "Rebaca", "Javier", "Luis" };
	static String[] VA = { "Garcia", "Lopez", "Perez", "Urias", "Mendoza", "Coppel", "Diaz" };
	static boolean[] Sexo = { false, false, false, true, false, false, false, true, true };

	public static String PonBlancos(String Texto, int Tamano) {
		while (Texto.length() < Tamano)
			Texto += " ";
		return Texto;
	}

	public static String PonCeros(int Valor, int Tamano) {
		String Texto = Valor + "";
		while (Texto.length() < Tamano)
			Texto = "0" + Texto;
		return Texto;
	}

	public static int nextInt(int Valor) {
		return R.nextInt(Valor);
	}

	public static int nextInt(int Ini, int Fin) {
		return R.nextInt(Fin - Ini + 1) + Ini;
	}

	public static String nextNombre(int Numero) {
		String Nom = "", NomTra = "";
		int Pos;
		boolean Genero = true;
		;

		for (int i = 0; i < Numero; i++) {
			Pos = nextInt(VN.length);

			NomTra = VN[Pos] + " ";

			if (i == 0) {
				Genero = Sexo[Pos];
			}

			if (Genero != Sexo[Pos] || i > 0 && Nom.indexOf(NomTra) > -1) {
				i--;
				continue;
			}

			Nom += NomTra + " ";

		}
		for (byte i = 0; i < 2; i++) {
			Nom += VA[nextInt(VA.length)] + " ";
		}
		return Nom;
	}

	public static ImageIcon AjustarImagen(String ico, int Ancho, int Alto) {
		ImageIcon tmpIconAux = new ImageIcon(ico);
		// Escalar Imagen
		ImageIcon tmpIcon = new ImageIcon(tmpIconAux.getImage().getScaledInstance(Ancho, Alto, Image.SCALE_SMOOTH));// SCALE_DEFAULT
		return tmpIcon;
	}
	
	public BufferedImage obtenerImagen(String ruta) {
		BufferedImage imagen = null;	
		try {
			InputStream is = this.getClass().getResourceAsStream(ruta);		
			imagen = (BufferedImage) ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return imagen;
	}
}

package tortuga_liebre;

public class Liebre extends Corredor {

	public Liebre(int corredorID, Semaforo[] semaforos, Puente[] puentes) {
		super(corredorID, semaforos, puentes);
		setImagenCorredor(this.rutinas.obtenerImagen("./liebre"+this.rutinas.nextInt(1,4)+".png"));
		this.setMinPasos(3);
		this.setMaxPasos(8);
	}

}

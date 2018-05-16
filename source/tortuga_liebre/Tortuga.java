package tortuga_liebre;


public class Tortuga extends Corredor {
	
	private Rutinas rutinas = new Rutinas();

	public Tortuga(int corredorID, Semaforo[] semaforos, Puente[] puentes) {
		super(corredorID, semaforos, puentes);
		setImagenCorredor(rutinas.obtenerImagen("./tortuga.png"));
		this.setMinPasos(2);
		this.setMaxPasos(5);
	}

}

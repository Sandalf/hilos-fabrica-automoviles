package tortuga_liebre;

public class Liebre extends Corredor {

	private Rutinas rutinas = new Rutinas();

	public Liebre(int corredorID, Semaforo[] semaforos, Puente[] puentes) {
		super(corredorID, semaforos, puentes);
		setImagenCorredor(rutinas.obtenerImagen("./liebre1.png"));
	}

	public void run() {
		try {
			while(estaCorriendo()) {
				int pasos = rutinas.nextInt(3,10);

				int numPuente = enPuente(getDistanciaRecorrida());
				if(numPuente > -1) {
					getSemaforos()[numPuente].espera();
					Puente puente = getPuentes()[numPuente];
					if(getPuentes()[numPuente].estaDisponible()) {					
						atravesarPuente(numPuente,
								puente.getPosicion(),
								puente.getPosicion()+puente.getAncho());
					}
					getSemaforos()[numPuente].libera();
				} else {
					setDistanciaRecorrida(getDistanciaRecorrida()+pasos);
				}

				if (getDistanciaRecorrida() >= 500) {
					setCorriendo(false);
				}				
				sleep(100);			
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void atravesarPuente(int numPuente, int inicioPuente, int finPuente) {
		try {
			getPuentes()[numPuente].setDisponible(false);
			while(getDistanciaRecorrida() >= inicioPuente && 
				getDistanciaRecorrida() <= finPuente) {
				int pasos = rutinas.nextInt(3,10);
				setDistanciaRecorrida(getDistanciaRecorrida()+pasos);
				sleep(100);
			}
			getPuentes()[numPuente].setDisponible(true);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

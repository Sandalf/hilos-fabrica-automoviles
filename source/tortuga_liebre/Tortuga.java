package tortuga_liebre;


public class Tortuga extends Corredor {
	
	private Rutinas rutinas = new Rutinas();

	public Tortuga(int corredorID, Semaforo[] semaforos, Puente[] puentes) {
		super(corredorID, semaforos, puentes);
		setImagenCorredor(rutinas.obtenerImagen("./tortuga.png"));
	}
	
	public void run() {
		try {
			while(estaCorriendo()) {
				int pasos = rutinas.nextInt(2,5);

				int numPuente = enPuente(getDistanciaRecorrida());
				if(numPuente > -1) {
					System.out.println("En Puente: "+numPuente);
					getSemaforos()[numPuente].espera();
					Puente puente = getPuentes()[numPuente];
					if(puente.estaDisponible()) {					
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
			while(getDistanciaRecorrida() >= inicioPuente && getDistanciaRecorrida() <= finPuente) {
				int pasos = rutinas.nextInt(2,5);
				setDistanciaRecorrida(getDistanciaRecorrida()+pasos);
				sleep(100);
			}
			getPuentes()[numPuente].setDisponible(true);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

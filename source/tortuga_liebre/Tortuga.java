package tortuga_liebre;


public class Tortuga extends Corredor {
	
	private Rutinas rutinas = new Rutinas();

	public Tortuga(int corredorID, Semaforo[] semaforos, boolean[] puentes) {
		super(corredorID, semaforos, puentes);
		setImagenCorredor(rutinas.obtenerImagen("./tortuga.png"));
	}
	
	public void run() {
		try {
			while(estaCorriendo()) {
				int pasos = rutinas.nextInt(2,5);

				if(enPuente(getDistanciaRecorrida())) {
					getSemaforos()[0].espera();
					if(!getPuentes()[0]) {					
						atravesarPuente(30,200);
					}
					getSemaforos()[0].libera();
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

	public void atravesarPuente(int inicioPuente, int finPuente) {
		try {
			getPuentes()[0] = true;
			while(getDistanciaRecorrida() >= inicioPuente && getDistanciaRecorrida() <= finPuente) {
				int pasos = rutinas.nextInt(3,10);
				setDistanciaRecorrida(getDistanciaRecorrida()+pasos);
				sleep(100);
			}
			getPuentes()[0] = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

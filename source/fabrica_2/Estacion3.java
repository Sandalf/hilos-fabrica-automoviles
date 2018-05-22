package fabrica_2;

public class Estacion3 extends Thread {

	private int linea;
	private int estacion;
	private boolean activo;
	
	static Semaforo semaforo;
	static Semaforo semNumCarros;
	static int limiteCarros;
	static int carrosFabricados;

	public Estacion3(int linea, int estacion, Semaforo semaforo, Semaforo semNumCarros, int limiteCarros,int carrosFabricados) {
		this.linea = linea;
		this.estacion = estacion;
		this.semaforo = semaforo;
		this.semNumCarros = semNumCarros;
		this.limiteCarros = limiteCarros;
		this.carrosFabricados = carrosFabricados;
	}

	public void run() {
		try {
			while(!limiteFabricacionAlcanzada()) {
				semNumCarros.libera();
				if(activo) {				
					semaforo.espera();
					System.out.println("("+linea+","+estacion+") Fabricando");				
					sleep(1000);			
					System.out.println("("+linea+","+estacion+") Terminado");
					semaforo.libera();
					setActivo(false);
					incrementarNumCarros();
				}			
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private boolean limiteFabricacionAlcanzada() {
		semNumCarros.espera();
		return carrosFabricados >= limiteCarros ? true : false;
	}
	
	private void incrementarNumCarros() {
		semNumCarros.espera();
		carrosFabricados++;
		System.out.println("Carros Fabricados: " + carrosFabricados);
		semNumCarros.libera();
	}
	
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
}
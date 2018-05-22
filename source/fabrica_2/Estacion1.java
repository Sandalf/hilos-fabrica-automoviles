package fabrica_2;

public class Estacion1 extends Thread {

	private int linea;
	private int estacion;
	private Estacion2 siguienteEstacion;
	
	static Semaforo semaforo;
	static Semaforo semNumCarros;
	static int limiteCarros;
	static int carrosFabricados;

	public Estacion1(int linea, int estacion, Semaforo semaforo, Semaforo semNumCarros, int limiteCarros,int carrosFabricados, Estacion2 siguienteEstacion) {
		this.linea = linea;
		this.estacion = estacion;
		this.siguienteEstacion = siguienteEstacion;
		this.semaforo = semaforo;
		this.semNumCarros = semNumCarros;
		this.limiteCarros = limiteCarros;
		this.carrosFabricados = carrosFabricados;
	}

	public void run() {
		try {
			while(!limiteFabricacionAlcanzada()) {
				semNumCarros.libera();
				semaforo.espera();
				System.out.println("("+linea+","+estacion+") Fabricando");
				sleep(1000);			
				System.out.println("("+linea+","+estacion+") Terminado");
				semaforo.libera();
				siguienteEstacion.setActivo(true);
				
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private boolean limiteFabricacionAlcanzada() {
		semNumCarros.espera();
		return carrosFabricados >= limiteCarros ? true : false;
	}

}

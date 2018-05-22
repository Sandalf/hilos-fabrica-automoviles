package fabrica_2;

public class Estacion2 extends Thread {

	private int linea;
	private int estacion;
	private boolean activo;
	private Estacion3 siguienteEstacion;
	
	static Semaforo semaforo;
	static Semaforo semNumCarros;
	static int limiteCarros;
	static int carrosFabricados;

	public Estacion2(int linea, int estacion, Semaforo semaforo, Semaforo semNumCarros, int limiteCarros,int carrosFabricados, Estacion3 siguienteEstacion) {
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
				if(activo) {				
					semaforo.espera();
					System.out.println("("+linea+","+estacion+") Fabricando");				
					sleep(1000);			
					System.out.println("("+linea+","+estacion+") Terminado");
					semaforo.libera();
					setActivo(false);
					siguienteEstacion.setActivo(true);
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
	
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
}

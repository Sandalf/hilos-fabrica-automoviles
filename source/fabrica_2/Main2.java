package fabrica_2;

public class Main2 {
	
	static Estacion1 estacion1;
	static Estacion2 estacion2;
	static Estacion3 estacion3;
	static Semaforo[] semaforos;
	static Semaforo semNumCarros;
	static int limiteCarros = 10;
	static int carrosFabricados = 0;
	static Thread[][] hilos;

	public static void main(String[] args) {
		hilos = new Thread[6][3];
		
		semaforos = new Semaforo[3];
		semaforos[0] = new Semaforo(5);
		semaforos[1] = new Semaforo(4);
		semaforos[2] = new Semaforo(3);
		
		semNumCarros = new Semaforo(1);
		
		for(int i = 0; i < hilos.length; i++) {
			estacion3 = new Estacion3(i,2,semaforos[0],semNumCarros,limiteCarros,carrosFabricados);
			estacion2 = new Estacion2(i,1,semaforos[1],semNumCarros,limiteCarros,carrosFabricados,estacion3);
			estacion1 = new Estacion1(i,0,semaforos[2],semNumCarros,limiteCarros,carrosFabricados,estacion2);
			hilos[i][0] = estacion1;
			hilos[i][1] = estacion2;
			hilos[i][2] = estacion3;
		}
		
		for(int i = 0; i < hilos.length; i++) {
			for(int j = 0; j < hilos[0].length; j++) {
				hilos[i][j].start();
			}
		}
		
	}

}

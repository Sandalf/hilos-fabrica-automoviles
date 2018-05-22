package fabrica_2;

public class Main2 {
	
	static Estacion1 estacion1;
	static Estacion2 estacion2;
	static Estacion3 estacion3;
	static Estacion4 estacion4;
	static Estacion5 estacion5;
	static Estacion6 estacion6;
	static Semaforo[] semaforos;
	static Semaforo semNumCarros;
	static int limiteCarros = 10;
	static int carrosFabricados = 0;
	static Thread[][] hilos;

	public static void main(String[] args) {
		hilos = new Thread[6][6];
		
		semaforos = new Semaforo[6];
		semaforos[0] = new Semaforo(5);
		semaforos[1] = new Semaforo(4);
		semaforos[2] = new Semaforo(3);
		semaforos[3] = new Semaforo(3);
		semaforos[4] = new Semaforo(3);
		semaforos[5] = new Semaforo(3);
		
		semNumCarros = new Semaforo(1);
		
//		for(int i = 0; i < hilos.length; i++) {
//			estacion6 = new Estacion6(i,2,semaforos[5],semNumCarros,limiteCarros,carrosFabricados);
//			estacion5 = new Estacion5(i,2,semaforos[4],semNumCarros,limiteCarros,carrosFabricados,estacion6);
//			estacion4 = new Estacion4(i,2,semaforos[3],semNumCarros,limiteCarros,carrosFabricados,estacion5);
//			estacion3 = new Estacion3(i,2,semaforos[2],semNumCarros,limiteCarros,carrosFabricados,estacion4);
//			estacion2 = new Estacion2(i,1,semaforos[1],semNumCarros,limiteCarros,carrosFabricados,estacion3);
//			estacion1 = new Estacion1(i,0,semaforos[0],semNumCarros,limiteCarros,carrosFabricados,estacion2);
//			hilos[i][0] = estacion1;
//			hilos[i][1] = estacion2;
//			hilos[i][2] = estacion3;
//			hilos[i][3] = estacion4;
//			hilos[i][4] = estacion5;
//			hilos[i][5] = estacion6;
//		}
		
		for(int i = 0; i < hilos.length; i++) {
			for(int j = 0; j < hilos[0].length; j++) {
				hilos[i][j].start();
			}
		}
		
	}

}

package fabrica_2;

public class Semaforo {

	private int recursos;

	public Semaforo (int recursos) {
		this.recursos = recursos;
	}

	public synchronized void espera() {
		while (recursos<1) {
			//System.out.println("En espera");
			try {
				wait();
			} catch(InterruptedException e) {}
		}
		recursos--;
	}

	public synchronized void libera() {
		recursos++;
		notify();
	}
}

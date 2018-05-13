package tortuga_liebre;

public class Corredor extends Thread {
	
	private int corredorID;

	public Corredor(int corredorID) {
		this.corredorID = corredorID;
	}

	public int getCorredorID() {
		return corredorID;
	}

	public void setCorredorID(int corredorID) {
		this.corredorID = corredorID;
	}
	
}


public class Cell {
	private boolean alive;
	private int nbNeighbors;
	
	// CONSTRUCTOR
	public Cell(boolean alive) {
		this.alive = alive;
		this.nbNeighbors = 0;
	}
	
	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public int getNbNeighbors() {
		return nbNeighbors;
	}

	public void setNbNeighbors(int nbNeighbors) {
		this.nbNeighbors = nbNeighbors;
	}
	
	public void incrementNbNeighbors() {
		nbNeighbors += 1;
	}

}
